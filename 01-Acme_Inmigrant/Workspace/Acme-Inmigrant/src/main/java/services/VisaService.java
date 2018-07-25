package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CountryRepository;
import repositories.VisaRepository;
import domain.Category;
import domain.Country;
import domain.Visa;

@Service
@Transactional
public class VisaService {

	// Managed repository

	@Autowired
	private VisaRepository visaRepository;
	
	@Autowired
	private CountryRepository countryRepository;

	// Suporting services

	// Constructors

	public VisaService() {
		super();
	}

	// Simple CRUD methods

	public Visa create() {
		Visa res;
		res = new Visa();

		String classes = "class";
		String description = "description";
		int price = 3;

		Country country;
		Category category;

		country = new Country();
		category = new Category();

		res.setClasses(classes);
		res.setDescription(description);
		res.setPrice(price);
		res.setCountry(country);
		res.setCategory(category);

		return res;
	}

	public Collection<Visa> findAll() {
		Collection<Visa> res;
		res = visaRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Visa findOne(int visaId) {
		Assert.isTrue(visaId != 0);
		Visa res;
		res = visaRepository.findOne(visaId);
		Assert.notNull(res);
		return res;
	}

	public Visa save(Visa visa) {
		Visa res;
		res = visaRepository.save(visa);
		return res;
	}

	public void delete(Visa visa) {
		Assert.notNull(visa);
		Assert.isTrue(visa.getId() != 0);
		Assert.isTrue(visaRepository.exists(visa.getId()));
		visaRepository.delete(visa);
	}
	
	public Collection<Visa> searchVisa(String criteria){
		Collection<Visa> res = new ArrayList<Visa>();
		res.addAll(visaRepository.searchVisa(criteria));
		return res;
	}

	private double conversionToEuro(String currency, double price) {
		double res = 0.;
		if (currency.equals("dolar")) {
			res = price * 0.86;
		} else if (currency.equals("pound")) {
			res = price * 1.112;
		} else {
			res = price;
		}

		return res;
	}

	private double conversionToOther(String currency, double price) {
		double res = 0.;
		if (currency.equals("dolar")) {
			res = price * 1.17;
		} else if (currency.equals("pound")) {
			res = price * 0.89;
		} else {
			res = price;
		}

		return res;
	}
	
	public Collection<Visa> findVisasByCategory(int categoryId) {
		Collection<Visa> res;
		res = new ArrayList<Visa>();
		Assert.isTrue(categoryId != 0);
		res.addAll(visaRepository.findVisasByCategories(categoryId));

		return res;
	}

}
