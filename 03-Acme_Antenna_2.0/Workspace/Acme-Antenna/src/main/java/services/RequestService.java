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
	private AntennaService antennaService;
	
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
		
		Date moment = new Date(System.currentTimeMillis() - 1000);

//		Handyworker handyworker;
//		handyworker = handyworkerService.findByPrincipal();

		User user;
		user = userService.findByPrincipal();

		res.setUser(user);
//		res.setHandyworker(handyworker);
		res.setMoment(moment);

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
//		this.handyworkerService.checkAuthority();
//		this.userService.checkAuthority();

		Assert.isTrue(request.getUser().equals(
				this.userService.findByPrincipal()));
//		Assert.isTrue(request.getHandyworker().equals(
//				this.handyworkerService.findByPrincipal()));
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

	public Request reconstruct(RequestForm requestForm, BindingResult binding) {
		Assert.notNull(requestForm);
		
		Request res;
		CreditCard cc = new CreditCard();
		
		if(requestForm.getId()!=0)
			res = this.findOne(requestForm.getId());
		else
			res = this.create();
		
		//CREDIT CARD
		int cvv = Integer.parseInt(requestForm.getCvv());
		int expirationMonth = Integer.parseInt(requestForm.getExpirationMonth());
		int expirationYear = Integer.parseInt(requestForm.getExpirationYear());
		
		cc.setBrandName(requestForm.getBrandName());
		cc.setCvv(cvv);
		cc.setExpirationMonth(expirationMonth);
		cc.setExpirationYear(expirationYear);
		cc.setHolderName(requestForm.getHolderName());
		cc.setNumber(requestForm.getNumber());
		
		
		res.setCreditCard(cc);
		res.setDescription(requestForm.getDescription());
		res.setResult(requestForm.getResult());
		res.setAntenna(this.antennaService.findOne(requestForm.getAntennaId()));
		
		if(binding!=null)
			this.validator.validate(res, binding);
		
		return res;
	}

	

}
