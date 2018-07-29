package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SatelliteRepository;
import domain.Satellite;

@Service
@Transactional
public class SatelliteService {

	// Managed repository

	@Autowired
	private SatelliteRepository satelliteRepository;

	// Suporting services

	// Constructors

	public SatelliteService() {
		super();
	}

	// Simple CRUD methods

	public Satellite create() {
		Satellite res;
		res = new Satellite();

		String name = "Satellite";
		String description = "description";

		res.setName(name);
		res.setDescription(description);

		return res;
	}

	public Collection<Satellite> findAll() {
		Collection<Satellite> res;
		res = this.satelliteRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Satellite findOne(final int id) {
		Assert.isTrue(id != 0);
		Satellite res;
		res = this.satelliteRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Satellite save(final Satellite satellite) {
		Assert.notNull(satellite);
		Satellite res;
		res = this.satelliteRepository.save(satellite);
		return res;
	}

	public void delete(Satellite satellite) {
		Assert.notNull(satellite);
		Assert.isTrue(satellite.getId() != 0);
		Assert.isTrue(this.satelliteRepository.exists(satellite.getId()));
		this.satelliteRepository.delete(satellite);
	}

}
