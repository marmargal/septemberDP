package services;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CambioRepository;
import domain.Cambio;
import domain.User;

@Service
@Transactional
public class CambioService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CambioRepository cambioRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorService administratorService;

	// Constructor ------------------------------------------------------------

	public CambioService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Cambio create() {
		Cambio res;
		User user;
		Date moment;

		res = new Cambio();
		user = userService.findByPrincipal();
		moment = new Date(System.currentTimeMillis() - 1000);

		res.setMoment(moment);
		res.setIdentifier(this.generatedIdentifier());
		res.setUser(user);

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

//		try {
//			this.userService.checkAuthority();
//			Assert.isTrue(cambio.getJustification() == null);
//
//		} catch (Exception e) {
//			this.administratorService.checkAuthority();
//			if(cambio.getJustification() == null || cambio.getJustification() == ""){
//				throw new IllegalArgumentException();
//			}
//
//		}
		res = this.cambioRepository.save(cambio);
		return res;
	}

	public void delete(Cambio cambio) {
		Assert.notNull(cambio);
		Assert.isTrue(cambio.getId() != 0);
		Assert.isTrue(this.cambioRepository.exists(cambio.getId()));

		this.cambioRepository.delete(cambio);
	}

	// Other business method --------------------------------------------------

	public void flush() {
		this.cambioRepository.flush();
	}

	public Collection<Cambio> findCambiosByRoute(int id) {
		Collection<Cambio> res;
		res = this.cambioRepository.findCambiosByRoute(id);
		return res;
	}

	public Collection<Cambio> findCambiosWithoutDecision() {
		this.administratorService.checkAuthority();
		Collection<Cambio> res;
		res = this.cambioRepository.findCambiosWithoutDecision();
		return res;
	}
	
	public Collection<Cambio> findCambiosByUser(int id) {
		Collection<Cambio> res;
		res = this.cambioRepository.findCambiosByUser(id);
		return res;
	}

	public String generatedIdentifier() {
		String identifier;
		String letters;
		// String numbers;
		Random r;

		letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		// numbers = "0123456789";
		r = new Random();

		final Date date = new Date(System.currentTimeMillis() - 1);
		final SimpleDateFormat dt = new SimpleDateFormat("yyMMdd");

		identifier = dt.format(date).toString() + "-";
		for (int i = 0; i < 4; i++)
			identifier = identifier
					+ letters.charAt(r.nextInt(letters.length()));
		// for (int i = 0; i < 2; i++)
		// identifier = identifier +
		// numbers.charAt(r.nextInt(numbers.length()));

		return identifier;
	}

}
