package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AmenityRepository;
import security.Authority;
import security.LoginService;
import domain.Amenity;
import domain.Inn;
import domain.Innkeeper;
import forms.AmenityForm;

@Service
@Transactional
public class AmenityService {

	// Managed repository
	@Autowired
	private AmenityRepository amenityRepository;

	// Supporting services

	@Autowired
	private InnkeeperService innkeeperService;

	@Autowired
	private Validator validator;

	// Constructors
	public AmenityService() {
		super();
	}

	// Simple CRUD methods

	public Amenity create() {
		Amenity res = new Amenity();
		Inn inn = new Inn();

		Collection<String> pictures = new ArrayList<String>();
		res.setPictures(pictures);
		res.setInn(inn);

		return res;
	}

	public Collection<Amenity> findAll() {
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Authority innkeeper = new Authority();
		innkeeper.setAuthority("INNKEEPER");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(innkeeper)
				|| authority.contains(admin));
		Collection<Amenity> res;
		res = this.amenityRepository.findAll();
		return res;
	}

	public Amenity findOne(final int id) {
		Assert.isTrue(id != 0);
		Amenity res;
		res = this.amenityRepository.findOne(id);
		return res;
	}

	public Amenity save(final Amenity amenity) {
		Assert.notNull(amenity);
		innkeeperService.checkAuthority();
		Amenity res;
		if (amenity.getId() != 0) {
			Assert.isTrue(amenity.getInn().getInnkeeper()
					.equals(this.innkeeperService.findByPrincipal()));
		}
		res = this.amenityRepository.save(amenity);
		if (amenity.getId() == 0) {
			Innkeeper innkeeper = this.innkeeperService.findByPrincipal();
			Collection<Amenity> amenities = innkeeper.getAmenities();
			amenities.add(res);
			innkeeper.setAmenities(amenities);
			this.innkeeperService.save(innkeeper);
		}

		return res;
	}

	public void delete(Amenity amenity) {
		innkeeperService.checkAuthority();
		Assert.notNull(amenity);
		Assert.isTrue(amenity.getId() != 0);
		Assert.isTrue(this.amenityRepository.exists(amenity.getId()));
		this.amenityRepository.delete(amenity);
	}

	public Collection<Amenity> findAmenitiesByInn(int innId) {
		Collection<Amenity> amenities = new ArrayList<Amenity>();

		try {

			amenities = this.amenityRepository.findAmenitiesByInn(innId);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return amenities;

	}

	public AmenityForm construct(Amenity amenity) {
		AmenityForm amenityForm = new AmenityForm();

		amenityForm.setId(amenity.getId());
		amenityForm.setInn(amenity.getInn());
		amenityForm.setName(amenity.getName());
		amenityForm.setDescription(amenity.getDescription());
		amenityForm.setPictures(amenity.getPictures());

		return amenityForm;
	}

	public Amenity reconstruct(final AmenityForm amenityForm,
			final BindingResult binding) {
		Amenity amenity;

		if (amenityForm.getId() != 0) {
			amenity = this.findOne(amenityForm.getId());
		} else
			amenity = this.create();

		amenity.setId(amenityForm.getId());
		amenity.setInn(amenityForm.getInn());
		amenity.setName(amenityForm.getName());
		amenity.setDescription(amenityForm.getDescription());
		amenity.setPictures(amenityForm.getPictures());

		this.validator.validate(amenity, binding);
		return amenity;
	}

	public void flush() {
		this.amenityRepository.flush();
	}

}
