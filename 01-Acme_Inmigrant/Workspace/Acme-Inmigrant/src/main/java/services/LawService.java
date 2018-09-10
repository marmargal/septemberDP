package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.LawRepository;
import domain.Country;
import domain.Law;
import domain.Requirement;
import forms.LawForm;

@Service
@Transactional
public class LawService {

	// Managed repository

	@Autowired
	private LawRepository lawRepository;

	// Suporting services
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private RequirementService requirementService;

	@Autowired
	private Validator validator;

	// Constructors

	public LawService() {
		super();
	}

	// Simple CRUD methods

	public Law create() {
		this.administratorService.checkAuthority();
		Law res;
		res = new Law();

		List<Requirement> requirement;
		List<Law> laws;

		requirement = new ArrayList<Requirement>();
		laws = new ArrayList<Law>();

		res.setRequirement(requirement);
		res.setLaws(laws);

		return res;
	}

	public Collection<Law> findAll() {
		Collection<Law> res;
		res = lawRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Law findOne(int lawId) {
		Assert.isTrue(lawId != 0);
		Law res;
		res = lawRepository.findOne(lawId);
		Assert.notNull(res);
		return res;
	}

	public Law save(Law law) {
		this.administratorService.checkAuthority();
		Assert.notNull(law);
		Law res;

		res = lawRepository.save(law);

		Assert.notNull(res);
		return res;
	}

	public void delete(Law law) {
		this.administratorService.checkAuthority();
		Assert.notNull(law);
		Assert.isTrue(law.getId() != 0);
		
		Law lawParent = new Law();
		List<Law> lawsSonsOfParent = new ArrayList<Law>();
		List<Law> lawsSons = new ArrayList<Law>();
		Country country = new Country();
		List<Law> countryLaws = new ArrayList<Law>();
		Collection<Requirement> requirementsOfLaw = new ArrayList<Requirement>();
		
		// Actualizando Laws hijas
		lawsSons = law.getLaws();
		if(!lawsSons.isEmpty()){
			for(int i=0; i<lawsSons.size(); i++){
				this.delete(lawsSons.get(i));
			}
		}	
		
		// Actualizando lawParent
		lawParent = law.getLawParent();
		if (lawParent != null){
			lawsSonsOfParent = lawParent.getLaws();
			lawsSonsOfParent.remove(law);
			lawParent.setLaws(lawsSonsOfParent);
			this.save(lawParent);
		}
		
		// Actualizando Country
		country = law.getCountry();
		countryLaws = country.getLaw();
		countryLaws.remove(law);
		country.setLaw(countryLaws);
		this.countryService.save(country);
		
		//Actualizando Requirement
		requirementsOfLaw = law.getRequirement();
		if(!requirementsOfLaw.isEmpty()){
			for(Requirement requirement: requirementsOfLaw){
				this.requirementService.delete(requirement);
			}
		}
		
		lawRepository.delete(law);
	}

	public LawForm construct(Law law) {
		LawForm res = new LawForm();

		res.setId(law.getId());
		res.setAbrogationTime(law.getAbrogationTime());
		res.setEnactmentDate(law.getEnactmentDate());
		res.setText(law.getText());
		res.setTitle(law.getTitle());

		res.setLaws(law.getLaws());
		res.setLawParent(law.getLawParent());
		res.setRequirement(law.getRequirement());
		res.setCountry(law.getCountry());

		return res;
	}

	public Law reconstruct(LawForm lawForm, BindingResult binding) {
		Assert.notNull(lawForm);
		Law res = new Law();

		if (lawForm.getId() != 0)
			res = this.findOne(lawForm.getId());
		else
			res = this.create();

		res.setId(lawForm.getId());
		if (lawForm.getAbrogationTime() == null) {
			res.setAbrogationTime(null);
		} else {
			res.setAbrogationTime(lawForm.getAbrogationTime());
		}

		res.setEnactmentDate(lawForm.getEnactmentDate());
		res.setText(lawForm.getText());
		res.setTitle(lawForm.getTitle());

		res.setLaws(lawForm.getLaws());
		if (lawForm.getLawParent() == null) {
			res.setLawParent(null);
		} else {
			res.setLawParent(lawForm.getLawParent());
		}

		res.setRequirement(lawForm.getRequirement());
		res.setCountry(lawForm.getCountry());

		this.validator.validate(res, binding);
		return res;
	}
	
	public void flush() {
		this.lawRepository.flush();
	}

}
