package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	private CountryService countryService;

	@Autowired
	private Validator validator;

	// Constructors

	public LawService() {
		super();
	}

	// Simple CRUD methods

	public Law create() {
		Law res;
		res = new Law();

		List<Requirement> requirement;
		List<Law> laws;
		Law lawParent;

		requirement = new ArrayList<Requirement>();
		laws = new ArrayList<Law>();
		lawParent= new Law();

		res.setRequirement(requirement);
		res.setLaws(laws);
		res.setLawParent(lawParent);


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
		Assert.notNull(law);
		Law res;
		List<Law> laws = new ArrayList<Law>();
		

		res = lawRepository.save(law);
		Collection<Country> ee = this.countryService.findCountryByLawId(law
				.getId());
		Country countryBueno = new Country();
		for (Country l : ee) {
			countryBueno = l;
		}

		laws.add(res);
		countryBueno.setLaw(laws);

		Assert.notNull(res);
		return res;
	}

	public void delete(Law law) {
		Assert.notNull(law);
		Assert.isTrue(law.getId() != 0);
		Assert.isTrue(lawRepository.exists(law.getId()));
		lawRepository.delete(law);
	}

	public LawForm construct(Law law) {
		LawForm res = new LawForm();

		res.setAbrogationTime(law.getAbrogationTime());
		res.setEnactmentDate(law.getEnactmentDate());
		res.setText(law.getText());
		res.setTitle(law.getTitle());

		res.setLaws(law.getLaws());
		res.setLawParent(law.getLawParent());
		res.setRequirement(law.getRequirement());


		return res;
	}

	public Law reconstruct(LawForm lawForm, BindingResult binding) {
		Assert.notNull(lawForm);
		Law res = new Law();

		res.setAbrogationTime(lawForm.getAbrogationTime());
		res.setEnactmentDate(lawForm.getEnactmentDate());
		res.setText(lawForm.getText());
		res.setTitle(lawForm.getTitle());

		res.setLaws(lawForm.getLaws());
		res.setLawParent(lawForm.getLawParent());
		res.setRequirement(lawForm.getRequirement());


		this.validator.validate(res, binding);

		return res;
	}

}
