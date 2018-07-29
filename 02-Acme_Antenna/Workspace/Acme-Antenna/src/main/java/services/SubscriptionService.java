package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubscriptionRepository;
import domain.CreditCard;
import domain.Subscription;
import domain.User;

@Service
@Transactional
public class SubscriptionService {

	// Managed repository

	@Autowired
	private SubscriptionRepository subscriptionRepository;

	// Suporting services

	private UserService userService;
	
	// Constructors

	public SubscriptionService() {
		super();
	}

	// Simple CRUD methods

	public Subscription create() {
		Subscription res;
		res = new Subscription();

		Date startDate = new Date(System.currentTimeMillis() - 1000);
		Date endDate = new Date(System.currentTimeMillis() + 1000);
		
		CreditCard cc = new CreditCard();
		cc.setBrandName("Master Card");
		cc.setCvv(123);
		cc.setExpirationMonth(12);
		cc.setExpirationYear(2040);
		cc.setHolderName("BBVA");
		cc.setNumber("5105105105105100");
		
		User user;
		user = userService.findByPrincipal();
		
		res.setStartDate(startDate);
		res.setEndDate(endDate);
		res.setCreditCard(cc);
		res.setUser(user);
		
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
		Assert.isTrue(subscription.getUser().equals(
				this.userService.findByPrincipal()));
		Assert.notNull(subscription);
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
}
