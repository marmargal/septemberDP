package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PlatformRepository;
import domain.Platform;
import domain.Satellite;

@Service
@Transactional
public class PlatformService {

	// Managed repository

	@Autowired
	private PlatformRepository platformRepository;

	// Suporting services

	// Constructors

	public PlatformService() {
		super();
	}

	// Simple CRUD methods

	public Platform create() {
		Platform res;
		res = new Platform();

		String name = "name";
		String description = "description";
		
		Satellite satellite = new Satellite();
		
		res.setName(name);
		res.setDescription(description);
		res.setSatellite(satellite);
		
		return res;
	}
	
	public Collection<Platform> findAll() {
		Collection<Platform> res;
		res = this.platformRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Platform findOne(final int id) {
		Assert.isTrue(id != 0);
		Platform res;
		res = this.platformRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Platform save(final Platform platform) {
		Assert.notNull(platform);
		Platform res;
		res = this.platformRepository.save(platform);
		return res;
	}
	
	public void delete(Platform platform) {
		Assert.notNull(platform);
		Assert.isTrue(platform.getId() != 0);
		Assert.isTrue(this.platformRepository.exists(platform.getId()));
		this.platformRepository.delete(platform);
	}

	// Other business method --------------------------------------------------

}
