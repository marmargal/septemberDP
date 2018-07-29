package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.QuestionRepository;
import domain.Application;
import domain.Immigrant;
import domain.Officer;
import domain.Question;
import forms.QuestionForm;

@Service
@Transactional
public class QuestionService {

	// Managed repository

	@Autowired
	private QuestionRepository questionRepository;

	// Suporting services

	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private ImmigrantService immigrantService;
	
	@Autowired
	private Validator		validator;

	// Constructors

	public QuestionService() {
		super();
	}

	// Simple CRUD methods

	public Question create(Integer applicationId) {
		this.officerService.checkAuthority();
		
		Question res = new Question();
		Officer officer = this.officerService.findByPrincipal();
		Application application = new Application();

		Date moment = new Date(System.currentTimeMillis() - 1000);
		application = this.applicationService.findOne(applicationId);

		res.setMoment(moment);
		res.setOfficer(officer);
		res.setApplication(application);

		return res;

	}

	public Collection<Question> findAll() {
		Collection<Question> res;
		res = questionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Question findOne(int questionId) {
		Assert.isTrue(questionId != 0);
		Question res;
		res = questionRepository.findOne(questionId);
		Assert.notNull(res);
		return res;
	}

	public Question save(Question question) {
		Assert.isTrue(checkCreateQuestion(question.getApplication()));
		Question res;
		
		res = questionRepository.save(question);
		
		return res;
	}

	public void delete(Question question) {
		Assert.notNull(question);
		Assert.isTrue(question.getId() != 0);
		Assert.isTrue(questionRepository.exists(question.getId()));
		questionRepository.delete(question);
	}
	
	public QuestionForm construct(Question question){
		QuestionForm res = new QuestionForm();
		
		res.setId(question.getId());
		res.setApplicationId(question.getApplication().getId());
		res.setText(question.getText());
		
		return res;
	}
	
	public Question reconstruct(QuestionForm questionForm, BindingResult binding){
		Assert.notNull(questionForm);
		
		Question res = new Question();

		if (questionForm.getId() != 0)
			res = this.findOne(questionForm.getId());
		else 
			res = this.create(questionForm.getApplicationId());
		
		res.setText(questionForm.getText());

		this.validator.validate(res, binding);

		return res;
	}
	
	public Application findApplicationSelfAsign(){
		Officer officer = this.officerService.findByPrincipal();
		Application application = new Application();
		
		application = this.questionRepository.findApplicationSelfAsign(officer.getId());
		
		return application;
	}
	
	
	private boolean checkCreateQuestion(Application application ){
		Boolean res = true;
		Collection<Application> applicationsAssign = new ArrayList<Application>();
		
		applicationsAssign = this.applicationService.findApplicationsSelfAssigning();
		
		if(!applicationsAssign.contains(application)){
			res = false;
		}
		return res;
	}

	public void checkAplicationNotApply(int applicationId) {
		Application application = this.applicationService.findOne(applicationId);
		Immigrant immigrant = this.immigrantService.findByPrincipal();
		Collection<Application> applications = immigrant.getApplications();
		Assert.isTrue(applications.contains(application));
	}

}
