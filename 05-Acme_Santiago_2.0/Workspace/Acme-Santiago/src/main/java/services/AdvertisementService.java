package services;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Advertisement;

import repositories.AdvertisementRepository;

@Service
@Transactional
public class AdvertisementService {

	// Managed repository
	
	@Autowired
	private AdvertisementRepository advertisementRepository;
	
	// Supporting services
	
	
	@Autowired
	private AdministratorService administratorService;
	
	// Constructors
	
	public AdvertisementService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Advertisement create(){
		Advertisement res = new Advertisement();
		return res;
	}
	
	public Collection<Advertisement> findAll(){
		Collection<Advertisement> res;
		res = this.advertisementRepository.findAll();
		return res;
	}
	
	public Advertisement findOne(final int id){
		Assert.isTrue(id!=0);
		Advertisement res;
		res = this.advertisementRepository.findOne(id);
		return res;
	}
	
	public Advertisement save(final Advertisement advertisement){
		Assert.notNull(advertisement);
		Advertisement res;
		res = this.advertisementRepository.save(advertisement);
		return res;
	}
	
	public void delete(Advertisement advertisement){
		Assert.notNull(advertisement);
		Assert.isTrue(advertisement.getId()!=0);
		Assert.isTrue(this.advertisementRepository.exists(advertisement.getId()));
		this.administratorService.checkAuthority();
		this.advertisementRepository.delete(advertisement);
	}
	
	// Other business methods
}
