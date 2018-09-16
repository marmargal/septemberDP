package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.CambioRepository;
import security.Authority;
import security.LoginService;
import domain.Amenity;
import domain.Cambio;
import domain.Innkeeper;
import forms.AmenityForm;

@Service
@Transactional
public class CambioService {

	// Managed repository
	@Autowired
	private CambioRepository cambioRepository;

	@Autowired
	private UserService userService;

	// Supporting services

	// Constructors
	public CambioService() {
		super();
	}

	// Simple CRUD methods

	public Cambio create() {

		// revisar identifier
		Cambio res = new Cambio();
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1000);
		res.setMoment(moment);
		Integer yearNum = moment.getYear() % 100;
		String year = yearNum.toString();
		Integer monthNum = moment.getMonth();

		String month = monthNum.toString();

		Integer dayhNum = moment.getDay();
		String day = dayhNum.toString();
		String identifier = year + "-" + month + "-" + day;

		res.setIdentifier(identifier);
		res.setUser(this.userService.findByPrincipal());

		return res;
	}

	public Collection<Cambio> findAll() {

		Collection<Cambio> res;
		res = this.cambioRepository.findAll();
		return res;
	}

	public Cambio findOne(final int id) {
		Assert.isTrue(id != 0);
		Cambio res;
		res = this.cambioRepository.findOne(id);
		return res;
	}

	public Cambio save(final Cambio cambio) {
		Assert.notNull(cambio);
		Cambio res;
		if (cambio.getId() == 0) {
			cambio.setUser(this.userService.findByPrincipal());
		}
		Date moment;
		moment = new Date(System.currentTimeMillis() - 1000);
		cambio.setMoment(moment);
		Integer yearNum = moment.getYear() % 100;
		String year = yearNum.toString();
		Integer monthNum = moment.getMonth();

		String month = monthNum.toString();

		Integer dayhNum = moment.getDay();
		String day = dayhNum.toString();
		String identifier = year + "-" + month + "-" + day;

		cambio.setIdentifier(identifier);
		res = this.cambioRepository.save(cambio);

		return res;
	}

	public void delete(Cambio cambio) {
		Assert.notNull(cambio);
		Assert.isTrue(cambio.getId() != 0);
		Assert.isTrue(this.cambioRepository.exists(cambio.getId()));

		this.cambioRepository.delete(cambio);
	}

	public void flush() {
		this.cambioRepository.flush();
	}

	public Collection<Cambio> cambiosWithoutDecision() {
		return this.cambioRepository.cambiosWithoutDecision();
	}
}
