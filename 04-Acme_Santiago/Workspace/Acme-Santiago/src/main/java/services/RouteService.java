package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RouteRepository;
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
		for (Hike hike : route.getHikes()) {
			hike.setRoute(route);
			this.hikeService.save(hike);
			}
		res = this.routeRepository.save(route);
		return res;
	}

	public void delete(Route route) {
		Assert.notNull(route);
		Assert.isTrue(route.getId() != 0);
		Assert.isTrue(this.routeRepository.exists(route.getId()));
		
		Collection<Hike> hikes = new ArrayList<Hike>();
		hikes = new ArrayList<Hike>(this.hikeService.findHikeByRoute(route.getId()));
		for (Hike hike : hikes) {
			this.hikeService.delete(hike);
		}
		
		Collection<Comment> comments = new ArrayList<Comment>();
		comments = route.getComments();
		for (Comment comment : comments) {
			this.commentService.delete(comment);
		}
		
		this.routeRepository.delete(route);
	}

	// Other business methods

	public void flush() {
		this.routeRepository.flush();
	}

	public Collection<Route> searchRoute(String criteria) {
		return this.routeRepository.searchRoute(criteria);
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
