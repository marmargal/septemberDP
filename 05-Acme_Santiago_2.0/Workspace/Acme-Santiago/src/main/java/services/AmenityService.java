package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Amenity;

import repositories.AmenityRepository;

@Service
@Transactional
public class AmenityService {

	// Managed repository 
	@Autowired
	private AmenityRepository amenityRepository;
	
	// Supporting services
	
	
	// Constructors
	public AmenityService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Amenity create(){
		Amenity res = new Amenity();

		Collection<String> pictures = new ArrayList<String>();
		res.setPictures(pictures);
		
		return res;
	}
	
	public Collection<Amenity> findAll(){
		Collection<Amenity> res;
		res = this.amenityRepository.findAll();
		return res;
	}
	
	public Amenity findOne(final int id){
		Assert.isTrue(id!=0);
		Amenity res;
		res = this.amenityRepository.findOne(id);
		return res;
	}
	
	public Amenity save(final Amenity amenity){
		Assert.notNull(amenity);
		Amenity res;
		res = this.amenityRepository.save(amenity);
		return res;
	}
	
	public void delete(Amenity amenity){
		Assert.notNull(amenity);
		Assert.isTrue(amenity.getId()!=0);
		Assert.isTrue(this.amenityRepository.exists(amenity.getId()));
		this.amenityRepository.delete(amenity);
	}
}
