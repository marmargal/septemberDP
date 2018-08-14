package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ChirpRepository;
import domain.Chirp;
import domain.User;

@Service
@Transactional
public class ChirpService {

	// Managed repository
	@Autowired
	private ChirpRepository chirpRepository;

	// Suporting services
	@Autowired
	private UserService userService;

	// Constructors

	public ChirpService() {
		super();
	}

	// Simple CRUD methods

	public Chirp create() {
		Chirp res;
		res = new Chirp();

		User user = userService.findByPrincipal();
		res.setUser(user);

		return res;
	}

	public Collection<Chirp> findAll() {
		Collection<Chirp> res;
		res = this.chirpRepository.findAll();
		return res;
	}

	public Chirp findOne(final int id) {
		Assert.isTrue(id != 0);
		Chirp res;
		res = this.chirpRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Chirp save(final Chirp chirp) {
		Assert.notNull(chirp);
		Chirp res;

		res = this.chirpRepository.save(chirp);
		return res;
	}

	public void delete(Chirp chirp) {
		Assert.notNull(chirp);
		Assert.isTrue(chirp.getId() != 0);
		Assert.isTrue(this.chirpRepository.exists(chirp.getId()));
		this.chirpRepository.delete(chirp);
	}

	// Other business methods

	public void flush() {
		this.chirpRepository.flush();
	}
}
