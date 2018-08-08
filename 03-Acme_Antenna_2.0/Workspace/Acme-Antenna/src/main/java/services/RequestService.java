package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Handyworker;
import domain.Request;
import domain.User;

@Service
@Transactional
public class RequestService {

	// Managed repository

	@Autowired
	private RequestRepository requestRepository;

	// Suporting services
	@Autowired
	private HandyworkerService handyworkerService;

	@Autowired
	private UserService userService;

	// Constructors

	public RequestService() {
		super();
	}

	// Simple CRUD methods

	public Request create() {
		Request res;
		res = new Request();

		Handyworker handyworker;
		handyworker = handyworkerService.findByPrincipal();

		User user;
		user = userService.findByPrincipal();

		res.setUser(user);
		res.setHandyworker(handyworker);

		return res;
	}

	public Collection<Request> findAll() {
		Collection<Request> res;
		res = this.requestRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Request findOne(final int id) {
		Assert.isTrue(id != 0);
		Request res;
		res = this.requestRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Request save(final Request request) {
		this.handyworkerService.checkAuthority();
		this.userService.checkAuthority();

		Assert.isTrue(request.getUser().equals(
				this.userService.findByPrincipal()));
		Assert.isTrue(request.getHandyworker().equals(
				this.handyworkerService.findByPrincipal()));
		Assert.notNull(request);

		Request res;
		res = this.requestRepository.save(request);
		return res;
	}

	public void delete(Request request) {
		Assert.notNull(request);
		Assert.isTrue(request.getId() != 0);
		Assert.isTrue(this.requestRepository.exists(request.getId()));
		this.requestRepository.delete(request);
	}

}
