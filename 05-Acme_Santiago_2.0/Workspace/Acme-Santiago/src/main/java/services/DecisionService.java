package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.DecisionRepository;
import domain.Administrator;
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
	private AdministratorService administratorService;
	
	@Autowired
	private Validator validator;

	// Constructors

	public DecisionService() {
		super();
	}

	// Simple CRUD methods

	public Decision create() {
		this.administratorService.checkAuthority();
		Decision res;
		res = new Decision();
		Administrator administrator = administratorService.findByPrincipal();
		
		res.setAdministrator(administrator);
		
		return res;
	}

	public Collection<Decision> findAll() {
		Collection<Decision> res;
		res = this.decisionRepository.findAll();
		return res;
	}

	public Decision findOne(final int id) {
		Assert.isTrue(id != 0);
		Decision res;
		res = this.decisionRepository.findOne(id);
		return res;
	}

	public Decision save(final Decision decision) {
		this.administratorService.checkAuthority();
		Assert.notNull(decision);
		
		Decision res;
		
		res = this.decisionRepository.save(decision);
		return res;
	}

	public void delete(Decision decision) {
		this.administratorService.checkAuthority();
		Assert.notNull(decision);
		Assert.isTrue(decision.getId() != 0);
		Assert.isTrue(this.decisionRepository.exists(decision.getId()));
		this.decisionRepository.delete(decision);
	}

	// Other business methods

	public void flush() {
		this.decisionRepository.flush();
	}
	
	
	public DecisionForm construct(Decision decision) {
		DecisionForm res = new DecisionForm();

		res.setId(decision.getId());
		res.setComment(decision.getComment());
		res.setApproved(decision.getApproved());

		return res;
	}

	public Decision reconstruct(DecisionForm decisionForm, BindingResult binding) {
		Assert.notNull(decisionForm);

		Decision res = new Decision();

		if (decisionForm.getId() != 0)
			res = this.findOne(decisionForm.getId());
		else
			res = this.create();

		res.setComment(decisionForm.getComment());
		res.setApproved(decisionForm.getApproved());
		res.setPrueba(decisionForm.getPrueba());
		
		this.validator.validate(res, binding);

		return res;
	}
	
	

}
