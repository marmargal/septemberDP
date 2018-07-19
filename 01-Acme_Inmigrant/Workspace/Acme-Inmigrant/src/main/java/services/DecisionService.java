package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.DecisionRepository;
import domain.Application;
import domain.Decision;
import forms.DecisionForm;

@Service
@Transactional
public class DecisionService {

	// Managed repository

	@Autowired
	private DecisionRepository decisionRepository;

	// Suporting services
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private Validator validator;

	// Constructors

	public DecisionService() {
		super();
	}

	// Simple CRUD methods

	public Decision create(int applicationId) {
		Decision res = new Decision();

		Date moment = new Date(System.currentTimeMillis() - 1000);

		Application application = this.applicationService.findOne(applicationId);

		res.setAccept(false);
		res.setMoment(moment);
		res.setApplication(application);

		return res;
	}

	public Collection<Decision> findAll() {
		Collection<Decision> res;
		res = decisionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Decision findOne(int decisionId) {
		Assert.isTrue(decisionId != 0);
		Decision res;
		res = decisionRepository.findOne(decisionId);
		Assert.notNull(res);
		return res;
	}

	public Decision save(Decision decision) {
		Decision res;
		res = decisionRepository.save(decision);
		return res;
	}

	public void delete(Decision decision) {
		Assert.notNull(decision);
		Assert.isTrue(decision.getId() != 0);
		Assert.isTrue(decisionRepository.exists(decision.getId()));
		decisionRepository.delete(decision);
	}

	public DecisionForm construct(Decision decision) {
		Assert.notNull(decision);
		DecisionForm res = new DecisionForm();
		
		res.setId(decision.getId());
		res.setAccepted(decision.getAccept());
		res.setComment(decision.getComment());
		res.setApplicationId(decision.getApplication().getId());
		
		return res;
	}
	
	public Decision reconstruct(DecisionForm decisionForm,
			BindingResult binding){
		Assert.notNull(decisionForm);
		Application application = this.applicationService.findOne(decisionForm.getApplicationId());
		Decision res;
		
		if(decisionForm.getId()!=0)
			res = this.findOne(decisionForm.getId());
		else
			res = this.create(application.getId());
		
		res.setAccept(decisionForm.isAccepted());
		res.setComment(decisionForm.getComment());
		res.setApplication(application);
		
		if(binding!=null)
			this.validator.validate(res,binding);
		
		return res;
	}

}
