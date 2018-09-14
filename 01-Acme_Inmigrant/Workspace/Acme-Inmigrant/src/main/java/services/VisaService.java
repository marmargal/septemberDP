package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.VisaRepository;
import domain.Application;
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
	private AdministratorService administratorService;
	
	@Autowired
	private ApplicationService applicationService;

	// Suporting services

	// Constructors

	public VisaService() {
		super();
	}

	// Simple CRUD methods

	public Visa create() {
		this.administratorService.checkAuthority();
		Visa res;
		res = new Visa();

		Country country;
		Category category;

		country = new Country();
		category = new Category();

		res.setCountry(country);
		res.setCategory(category);
		res.setCreditCard(null);

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
		this.administratorService.checkAuthority();
		Visa res;
		res = visaRepository.save(visa);
		return res;
	}

	public void delete(Visa visa) {
		Assert.notNull(visa);
		Assert.isTrue(visa.getId() != 0);
		Assert.isTrue(visaRepository.exists(visa.getId()));
		
		Collection<Application> applications = this.applicationService.findApplicationsByVisa(visa.getId());
		for(Application application: applications){
			application.setVisa(null);
		}
		visaRepository.delete(visa);
	}
	
	public Collection<Visa> searchVisa(String criteria){
		Collection<Visa> res = new ArrayList<Visa>();
		res.addAll(visaRepository.searchVisa(criteria));
		return res;
	}


	
	public Collection<Visa> findVisasByCategory(int categoryId) {
		Collection<Visa> res;
		res = new ArrayList<Visa>();
		Assert.isTrue(categoryId != 0);
		res.addAll(visaRepository.findVisasByCategories(categoryId));

		return res;
	}
	
	public Collection<Visa> findVisasByCountry(int countryId) {
		Collection<Visa> res;
		res = new ArrayList<Visa>();
		Assert.isTrue(countryId != 0);
		res.addAll(visaRepository.findVisasByCountry(countryId));

		return res;
	}
	
	public boolean checkCreditCard(Visa visa) {
		boolean isValid = true;
		if(visa.getPrice() > 0){
			if(visa.getCreditCard().getBrandName().isEmpty() ||
			   visa.getCreditCard().getCvv() == null ||
			   visa.getCreditCard().getExpirationMonth() == null ||
			   visa.getCreditCard().getExpirationYear() == null ||
			   visa.getCreditCard().getHolderName().isEmpty() ||
			   visa.getCreditCard().getNumber().isEmpty()){
				
					isValid = false;
			}
		}

		return isValid;
	}
	
	public void flush() {
		this.visaRepository.flush();
	}

}
