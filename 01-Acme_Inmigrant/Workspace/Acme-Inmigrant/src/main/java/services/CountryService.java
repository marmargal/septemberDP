package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CountryRepository;
import domain.Country;
import domain.Law;
import domain.Report;

@Service
@Transactional
public class CountryService {

	// Managed repository

	@Autowired
	private CountryRepository countryRepository;

	// Suporting services

	// Constructors

	public CountryService() {
		super();
	}

	// Simple CRUD methods

	public Country create() {
		Country res = new Country();

		String name = "name";
		String isoCode = "isoCode";
		String flag = "flag";
		String link = "link";
		List<Law> law = new ArrayList<Law>();

		res.setName(name);
		res.setIsoCode(isoCode);
		res.setFlag(flag);
		res.setLink(link);
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

}
