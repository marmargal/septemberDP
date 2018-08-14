package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RequestRepository;
import domain.CreditCard;
import domain.Handyworker;
import domain.Request;
import domain.User;
import forms.RequestForm;

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

	@Autowired
	private Validator validator;

	// Constructors

	public RequestService() {
		super();
	}

	// Simple CRUD methods

	public Request create() {
		Request res;
		res = new Request();

		Collection<CreditCard> creditCards = new ArrayList<CreditCard>();

		User user;
		user = userService.findByPrincipal();

		res.setUser(user);
		
		creditCards = this.findAllCreditCard(user.getId());
		if (creditCards.size() > 0)
			res.setCreditCard(creditCards.iterator().next());

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
		// this.handyworkerService.checkAuthority();
		// this.userService.checkAuthority();
		
		if (request.getId() == 0) {
			Date moment;
			moment = new Date(System.currentTimeMillis() - 1000);
			request.setMoment(moment);
		}

		if (this.userService.findByPrincipal() != null) {
			Assert.isTrue(request.getUser().equals(
					this.userService.findByPrincipal()));

		} else if (this.handyworkerService.findByPrincipal() != null) {
			Assert.isTrue(request.getHandyworker().equals(
					this.handyworkerService.findByPrincipal()));
		} else {
			Assert.notNull(null);

		}
		
		// Assert.isTrue(request.getHandyworker().equals(
		// this.handyworkerService.findByPrincipal()));
		Assert.notNull(request);

		if(!request.getResult().isEmpty()){
			request.setFinishMoment(new Date());
		}
		
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

	// Other business method --------------------------------

	public Collection<Request> alreadyServicedRequest(int userId) {
		Collection<Request> requests = new ArrayList<Request>();
		requests = this.requestRepository.alreadyServicedRequest(userId);
		return requests;
	}

	public Collection<Request> notYetServicedRequest(int userId) {
		Collection<Request> requests = new ArrayList<Request>();
		requests = this.requestRepository.notYetServicedRequest(userId);
		return requests;
	}
	
	public Collection<CreditCard> findAllCreditCard(int userId) {
		Collection<CreditCard> creditcCards = new ArrayList<CreditCard>();

		creditcCards = requestRepository.findAllCreditCard(userId);

		return creditcCards;
	}
	
	public RequestForm construct(Request request) {
		Assert.notNull(request);
		RequestForm res = new RequestForm();
		
		res.setRequest(request);
		
		res.setAntenna(request.getAntenna());
		res.setCreditCard(request.getCreditCard());
		res.setDescription(request.getDescription());
		res.setId(request.getId());
		res.setRequestHandyworker(request.getRequestHandyworker());
		res.setResult(request.getResult());

		return res;
	}

	public Request reconstruct(RequestForm requestForm, BindingResult binding) {
		Assert.notNull(requestForm);

		Request res;
		CreditCard cc = new CreditCard();

		if (requestForm.getId() != 0)
			res = this.findOne(requestForm.getId());
		else
			res = this.create();

		// CREDIT CARD
		cc = requestForm.getCreditCard();

		res.setCreditCard(cc);
		res.setDescription(requestForm.getDescription());
		res.setResult(requestForm.getResult());
		res.setAntenna(requestForm.getAntenna());
		res.setRequestHandyworker(requestForm.getRequestHandyworker());

		if (binding != null)
			this.validator.validate(res, binding);

		return res;
	}

	public Collection<Request> requestUnassigned() {
		Collection<Request> requests = new ArrayList<>();
		
		Handyworker handyworker = handyworkerService.findByPrincipal();
		System.out.println(handyworker);
		
		for (Request r : this.findAll()) {
			if (r.getHandyworker() == null && r.getRequestHandyworker().getId() == handyworker.getId()) {
				requests.add(r);
				System.out.println(r.getRequestHandyworker());
			}
		}
		return requests;
	}

}
