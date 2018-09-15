package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ChirpRepository;
import security.Authority;
import security.LoginService;
import domain.Chirp;
import domain.User;

@Service
@Transactional
public class ChirpService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ChirpRepository chirpRepository;

	@Autowired
	private AdministratorService administratorService;
	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private Validator validator;

	// Constructor ------------------------------------------------------------

	public ChirpService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Chirp create() {
		this.userService.checkAuthority();
		User user;
		Chirp result;
		Date moment;

		result = new Chirp();
		moment = new Date(System.currentTimeMillis() - 1000);
		user = userService.findByPrincipal();
		result.setMoment(moment);
		result.setUser(user);

		return result;
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> res;
		res = this.chirpRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Chirp findOne(int id) {
		Assert.isTrue(id != 0);
		Chirp res;
		res = this.chirpRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Chirp save(Chirp chirp) {
		Assert.notNull(chirp);
		Collection<String> tabooWords = new ArrayList<String>();
		tabooWords = configurationService.findTabooWords();
		for (String s : tabooWords) {

			if (chirp.getTitle().toLowerCase().contains(s.toLowerCase())
					|| chirp.getText().toLowerCase().contains(s.toLowerCase())) {
				chirp.setTaboo(true);
			} else {
				chirp.setTaboo(false);

			}
		}
		Chirp res;
		res = this.chirpRepository.save(chirp);
		return res;
	}

	public void delete(Chirp chirp) {
		this.administratorService.checkAuthority();
		Assert.notNull(chirp);
		Assert.isTrue(chirp.getId() != 0);
		Assert.isTrue(this.chirpRepository.exists(chirp.getId()));
		this.chirpRepository.delete(chirp);
	}

	// Other business method --------------------------------------------------

	public Collection<Chirp> findChirpByUser(int id) {
		Collection<Chirp> res;
		res = this.chirpRepository.findChirpByUser(id);
		return res;
	}

	public void checkTabooWords() {
		Collection<String> tabooWords = new ArrayList<String>();
		tabooWords = configurationService.findTabooWords();

		Collection<Chirp> chirps = new ArrayList<Chirp>();
		chirps = this.findAll();

		for (String s : tabooWords) {
			for (Chirp c : chirps) {
				if (c.getTitle().toLowerCase().contains(s.toLowerCase())
						|| c.getText().toLowerCase().contains(s.toLowerCase())) {
					c.setTaboo(true);
				}
			}
		}
	}

	public Collection<Chirp> findChirpTaboo() {
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority user = new Authority();
		user.setAuthority("USER");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(user) || authority.contains(admin));
		Collection<Chirp> res = new ArrayList<Chirp>();
		res.addAll(chirpRepository.findChirpTaboo());
		return res;
	}

	public void flush() {
		this.chirpRepository.flush();
	}

	public Chirp reconstruct(final Chirp chirp, final BindingResult binding) {
		Chirp res;
		Chirp chirpFinal;
		if (chirp.getId() == 0) {
			User userPrincipal;

			userPrincipal = this.userService.findByPrincipal();
			chirp.setUser(userPrincipal);

			res = chirp;
		} else {
			chirpFinal = this.chirpRepository.findOne(chirp.getId());

			chirp.setId(chirpFinal.getId());
			chirp.setVersion(chirpFinal.getVersion());
			chirp.setMoment(chirpFinal.getMoment());
			chirp.setUser(chirpFinal.getUser());
			chirp.setText(chirpFinal.getText());
			chirp.setTitle(chirpFinal.getTitle());

			res = chirp;
		}
		this.validator.validate(res, binding);
		return res;
	}

}