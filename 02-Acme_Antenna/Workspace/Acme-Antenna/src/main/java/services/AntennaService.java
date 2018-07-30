package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AntennaRepository;
import domain.Antenna;
import domain.GpsCoordinate;
import domain.Satellite;
import domain.User;
import forms.AntennaForm;

@Service
@Transactional
public class AntennaService {

	// Managed repository

	@Autowired
	private AntennaRepository antennaRepository;

	// Suporting services

	@Autowired
	private UserService userService;
	
	@Autowired
	private Validator validator;

	// Constructors

	public AntennaService() {
		super();
	}

	// Simple CRUD methods

	public Antenna create() {
		this.userService.checkAuthority();
		
		Antenna res = new Antenna();
		Satellite satellite = new Satellite();
		User user = new User();;
		
		user = userService.findByPrincipal();

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
		Assert.isTrue(antenna.getUser().equals(this.userService.findByPrincipal()));
		Assert.notNull(antenna);
		Antenna res = new Antenna();;
		res = this.antennaRepository.save(antenna);
		return res;
	}

	public void delete(Antenna antenna) {
		this.userService.checkAuthority();
		Assert.notNull(antenna);
		Assert.isTrue(antenna.getId() != 0);
		Assert.isTrue(this.antennaRepository.exists(antenna.getId()));
		this.antennaRepository.delete(antenna);
	}

	// Other business method --------------------------------------------------
	
	public AntennaForm construct(Antenna antenna){
		AntennaForm res = new AntennaForm();
		
		GpsCoordinate gpsCoordinate = new GpsCoordinate();
		gpsCoordinate = antenna.getCoordinates();
		double latitude = gpsCoordinate.getLatitude();
		double longitude = gpsCoordinate.getLongitude();
		
		res.setId(antenna.getId());
		res.setSerialNumber(antenna.getSerialNumber());
		res.setModel(antenna.getModel());
		gpsCoordinate.setLatitude(latitude);
		gpsCoordinate.setLongitude(longitude);
		res.setAzimuth(antenna.getAzimuth());
		res.setElevation(antenna.getElevation());
		res.setQuality(antenna.getQuality());
		
		return res;
	}
	
	public Antenna reconstruct(AntennaForm antennaForm, BindingResult binding){
		Assert.notNull(antennaForm);
		
		Antenna res = new Antenna();

		if (antennaForm.getId() != 0)
			res = this.findOne(antennaForm.getId());
		else
			res = this.create();
		
		GpsCoordinate gpsCoordinate = new GpsCoordinate();
		double latitude = antennaForm.getLatitude();
		double longitude = antennaForm.getLongitude();
		gpsCoordinate.setLatitude(latitude);
		gpsCoordinate.setLongitude(longitude);
		
		res.setSerialNumber(antennaForm.getSerialNumber());
		res.setModel(antennaForm.getModel());
		res.setCoordinates(gpsCoordinate);
		res.setAzimuth(antennaForm.getAzimuth());
		res.setElevation(antennaForm.getElevation());
		res.setQuality(antennaForm.getQuality());

		this.validator.validate(res, binding);

		return res;
	}
	
	public Collection<Antenna> findAntennasByUser(int userId){
		Collection<Antenna> antennas = new ArrayList<Antenna>();
		antennas = this.antennaRepository.findAntennasByUser(userId);
		return antennas;
	}
	
}
