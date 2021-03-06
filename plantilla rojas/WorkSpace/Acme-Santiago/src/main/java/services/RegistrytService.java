package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RegistryRepository;
import domain.Hike;
import domain.Inn;
import domain.Innkeeper;
import domain.Registry;
import domain.User;

@Service
@Transactional
public class RegistrytService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private RegistryRepository registytRepositoty;

	// Supporting services ----------------------------------------------------

	@Autowired
	private InnkeeperService innkeeperService;

	@Autowired
	private InnService innService;

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
		Innkeeper principal = innkeeperService.findByPrincipal();
		Assert.isTrue(registry.getInn().getInnkeeper() == principal);
		Registry res;

		res = this.registytRepositoty.save(registry);
		return res;
	}

	public void delete(Registry registry) {
		Assert.notNull(registry);
		Assert.isTrue(registry.getId() != 0);
		Assert.isTrue(this.registytRepositoty.exists(registry.getId()));
		Collection<Registry> registries = new ArrayList<>();
		registries.addAll(registry.getInn().getRegistries());
		registries.remove(registry);
		Inn inn = registry.getInn();
		inn.setRegistries(registries);
		this.innService.save(inn);
		this.registytRepositoty.delete(registry);
	}

	public Registry findRegistry(Date date, Inn inn, User user, Hike hike) {
		return this.registytRepositoty.findRegistry(date, inn, user, hike);
	}

	public Collection<Registry> findByUser(User user) {
		return this.registytRepositoty.findByUser(user);
	}

	public Collection<Registry> findByHike(Hike hike) {
		return this.registytRepositoty.findByHike(hike);
	}
	// Other business method --------------------------------------------------

}
