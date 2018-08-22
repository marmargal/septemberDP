package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RegistryRepository;
import domain.Registry;

@Service
@Transactional
public class RegistrytService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private RegistryRepository registytRepositoty;

	// Supporting services ----------------------------------------------------

	@Autowired
	private InnkeeperService innkeeperService;

	// Constructor ------------------------------------------------------------

	public RegistrytService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Registry create() {
		this.innkeeperService.checkAuthority();
		Registry result;
		Date moment;

		result = new Registry();
		moment = new Date(System.currentTimeMillis() - 1000);
		result.setDate(moment);

		return result;
	}

	public Collection<Registry> findAll() {
		Collection<Registry> res;
		res = this.registytRepositoty.findAll();
		Assert.notNull(res);
		return res;
	}

	public Registry findOne(int id) {
		Assert.isTrue(id != 0);
		Registry res;
		res = this.registytRepositoty.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Registry save(Registry registry) {
		this.innkeeperService.checkAuthority();
		Assert.notNull(registry);
		Registry res;
		res = this.registytRepositoty.save(registry);
		return res;
	}

	// Other business method --------------------------------------------------

}
