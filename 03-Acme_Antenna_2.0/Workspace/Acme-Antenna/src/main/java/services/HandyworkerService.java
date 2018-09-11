package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.HandyworkerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Handyworker;
import domain.Request;
import domain.Tutorial;
import forms.ActorForm;

@Service
@Transactional
public class HandyworkerService {

	// Managed repository
	@Autowired
	private HandyworkerRepository handyworkerRepository;

	// Suporting services
	@Autowired
	private Validator validator;

	// Constructors

	public HandyworkerService() {
		super();
	}

	// Simple CRUD methods

	public Handyworker create() {
		Handyworker res = new Handyworker();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Collection<Request> requests = new ArrayList<Request>();
		Collection<Tutorial> tutorials = new ArrayList<Tutorial>();

		authority.setAuthority("HANDYWORKER");
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		res.setRequests(requests);
		res.setTutorials(tutorials);
		return res;
	}

	public Collection<Handyworker> findAll() {
		Collection<Handyworker> res;
		res = handyworkerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Handyworker findOne(int handyworkerId) {
		Assert.isTrue(handyworkerId != 0);
		Handyworker res;
		res = handyworkerRepository.findOne(handyworkerId);
		Assert.notNull(res);
		return res;
	}

	public Handyworker save(Handyworker handyworker) {
		Handyworker res;

		String pass = handyworker.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		handyworker.getUserAccount().setPassword(pass);

		res = this.handyworkerRepository.save(handyworker);

		return res;

	}
	
	public Handyworker saveRequest(Handyworker handyworker) {
		Handyworker res;

		res = this.handyworkerRepository.save(handyworker);

		return res;

	}

	public void delete(Handyworker handyworker) {
		Assert.notNull(handyworker);
		Assert.isTrue(handyworker.getId() != 0);
		Assert.isTrue(handyworkerRepository.exists(handyworker.getId()));
		handyworkerRepository.delete(handyworker);
	}

	// Other business methods

	public Handyworker findByPrincipal() {
		Handyworker res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = handyworkerRepository.findHandyworkerByUserAccountId(userAccount
				.getId());
	//	Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("HANDYWORKER");
		Assert.isTrue(authority.contains(res));
	}

	public ActorForm construct(Handyworker handyworker) {
		ActorForm res = new ActorForm();

		res.setId(handyworker.getId());
		res.setName(handyworker.getName());
		res.setSurname(handyworker.getSurname());
		res.setPictures(handyworker.getPictures());
		res.setPostalAddress(handyworker.getPostalAddress());
		res.setPhoneNumber(handyworker.getPhoneNumber());
		res.setEmail(handyworker.getEmail());
		res.setUsername(handyworker.getUserAccount().getUsername());

		return res;
	}

	public Handyworker reconstruct(final ActorForm handyworkerForm,
			final BindingResult binding) {
		Assert.notNull(handyworkerForm);
		Handyworker res = new Handyworker();

		if (handyworkerForm.getId() != 0)
			res = this.findOne(handyworkerForm.getId());
		else
			res = this.create();

		res.setName(handyworkerForm.getName());
		res.setSurname(handyworkerForm.getSurname());
		res.setPictures(handyworkerForm.getPictures());
		res.setEmail(handyworkerForm.getEmail());
		res.setPhoneNumber(handyworkerForm.getPhoneNumber());
		res.setPostalAddress(handyworkerForm.getPostalAddress());
		res.getUserAccount().setUsername(handyworkerForm.getUsername());
		res.getUserAccount().setPassword(handyworkerForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}
	
	public void flush() {
		this.handyworkerRepository.flush();
	}

}
