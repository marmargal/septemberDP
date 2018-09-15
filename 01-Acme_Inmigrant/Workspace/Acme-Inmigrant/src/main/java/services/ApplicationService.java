package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ApplicationRepository;
import domain.Application;
import domain.ContactSection;
import domain.CreditCard;
import domain.EducationSection;
import domain.Immigrant;
import domain.Officer;
import domain.PersonalSection;
import domain.Question;
import domain.SocialSection;
import domain.Visa;
import domain.WorkSection;
import forms.ApplicationForm;

@Service
@Transactional
public class ApplicationService {

	// Managed repository ------------------------------------------------------
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	// Suporting services ------------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;

	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private Validator validator;
	
	// Constructors ------------------------------------------------------------
	
	public ApplicationService(){
		super();
	}
	
	// Simple CRUD methods ------------------------------------------------------
	
	public Application create(){
		this.immigrantService.checkAuthority();
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		Application res;
		res = new Application();
		
		Date openedMoment = new Date(System.currentTimeMillis()-1000);
		
		PersonalSection personalSection;
		List<ContactSection> contactSection;
		List<WorkSection> workSection;
		List<SocialSection> socialSection;
		List<EducationSection> educationSection;
		List<Question> question;
		
		personalSection = new PersonalSection();
		contactSection = new ArrayList<ContactSection>();
		workSection = new ArrayList<WorkSection>();
		socialSection = new ArrayList<SocialSection>();
		educationSection = new ArrayList<EducationSection>();
		question = new ArrayList<Question>();
		
		res.setTicker(this.generatedTicker());
		res.setOpenedMoment(openedMoment);
		
		res.setPersonalSection(personalSection);
		res.setContactSection(contactSection);
		res.setWorkSection(workSection);
		res.setSocialSection(socialSection);
		res.setEducationSection(educationSection);
		res.setQuestion(question);
		res.setImmigrant(immigrant);
		
		res.setCreditCard(null);
		
		return res;
	}
	
	public Collection<Application> findAll(){
		Collection<Application> res;
		res = applicationRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Application findOne(int applicationId){
		Assert.isTrue(applicationId != 0);
		Application res;
		res = applicationRepository.findOne(applicationId);
		Assert.notNull(res);
		return res;
	}
	
	public Application save(Application application){
		if(application.getCreditCard() != null){
			Assert.isTrue(this.checkCreditCard(application.getCreditCard()));
		}
		this.immigrantService.checkAuthority();
		Immigrant immigrantTest = immigrantService.findByPrincipal();
		Assert.isTrue(immigrantTest.getId() == application.getImmigrant().getId());
		Visa visa = new Visa();
		Application res = new Application();
		Immigrant immigrant = new Immigrant();
		List<Application> applicationsLinked = new ArrayList<Application>();
		Collection<Application> applications = new ArrayList<Application>();
		Collection<Application> immigrantApplications = new ArrayList<Application>();
		
		immigrant = application.getImmigrant();
		immigrantApplications = immigrant.getApplications();
		immigrantApplications.add(application);
		this.immigrantService.saveImmigrant(immigrant);
		
		if(application.isClosed()==true){
			application.setClosedMoment(new Date());
			visa = application.getVisa();
			applications = this.applicationRepository.findApplicationClosedFalseByVisa(visa.getId());
			for(Application a: applications){
				if(a.getImmigrant()==immigrantService.findByPrincipal()){
					a.setClosed(true);
					save(a);
				}
			}
			applicationsLinked = this.findApplicationsLinked(application);
			if(!applicationsLinked.isEmpty()){
				for(Application a: applicationsLinked){
					if(!a.isClosed()){
						a.setClosed(true);
						a.setClosedMoment(new Date());
						save(a);
					}
				}
			}
		}
		
		res = applicationRepository.save(application);
		
		return res;
	}
	
	public Application saveOfficerOfApplication(Application application){
		this.officerService.checkAuthority();
		Application res;
		res = applicationRepository.save(application);
		return res;
	}
	
	public void delete(Application application){
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(applicationRepository.exists(application.getId()));
		applicationRepository.delete(application);
	}
	
	// Other business methods ------------------------------------------------------
	
	public String generatedTicker() {
		String ticker;
		LocalDate date;
		String letters;
		String numbers;
		Random r;
		
		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		numbers = "0123456789";
		r = new Random();
		date = new LocalDate();
		
		ticker = String.valueOf(date.getYear() % 100 < 10 ? "0" + date.getYear() : date.getYear() % 100) + 
					String.valueOf(date.getMonthOfYear() < 10 ? "0" + date.getMonthOfYear() : date.getMonthOfYear())
					+ String.valueOf(date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + "-";
		for (int i = 0; i < 4; i++)
			ticker = ticker + letters.charAt(r.nextInt(letters.length()));
		for (int i = 0; i < 2; i++)
			ticker = ticker + numbers.charAt(r.nextInt(numbers.length()));
		
		return ticker;
	}
	
	public Collection<Application> getApplicationByImmigrant(int immigrantId){
		Collection<Application> res;
		
		res = this.applicationRepository.findApplicationByImmigrant(immigrantId);
		
		return res;
	}
	
	public boolean checkCreditCard(final CreditCard creditCard) {
		boolean res;
		Calendar calendar;
		int actualYear;

		res = false;
		calendar = new GregorianCalendar();
		actualYear = calendar.get(Calendar.YEAR);
		actualYear = actualYear % 100;
		
		if (creditCard.getExpirationYear() != 0) {
			if (creditCard.getExpirationYear() > actualYear) {
				res = true;
			} else if (creditCard.getExpirationYear() == actualYear && creditCard.getExpirationMonth() >= calendar.get(Calendar.MONTH)) {
				res = true;
			} 
		} 
		return res;
	}
	
	
	
	public boolean checkCreditCardCvvAndDate(final CreditCard creditCard) {
		boolean res = true;
		if(creditCard.getCvv() > 999 || creditCard.getCvv() < 100){
			res = false;
		}
		if(creditCard.getExpirationMonth() > 12 || creditCard.getExpirationMonth() < 1){
			res = false;
		}
		if(creditCard.getExpirationYear() > 99 || creditCard.getExpirationYear() < 1){
			res = false;
		}else{
			Calendar calendar = new GregorianCalendar();
			int actualYear = calendar.get(Calendar.YEAR) % 100;
			if(creditCard.getExpirationYear() < actualYear){
				res = false;
			}else if(creditCard.getExpirationYear() == actualYear && creditCard.getExpirationMonth() < calendar.get(Calendar.MONTH)){
				res = false;
			}
		}
		
		Pattern pattern = Pattern.compile("^\\d{4}\\s?\\d{4}\\s?\\d{4}\\s?\\d{4}$");
		Matcher m = pattern.matcher(creditCard.getNumber());
		if(!m.find()){
			res = false;
		}
		
		return res;
	}
	
	public Application reconstruct(final Application application, final BindingResult binding) {
		Application res;
		Application applicationFinal;
		Date openedMoment;
		if (application.getId() == 0) {
			Immigrant immigrant;
			
			openedMoment = new Date(System.currentTimeMillis() - 1000);
			application.setOpenedMoment(openedMoment);

			immigrant = this.immigrantService.findByPrincipal();
			application.setImmigrant(immigrant);
			
			res = application;
		} else {
			applicationFinal = this.applicationRepository.findOne(application.getId());
			
			application.setId(applicationFinal.getId());
			application.setVersion(applicationFinal.getVersion());
			application.setTicker(applicationFinal.getTicker());
			application.setOpenedMoment(applicationFinal.getOpenedMoment());
			application.setClosedMoment(applicationFinal.getClosedMoment());
			
			res = application;
		}
		this.validator.validate(res, binding);
		return res;
	}
	
	public ApplicationForm construct(Application application){
		ApplicationForm res = new ApplicationForm();
		CreditCard cc = new CreditCard();
		PersonalSection personalSection = new PersonalSection();
		SocialSection socialSection = new SocialSection();
		
		cc = application.getCreditCard();
		personalSection = application.getPersonalSection();
		socialSection = application.getSocialSection().get(0);
		
		//CREDIT CARD
		if(cc != null){
			
			String brandName = cc.getBrandName();
			Integer cvv = cc.getCvv();
			Integer expirationMonth = cc.getExpirationMonth();
			Integer expirationYear = cc.getExpirationYear();
			String holderName = cc.getHolderName();
			String number = cc.getNumber();
			
			res.setBrandName(brandName);
			res.setCvv(cvv);
			res.setExpirationMonth(expirationMonth);
			res.setExpirationYear(expirationYear);
			res.setHolderName(holderName);
			res.setNumber(number);
		}
		
		//PERSONAL SECTION
		res.setNames(personalSection.getNames());
		res.setBithPlace(personalSection.getBirthPlace());
		res.setBithDate(personalSection.getBirthDate());
		res.setPicture(personalSection.getPicture());
		
		//SOCIAL SECTION
		res.setNickName(socialSection.getNickName());
		res.setSocialNetwork(socialSection.getSocialNetwork());
		res.setProfileLink(socialSection.getProfileLink());
		
		//VISA
		res.setVisa(application.getVisa());
		
		//APPLICATION
		res.setId(application.getId());
		res.setClosed(application.isClosed());
		if(application.getApplication() != null){
			res.setTickerApplicationLinked(application.getTicker());
		}
		
		return res;
	}
	
	public Application reconstruct(ApplicationForm applicationForm, BindingResult binding){
		Assert.notNull(applicationForm);
		
		CreditCard cc = new CreditCard();
		SocialSection socialSection = new SocialSection();
		PersonalSection personalSection = new PersonalSection();
		List<SocialSection> socialSections = new ArrayList<SocialSection>();
		
		Application res = new Application();

		if (applicationForm.getId() != 0){
			res = this.findOne(applicationForm.getId());
			socialSection = res.getSocialSection().get(0);
		}else 
			res = this.create();
		
		//CREDIT CARD
		if(!applicationForm.getBrandName().isEmpty() || applicationForm.getCvv() != null || applicationForm.getExpirationMonth() != null || 
				applicationForm.getExpirationYear() != null || !applicationForm.getHolderName().isEmpty() || !applicationForm.getNumber().isEmpty()){
//			Integer cvv = Integer.parseInt(applicationForm.getCvv());
//			Integer expirationMonth = Integer.parseInt(applicationForm.getExpirationMonth());
//			Integer expirationYear = Integer.parseInt(applicationForm.getExpirationYear());
			
			cc.setBrandName(applicationForm.getBrandName());
			cc.setCvv(applicationForm.getCvv());
			cc.setExpirationMonth(applicationForm.getExpirationMonth());
			cc.setExpirationYear(applicationForm.getExpirationYear());
			cc.setHolderName(applicationForm.getHolderName());
			cc.setNumber(applicationForm.getNumber());
			res.setCreditCard(cc);
		}else{
			res.setCreditCard(null);
		}
		
		//PERSONAL SECTION
		personalSection.setNames(applicationForm.getNames());
		personalSection.setBirthPlace(applicationForm.getBithPlace());
		personalSection.setBirthDate(applicationForm.getBithDate());
		personalSection.setPicture(applicationForm.getPicture());
		res.setPersonalSection(personalSection);
		
		//SOCIAL SECTION
		socialSection.setNickName(applicationForm.getNickName());
		socialSection.setSocialNetwork(applicationForm.getSocialNetwork());
		socialSection.setProfileLink(applicationForm.getProfileLink());
		socialSections.add(0, socialSection);
		res.setSocialSection(socialSections);
		
		//VISA	
		res.setVisa(applicationForm.getVisa());
		
		//APPLICATION
		res.setClosed(applicationForm.isClosed());
		if(applicationForm.isClosed()){
			res.setClosedMoment(new Date());
		}
		if(applicationForm.getTickerApplicationLinked().isEmpty()){
			Application application = new Application();
			application = this.findApplicationByTicker(applicationForm.getTickerApplicationLinked());
			res.setApplication(application);
		}
		
		this.validator.validate(res, binding);

		return res;
	}
	
	public void flush() {
		this.applicationRepository.flush();
	}
	
	public Application findApplicationRejected(){
		Application res = new Application();
		Officer officer = this.officerService.findByPrincipal();
		res = this.applicationRepository.findApplicationRejected(officer.getId());
		return res;
	}
	
	public Collection<Application> findApplicationClosed(){
		this.immigrantService.checkAuthority();
		Collection<Application> res = new ArrayList<Application>();
		Immigrant immigrant = this.immigrantService.findByPrincipal();
		res = this.applicationRepository.findApplicationClosed(immigrant.getId());
		return res;
	}
	
	public Collection<Application> findApplicationAccepted(){
		this.immigrantService.checkAuthority();
		Collection<Application> res = new ArrayList<Application>();
		Immigrant immigrant = this.immigrantService.findByPrincipal();
		res = this.applicationRepository.findApplicationAccepted(immigrant.getId());
		return res;
	}
	
	public Collection<Application> findApplicationRejectedbyImmigrant(){
		this.immigrantService.checkAuthority();
		Collection<Application> res = new ArrayList<Application>();
		Immigrant immigrant = this.immigrantService.findByPrincipal();
		res = this.applicationRepository.findApplicationRejectedbyImmigrant(immigrant.getId());
		return res;
	}

	public Collection<Application> findApplicationsSelfAssigning(){
		Collection<Application> res = new ArrayList<Application>();
		res = this.applicationRepository.findApplicationsSelfAssigning();
		return res;
	}
	
	public Collection<Application> findApplicationsWhitDecisionByOfficer(int officerId){
		Collection<Application> res = new ArrayList<Application>();
		res = this.applicationRepository.findApplicationsWhitDecisionByOfficer(officerId);
		return res;
	}

	public Application findApplicationByTicker(String tickerValue){
		Application application = new Application();
		application = this.applicationRepository.findApplicationByTicker(tickerValue);
		return application;
	}
	
	public Collection<Application> findApplicationsByVisa(int visaId){
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.applicationRepository.findApplicationsByVisa(visaId);
		return applications;
	}
	
	public List<Application> findApplicationsLinked(Application application){
		List<Application> applicationLinked = new ArrayList<Application>();
		Application son = new Application();
		Application iter = new Application();
		iter = application;
		
		int i = 0;
		while(i == 0){
			if(iter.getApplication() != null && !applicationLinked.contains(iter.getApplication())){
				son = iter.getApplication();
				applicationLinked.add(son);
				iter = son;
			}else{
				i = 1;
			}
		}
		return applicationLinked;
	}
	
	public void checkApplicationIsNotCloser(int applicationId){
		Application application= new Application();
		application = this.findOne(applicationId);
		Assert.isTrue(application.isClosed() == false);
	}

}
