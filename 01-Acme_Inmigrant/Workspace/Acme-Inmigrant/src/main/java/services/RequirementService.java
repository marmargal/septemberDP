package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RequirementRepository;
import domain.Requirement;
import forms.RequirementForm;

@Service
@Transactional
public class RequirementService {

	// Managed repository

	@Autowired
	private RequirementRepository requirementRepository;

	// Suporting services

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private LawService lawService;
	
	@Autowired
	private Validator		validator;

	
	// Constructors

	public RequirementService() {
		super();
	}

	// Simple CRUD methods

	public Requirement create() {
		this.administratorService.checkAuthority();
		Requirement res = new Requirement();

		return res;
	}

	public Collection<Requirement> findAll() {
		Collection<Requirement> res;
		res = requirementRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Requirement findOne(int requirementId) {
		Assert.isTrue(requirementId != 0);
		Requirement res;
		res = requirementRepository.findOne(requirementId);
		Assert.notNull(res);
		return res;
	}

	public Requirement save(Requirement requirement) {
		this.administratorService.checkAuthority();
		Requirement res;
		res = requirementRepository.save(requirement);
		return res;
	}

	public void delete(Requirement requirement) {
		Assert.notNull(requirement);
		Assert.isTrue(requirement.getId() != 0);
		Assert.isTrue(requirementRepository.exists(requirement.getId()));
		requirementRepository.delete(requirement);
	}

	public Collection<Requirement> findRequirementByVisaId(int visaId) {
		Collection<Requirement> requirements = this.requirementRepository.findRequirementByVisaId(visaId);
		return requirements;
	}
	
	public RequirementForm construct(Requirement requirement){
		RequirementForm res = new RequirementForm();
		
		res.setId(requirement.getId());
		res.setTitle(requirement.getTitle());
		res.setDescription(requirement.getDescription());
		res.setAbrogated(requirement.getAbrogated());
		res.setLawId(requirement.getLaw().getId());
		
		return res;
	}
	
	public Requirement reconstruct(RequirementForm requirementForm, BindingResult binding){
		Assert.notNull(requirementForm);
		
		Requirement res = new Requirement();

		if (requirementForm.getId() != 0)
			res = this.findOne(requirementForm.getId());
		else 
			res = this.create();
		
		res.setTitle(requirementForm.getTitle());
		res.setDescription(requirementForm.getDescription());
		res.setAbrogated(requirementForm.getAbrogated());
		res.setLaw(this.lawService.findOne(requirementForm.getLawId()));
		
		this.validator.validate(res, binding);

		return res;
	}

	public void flush() {
		this.requirementRepository.flush();
	}
	
}
