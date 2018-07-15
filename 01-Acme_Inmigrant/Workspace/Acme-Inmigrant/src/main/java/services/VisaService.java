package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
	
	// Suporting services
	
	// Constructors
	
	public VisaService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Visa create(){
		Visa res;
		res = new Visa();
		
		String classes = "class";
		String description = "description";
		String price = "3 Euros";
		
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
	
	public Collection<Visa> findAll(){
		Collection<Visa> res;
		res = visaRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Visa findOne(int visaId){
		Assert.isTrue(visaId != 0);
		Visa res;
		res = visaRepository.findOne(visaId);
		Assert.notNull(res);
		return res;
	}
	
	public Visa save(Visa visa){
		Visa res;
		res = visaRepository.save(visa);
		return res;
	}
	
	public void delete(Visa visa){
		Assert.notNull(visa);
		Assert.isTrue(visa.getId() != 0);
		Assert.isTrue(visaRepository.exists(visa.getId()));
		visaRepository.delete(visa);
	}
}
