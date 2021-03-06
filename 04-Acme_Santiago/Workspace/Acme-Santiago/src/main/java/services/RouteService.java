package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RouteRepository;
import security.Authority;
import security.LoginService;
import domain.Comment;
import domain.Hike;
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

	@Autowired
	private HikeService hikeService;

	@Autowired
	private CommentService commentService;

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
		return res;
	}

	public Route save(final Route route) {
		Assert.notNull(route);
		Route res;
		this.userService.checkAuthority();
		for (Hike hike : route.getHikes()) {
			hike.setRoute(route);
			this.hikeService.save(hike);
		}
		res = this.routeRepository.save(route);
		return res;
	}

	public void delete(Route route) {
		try {

			Assert.notNull(route);
			Assert.isTrue(route.getId() != 0);
			Assert.isTrue(this.routeRepository.exists(route.getId()));
			Collection<Authority> authority = LoginService.getPrincipal()
					.getAuthorities();
			Assert.notNull(authority);
			Authority user = new Authority();
			user.setAuthority("USER");
			Authority admin = new Authority();
			admin.setAuthority("ADMIN");
			Assert.isTrue(authority.contains(user) || authority.contains(admin));
			if (authority.contains(user)) {
				Assert.isTrue(route.getUser().equals(
						this.userService.findByPrincipal()));

			}
			

			
			this.routeRepository.delete(route);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// Other business methods

	public void flush() {
		this.routeRepository.flush();
	}

	public Collection<Route> searchRoute(String criteria) {
		Collection<Route> routes = new ArrayList<>();
		if (criteria.isEmpty()) {
			routes.addAll(this.routeRepository.findAll());
		} else {

			routes.addAll(this.routeRepository.searchRoute(criteria));
			routes.addAll(this.routeRepository.searchRoute2(criteria));
		}
		return routes;
	}

	public Collection<Route> lengthRoute(double max, double min) {
		return this.routeRepository.lengthRoute(max, min);
	}

	public Collection<Route> numHikesRoute(int max, int min) {
		return this.routeRepository.numHikesRoute(max, min);
	}

	public Collection<Hike> hikesWithoutRoute() {
		Collection<Hike> hikes = new ArrayList<Hike>();
		hikes = this.routeRepository.hikesWithoutRoute();
		return hikes;
	}

}
