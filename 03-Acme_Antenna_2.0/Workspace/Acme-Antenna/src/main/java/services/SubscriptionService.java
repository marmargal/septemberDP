package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SubscriptionRepository;
import domain.CreditCard;
import domain.Platform;
import domain.Subscription;
import domain.User;
import forms.SubscriptionForm;

@Service
@Transactional
public class SubscriptionService {

	// Managed repository

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	// Suporting services

	@Autowired
	private UserService userService;
	
	@Autowired
	private PlatformService platformService;
	
	@Autowired
	private Validator validator;
	
	// Constructors

	public SubscriptionService() {
		super();
	}

	// Simple CRUD methods

	public Subscription create() {
		Subscription res;
		res = new Subscription();

		User user;
		user = userService.findByPrincipal();
		
		res.setUser(user);
		res.setStartDate(new Date());
		res.setKeyCode(this.generatedKeyCode());
		
		return res;
	}
	
	public Collection<Subscription> findAll() {
		Collection<Subscription> res;
		res = this.subscriptionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Subscription findOne(final int id) {
		Assert.isTrue(id != 0);
		Subscription res;
		res = this.subscriptionRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Subscription save(final Subscription subscription) {
		this.userService.checkAuthority();
		Assert.isTrue(subscription.getUser().equals(this.userService.findByPrincipal()));
		Assert.notNull(subscription);
		Assert.isTrue(subscription.getEndDate().after(new Date()));
		this.checkPlatform(subscription.getPlatform());
		
		Subscription res;
		res = this.subscriptionRepository.save(subscription);
		return res;
	}
	
	public void delete(Subscription subscription) {
		Assert.notNull(subscription);
		Assert.isTrue(subscription.getId() != 0);
		Assert.isTrue(this.subscriptionRepository.exists(subscription.getId()));
		this.subscriptionRepository.delete(subscription);
	}
	
	public String generatedKeyCode() {
		String alphanumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		
		StringBuilder keyCode = new StringBuilder(32);
		
		for(int i = 0; i < 32; i++)
			keyCode.append(alphanumeric.charAt(random.nextInt(alphanumeric.length())));
			
		return keyCode.toString();
	}
	
	public Subscription reconstruct(SubscriptionForm subscriptionForm, BindingResult binding){
		Assert.notNull(subscriptionForm);
		
		CreditCard cc = new CreditCard();
		Subscription res = new Subscription();

		if (subscriptionForm.getId() != 0){
			res = this.findOne(subscriptionForm.getId());
		}else 
			res = this.create();
		
		//CREDIT CARD
		int cvv = Integer.parseInt(subscriptionForm.getCvv());
		int expirationMonth = Integer.parseInt(subscriptionForm.getExpirationMonth());
		int expirationYear = Integer.parseInt(subscriptionForm.getExpirationYear());
		
		cc.setBrandName(subscriptionForm.getBrandName());
		cc.setCvv(cvv);
		cc.setExpirationMonth(expirationMonth);
		cc.setExpirationYear(expirationYear);
		cc.setHolderName(subscriptionForm.getHolderName());
		cc.setNumber(subscriptionForm.getNumber());
		res.setCreditCard(cc);
		
		res.setEndDate(subscriptionForm.getEndDate());
		res.setPlatform(subscriptionForm.getPlatform());
		
		this.validator.validate(res, binding);

		return res;
	}

	private void checkPlatform(Platform platform) {
		Collection<Platform> platformsNotSubscription = new ArrayList<Platform>();
		Collection<Platform> platformsWhitSubscription = new ArrayList<Platform>();
		User user = this.userService.findByPrincipal();

		platformsNotSubscription = this.platformService.findAll();
		platformsWhitSubscription = this.platformService.findPlatformByUser(user);
		platformsNotSubscription.removeAll(platformsWhitSubscription);
		
		Assert.isTrue(platformsNotSubscription.contains(platform));
	}
	
}
