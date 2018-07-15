package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ApplicationRepository;
import domain.Application;
import domain.ContactSection;
import domain.EducationSection;
import domain.PersonalSection;
import domain.Question;
import domain.SocialSection;
import domain.WorkSection;

@Service
@Transactional
public class ApplicationService {

	// Managed repository
	@Autowired
	private ApplicationRepository applicationRepository;
	
	// Suporting services
	
	// Constructors
	
	public ApplicationService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Application create(){
		Application res;
		res = new Application();
		
		// TODO: Aquí meter futuro método de generar Ticker
		String ticker = "ticker";
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
		
		res.setTicker(ticker);
		res.setOpenedMoment(openedMoment);
		res.setClosedMoment(closedMoment);
		
		res.setPersonalSection(personalSection);
		res.setContacSection(contactSection);
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
		res = applicationRepository.save(application);
		return res;
	}
	
	public void delete(Application application){
		Assert.notNull(application);
		Assert.isTrue(application.getId() != 0);
		Assert.isTrue(applicationRepository.exists(application.getId()));
		applicationRepository.delete(application);
	}
}
