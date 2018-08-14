package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RouteRepository;
import domain.Route;
import domain.User;

@Service
@Transactional
public class RouteService {

	// Managed repository
	@Autowired
	private RouteRepository routeRepository;

	// Suporting services
	@Autowired
	private UserService userService;

	// Constructors

	public RouteService() {
		super();
	}

	// Simple CRUD methods

	public Route create() {
		Route res;
		res = new Route();

		User user = userService.findByPrincipal();
		res.setUser(user);

		return res;
	}

	public Collection<Route> findAll() {
		Collection<Route> res;
		res = this.routeRepository.findAll();
		return res;
	}

	public Route findOne(final int id) {
		Assert.isTrue(id != 0);
		Route res;
		res = this.routeRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Route save(final Route route) {
		Assert.notNull(route);
		Route res;

		res = this.routeRepository.save(route);
		return res;
	}

	public void delete(Route route) {
		Assert.notNull(route);
		Assert.isTrue(route.getId() != 0);
		Assert.isTrue(this.routeRepository.exists(route.getId()));
		this.routeRepository.delete(route);
	}

	// Other business methods

	public void flush() {
		this.routeRepository.flush();
	}

	public Collection<Route> searchRoute(String criteria) {
		return this.routeRepository.searchRoute(criteria);
	}

	public Collection<Route> lentghRoute(double max, double min) {
		return this.routeRepository.lentghRoute(max, min);
	}

	public Collection<Route> numHikesRoute(int max, int min) {
		return this.routeRepository.numHikesRoute(max, min);
	}
}
