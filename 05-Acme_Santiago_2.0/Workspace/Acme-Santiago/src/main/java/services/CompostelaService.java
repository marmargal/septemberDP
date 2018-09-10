package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CompostelaRepository;
import domain.Compostela;
import domain.User;
import domain.Walk;

@Service
@Transactional
public class CompostelaService {

	// Managed repository
	@Autowired
	private CompostelaRepository compostelaRepository;

	// Supporting services

	@Autowired
	private UserService userService;

	// Constructors
	public CompostelaService() {
		super();
	}

	// Simple CRUD methods

	public Compostela create() {
		Compostela res = new Compostela();

		User user = userService.findByPrincipal();
		res.setUser(user);

		return res;
	}

	public Collection<Compostela> findAll() {
		Collection<Compostela> res;
		res = this.compostelaRepository.findAll();
		return res;
	}

	public Compostela findOne(final int id) {
		Assert.isTrue(id != 0);
		Compostela res;
		res = this.compostelaRepository.findOne(id);
		return res;
	}

	public Compostela save(Compostela compostela) {
		Assert.notNull(compostela);
		Compostela res;

		if (compostela.isfinallyDecision() == true
				&& compostela.isDecision() == true) {
			compostela.setDate(new Date(System.currentTimeMillis() - 100));
		}

		res = this.compostelaRepository.save(compostela);

		return res;
	}

	public void delete(Compostela compostela) {
		Assert.notNull(compostela);
		Assert.isTrue(compostela.getId() != 0);
		Assert.isTrue(this.compostelaRepository.exists(compostela.getId()));
		User user = this.userService.findOne(compostela.getUser().getId());
		Collection<Compostela> compostelas = user.getCompostelas();
		compostelas.remove(compostela);
		user.setCompostelas(compostelas);
		this.userService.save(user);
		this.compostelaRepository.delete(compostela);
	}

	public Collection<Compostela> findCompostelaByFinallyDecision(
			boolean decision) {
		return this.compostelaRepository
				.findCompostelaByFinallyDecision(decision);
	}

	public Collection<Compostela> findCompostelaByWalk(Walk walk) {
		return this.compostelaRepository.findCompostelaByWalk(walk);
	}
}
