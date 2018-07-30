package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AntennaRepository;
import domain.Antenna;
import domain.GpsCoordinate;
import domain.Satellite;
import domain.User;

@Service
@Transactional
public class AntennaService {

	// Managed repository

	@Autowired
	private AntennaRepository antennaRepository;

	// Suporting services

	@Autowired
	private UserService userService;

	// Constructors

	public AntennaService() {
		super();
	}

	// Simple CRUD methods

	public Antenna create() {
		Antenna res;
		res = new Antenna();

		Integer serialNumber = 1;
		String model = "model";

		GpsCoordinate gps = new GpsCoordinate();
		gps.setLatitude(100.);
		gps.setLongitude(200.);

		Double azimuth = 250.;
		Double elevation = 50.;
		Double quality = 90.;

		User user;
		user = userService.findByPrincipal();

		Satellite satellite;
		satellite = new Satellite();

		res.setSerialNumber(serialNumber);
		res.setModel(model);
		res.setCoordinates(gps);
		res.setAzimuth(azimuth);
		res.setElevation(elevation);
		res.setQuality(quality);
		res.setUser(user);
		res.setSatellite(satellite);

		return res;
	}

	public Collection<Antenna> findAll() {
		Collection<Antenna> res;
		res = this.antennaRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Antenna findOne(final int id) {
		Assert.isTrue(id != 0);
		Antenna res;
		res = this.antennaRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Antenna save(final Antenna antenna) {
		this.userService.checkAuthority();
		Assert.isTrue(antenna.getUser().equals(
				this.userService.findByPrincipal()));
		Assert.notNull(antenna);
		Antenna res;
		res = this.antennaRepository.save(antenna);
		return res;
	}

	public void delete(Antenna antenna) {
		Assert.notNull(antenna);
		Assert.isTrue(antenna.getId() != 0);
		Assert.isTrue(this.antennaRepository.exists(antenna.getId()));
		this.antennaRepository.delete(antenna);
	}

	// Other business method --------------------------------------------------

}
