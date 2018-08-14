package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.InkeeperRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Inkeeper;
import forms.ActorForm;

@Service
@Transactional
public class InkeeperService {

	// Managed repository
	@Autowired
	private InkeeperRepository inkeeperRepository;

	// Suporting services

	@Autowired
	private Validator validator;

	// Constructors

	public InkeeperService() {
		super();
	}

	// Simple CRUD methods

	public Inkeeper create() {
		Inkeeper res = new Inkeeper();

		UserAccount inkeeperAccount = new UserAccount();
		Authority authority = new Authority();

		authority.setAuthority("INKEEPER");
		inkeeperAccount.addAuthority(authority);

		res.setUserAccount(inkeeperAccount);
		return res;
	}

	public Collection<Inkeeper> findAll() {
		Collection<Inkeeper> res;
		res = inkeeperRepository.findAll();
		return res;
	}

	public Inkeeper findOne(int inkeeperId) {
		Assert.isTrue(inkeeperId != 0);
		Inkeeper res;
		res = inkeeperRepository.findOne(inkeeperId);
		return res;
	}

	public Inkeeper save(Inkeeper inkeeper) {
		Inkeeper res;

		String pass = inkeeper.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		inkeeper.getUserAccount().setPassword(pass);

		res = this.inkeeperRepository.save(inkeeper);

		return res;

	}

	public Inkeeper saveForComment(Inkeeper inkeeper) {
		Inkeeper res;

		if (inkeeper.getId() == 0) {
			String pass = inkeeper.getUserAccount().getPassword();

			final Md5PasswordEncoder code = new Md5PasswordEncoder();

			pass = code.encodePassword(pass, null);

			inkeeper.getUserAccount().setPassword(pass);
		}

		res = this.inkeeperRepository.save(inkeeper);

		return res;

	}

	public void delete(Inkeeper inkeeper) {
		Assert.notNull(inkeeper);
		Assert.isTrue(inkeeper.getId() != 0);
		Assert.isTrue(inkeeperRepository.exists(inkeeper.getId()));
		inkeeperRepository.delete(inkeeper);
	}

	// Other business methods

	public Inkeeper findByPrincipal() {
		Inkeeper res;
		UserAccount inkeeperAccount;
		inkeeperAccount = LoginService.getPrincipal();
		res = inkeeperRepository.findInkeeperByUserAccountId(inkeeperAccount
				.getId());
		// Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount inkeeperAccount;
		inkeeperAccount = LoginService.getPrincipal();
		Assert.notNull(inkeeperAccount);
		Collection<Authority> authority = inkeeperAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("INKEEPER");
		Assert.isTrue(authority.contains(res));
	}

	public Inkeeper reconstruct(final ActorForm inkeeperForm,
			final BindingResult binding) {
		Assert.notNull(inkeeperForm);
		Inkeeper res = new Inkeeper();

		if (inkeeperForm.getId() != 0)
			res = this.findOne(inkeeperForm.getId());
		else
			res = this.create();

		res.setName(inkeeperForm.getName());
		res.setSurname(inkeeperForm.getSurname());
		res.setPictures(inkeeperForm.getPictures());
		res.setEmail(inkeeperForm.getEmail());
		res.setPhoneNumber(inkeeperForm.getPhoneNumber());
		res.setPostalAddress(inkeeperForm.getPostalAddress());
		res.getUserAccount().setUsername(inkeeperForm.getUsername());
		res.getUserAccount().setPassword(inkeeperForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}

	public ActorForm construct(Inkeeper inkeeper) {
		ActorForm res = new ActorForm();

		res.setId(inkeeper.getId());
		res.setName(inkeeper.getName());
		res.setSurname(inkeeper.getSurname());
		res.setPictures(inkeeper.getPictures());
		res.setPostalAddress(inkeeper.getPostalAddress());
		res.setPhoneNumber(inkeeper.getPhoneNumber());
		res.setEmail(inkeeper.getEmail());
		res.setUsername(inkeeper.getUserAccount().getUsername());

		return res;
	}

	public void flush() {
		this.inkeeperRepository.flush();
	}
}
