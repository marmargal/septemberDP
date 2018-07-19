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

import repositories.CountryRepository;
import domain.Country;
import domain.Law;
import forms.CountryForm;

@Service
@Transactional
public class CountryService {

	// Managed repository

	@Autowired
	private CountryRepository countryRepository;

	// Suporting services

	@Autowired
	private Validator		validator;

	// Constructors

	public CountryService() {
		super();
	}

	// Simple CRUD methods

	public Country create() {
		Country res = new Country();

		List<Law> law = new ArrayList<Law>();

		res.setLaw(law);

		return res;

	}

	public Collection<Country> findAll() {
		Collection<Country> res;
		res = countryRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Country findOne(int countryId) {
		Assert.isTrue(countryId != 0);
		Country res;
		res = countryRepository.findOne(countryId);
		Assert.notNull(res);
		return res;
	}

	public Country save(Country country) {
		Country res;
		res = countryRepository.save(country);
		return res;
	}

	public void delete(Country country) {
		Assert.notNull(country);
		Assert.isTrue(country.getId() != 0);
		Assert.isTrue(countryRepository.exists(country.getId()));
		countryRepository.delete(country);
	}

	// Other business methods

	public Collection<Country> findCountryByLawId(int lawId) {
		Collection<Country> res = new ArrayList<Country>();

		res.addAll(countryRepository.findCountryByLawId(lawId));
		Assert.notNull(res);
		return res;
	}

	public CountryForm construct(Country country){
		CountryForm res = new CountryForm();
		
		res.setId(country.getId());
		res.setName(country.getIsoCode());
		res.setFlag(country.getFlag());
		res.setLink(country.getLink());
		
		return res;
	}
	
	public Country reconstruct(CountryForm countryForm, BindingResult binding){
		Assert.notNull(countryForm);
		
		Country res = new Country();

		if (countryForm.getId() != 0)
			res = this.findOne(countryForm.getId());
		else
			res = this.create();
		
		res.setName(countryForm.getName());
		res.setIsoCode(countryForm.getIsoCode());
		res.setFlag(countryForm.getFlag());
		res.setLink(countryForm.getLink());

		this.validator.validate(res, binding);

		return res;
	}
	
	
}
