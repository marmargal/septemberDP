package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.ContactSection;
import domain.EducationSection;
import domain.Immigrant;
import domain.PersonalSection;
import domain.Question;
import domain.SocialSection;
import domain.WorkSection;

@Service
@Transactional
public class ApplicationService {

	// Managed repository ------------------------------------------------------
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	// Suporting services ------------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;
	
	// Constructors ------------------------------------------------------------
	
	public ApplicationService(){
		super();
	}
	
	// Simple CRUD methods ------------------------------------------------------
	
	public Application create(){
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		Application res;
		res = new Application();
		
		Date openedMoment = new Date(System.currentTimeMillis()-1000);
		Date closedMoment = new Date(System.currentTimeMillis()-10);
		
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
		res.setClosedMoment(closedMoment);
		
		res.setPersonalSection(personalSection);
		res.setContactSection(contactSection);
		res.setWorkSection(workSection);
		res.setSocialSection(socialSection);
		res.setEducationSection(educationSection);
		res.setQuestion(question);
		
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
		Application res;
		Immigrant immigrant;
		immigrant = this.immigrantService.findByPrincipal();
		application.setImmigrant(immigrant);
		res = applicationRepository.save(application);
		Collection<Application> immigrantApplications = new ArrayList<Application>();
		immigrantApplications = immigrant.getApplications();
		immigrantApplications.add(application);
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
		Random r;
		
		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		r = new Random();
		date = new LocalDate();
		
		ticker = String.valueOf(date.getYear() % 100 < 10 ? "0" + date.getYear() : date.getYear() % 100) + 
					String.valueOf(date.getMonthOfYear() < 10 ? "0" + date.getMonthOfYear() : date.getMonthOfYear())
					+ String.valueOf(date.getDayOfMonth() < 10 ? "0" + date.getDayOfMonth() : date.getDayOfMonth()) + "-";
		for (int i = 0; i < 4; i++)
			ticker = ticker + letters.charAt(r.nextInt(letters.length()));
		
		ticker = ticker + "28";

		return ticker;
	}
	
	public Collection<Application> getApplicationByImmigrant(int immigrantId){
		Collection<Application> res;
		
		res = this.applicationRepository.findApplicationByImmigrant(immigrantId);
		
		return res;
	}
}
