package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WalkRepository;
import domain.Compostela;
import domain.Hike;
import domain.Route;
import domain.Walk;

@Service
@Transactional
public class WalkService {

	// Managed repository
	@Autowired
	private WalkRepository walkRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CompostelaService compostelaService;

	// Supporting services

	// Constructors
	public WalkService() {
		super();
	}

	// Simple CRUD methods

	public Walk create(Route route) {
		Walk res = new Walk();

		Collection<Date> daysOfEachHike = new ArrayList<Date>();

		// comments.addAll(route.getComments());

		Collection<Hike> hikes = new ArrayList<>();
		hikes.addAll(route.getHikes());

		res.setRoute(route);

		res.setDaysOfEachHike(daysOfEachHike);

		return res;
	}

	public Collection<Walk> findAll() {
		Collection<Walk> res;
		res = this.walkRepository.findAll();
		return res;
	}

	public Walk findOne(final int id) {
		Assert.isTrue(id != 0);
		Walk res;
		res = this.walkRepository.findOne(id);
		return res;
	}

	public Walk save(Walk walk) {
		Assert.notNull(walk);
		this.userService.checkAuthority();
		Walk res;
		res = this.walkRepository.save(walk);
		return res;
	}

	public void delete(Walk walk) {
		Assert.notNull(walk);
		Assert.isTrue(walk.getId() != 0);
		Assert.isTrue(this.walkRepository.exists(walk.getId()));
		Collection<Compostela> compostelas = this.compostelaService
				.findCompostelaByWalk(walk);

		for (Compostela compostela : compostelas) {
			compostelaService.delete(compostela);
		}

		this.walkRepository.delete(walk);
	}

	public Collection<Walk> findWalkByUser(int userId) {
		this.userService.checkAuthority();
		return this.walkRepository.findWalkByUser(userId);
	}

	public Collection<Walk> findWalkByRoute(Route route) {
		return this.walkRepository.findWalkByRoute(route);
	}
}
