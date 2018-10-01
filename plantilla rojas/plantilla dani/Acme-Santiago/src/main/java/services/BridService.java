package services;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BridRepository;
import domain.Brid;
import domain.User;

@Service
@Transactional
public class BridService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private BridRepository bridRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private AdministratorService administratorService;

	// Constructor ------------------------------------------------------------

	public BridService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Brid create() {
		Brid res;
		User user;
		Date moment;

		res = new Brid();
		user = userService.findByPrincipal();
		moment = new Date(System.currentTimeMillis() - 1000);

		res.setMoment(moment);
		res.setIdentifier(this.generateCode());
		res.setUser(user);

		return res;
	}

	public Collection<Brid> findAll() {

		Collection<Brid> res;
		res = this.bridRepository.findAll();
		return res;
	}

	public Brid findOne(final int id) {
		Assert.isTrue(id != 0);
		Brid res;
		res = this.bridRepository.findOne(id);
		return res;
	}

	public Brid save(final Brid brid) {
		Assert.notNull(brid);
		Brid res;

		// try {
		// this.userService.checkAuthority();
		// Assert.isTrue(brid.getJustification() == null);
		//
		// } catch (Exception e) {
		// this.administratorService.checkAuthority();
		// if(brid.getJustification() == null || brid.getJustification() ==
		// ""){
		// throw new IllegalArgumentException();
		// }
		//
		// }
		if (brid.getApproved() != null) {
			this.administratorService.checkAuthority();
		}
		res = this.bridRepository.save(brid);
		return res;
	}

	public void delete(Brid brid) {
		Assert.notNull(brid);
		Assert.isTrue(brid.getId() != 0);
		Assert.isTrue(this.bridRepository.exists(brid.getId()));

		this.bridRepository.delete(brid);
	}

	// Other business method --------------------------------------------------

	public void flush() {
		this.bridRepository.flush();
	}

	public Collection<Brid> findBridsByRoute(int id) {
		Collection<Brid> res;
		res = this.bridRepository.findBridsByRoute(id);
		return res;
	}

	public Collection<Brid> findBridsWithoutDecision() {
		this.administratorService.checkAuthority();
		Collection<Brid> res;
		res = this.bridRepository.findBridsWithoutDecision();
		return res;
	}

	public Collection<Brid> findBridsByUser(int id) {
		Collection<Brid> res;
		res = this.bridRepository.findBridsByUser(id);
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

	public String generateCode() {
		Date now = new Date();
		final SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
		String fecha = formatter.format(now);
		String yy = fecha.substring(0, 2);
		String mm = fecha.substring(2, 4);
		String dd = fecha.substring(4);

		String www = "";
		char v;
		for (int i = 0; i < 3; i++) {
			final Double d = Math.floor(Math.random() * (122 - 97 + 1) + 97);
			Integer r = d.intValue();
			if (r % 2 == 0) {
				v = (char) r.toString().charAt(0);
			} else {
				v = (char) d.doubleValue();
			}

			www += String.valueOf(v);
		}

		String ww = "";
		char b;
		for (int i = 0; i < 2; i++) {
			final Double d = Math.floor(Math.random() * (122 - 97 + 1) + 97);
			Integer r = d.intValue();
			if (r % 2 == 0) {
				b = (char) r.toString().charAt(0);
			} else {
				b = (char) d.doubleValue();
			}

			ww += String.valueOf(b);
		}
		String result = www + "-" + yy + mm + dd + "-" + ww;
		return result;
	}
}
