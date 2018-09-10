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
		Assert.notNull(antenna);
		if(antenna.getId() != 0){
			this.checkAntennaIsOfUserLogged(antenna.getId());
		}
		Antenna res = new Antenna();;
		res = this.antennaRepository.save(antenna);
		return res;
	}

	public void delete(Antenna antenna) {
		this.userService.checkAuthority();
		this.checkAntennaIsOfUserLogged(antenna.getId());
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
		
		String serialNumber = String.valueOf(antenna.getSerialNumber());
		String azimuth = String.valueOf(antenna.getAzimuth());
		String elevation = String.valueOf(antenna.getElevation()); 
		String quality = String.valueOf(antenna.getQuality()); 
		
		res.setId(antenna.getId());
		res.setSerialNumber(serialNumber);
		res.setModel(antenna.getModel());
		gpsCoordinate.setLatitude(latitude);
		gpsCoordinate.setLongitude(longitude);
		res.setAzimuth(azimuth);
		res.setElevation(elevation);antenna.getQuality();
		res.setQuality(quality);
		res.setSatellite(antenna.getSatellite());
		
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
		Double latitude = Double.parseDouble(antennaForm.getLatitude());
		Double longitude = Double.parseDouble(antennaForm.getLongitude());
		gpsCoordinate.setLatitude(latitude);
		gpsCoordinate.setLongitude(longitude);
		
		Integer serialNumber = Integer.parseInt(antennaForm.getSerialNumber());
		Double azimuth = Double.parseDouble(antennaForm.getAzimuth());
		Double elevation = Double.parseDouble(antennaForm.getElevation()); 
		Double quallity = Double.parseDouble(antennaForm.getQuality()); 
		
		res.setSerialNumber(serialNumber);
		res.setModel(antennaForm.getModel());
		res.setCoordinates(gpsCoordinate);
		res.setAzimuth(azimuth);
		res.setElevation(elevation);
		res.setQuality(quallity);
		res.setSatellite(antennaForm.getSatellite());

		this.validator.validate(res, binding);

		return res;
	}
	
	public Collection<Antenna> findAntennasByUser(int userId){
		Assert.isTrue(userService.findByPrincipal().getId() == userId);
		Collection<Antenna> antennas = new ArrayList<Antenna>();
		antennas = this.antennaRepository.findAntennasByUser(userId);
		return antennas;
	}
	public void checkAntennaIsOfUserLogged(int antennaId){
		Collection<Antenna> antennasOfUser = new ArrayList<Antenna>();
		Antenna antenna = new Antenna();
		User user = new User();
		
		user = this.userService.findByPrincipal();
		antenna = this.findOne(antennaId);
		
		antennasOfUser = user.getAntennas();
		Assert.isTrue(antennasOfUser.contains(antenna));
	}
	
}
