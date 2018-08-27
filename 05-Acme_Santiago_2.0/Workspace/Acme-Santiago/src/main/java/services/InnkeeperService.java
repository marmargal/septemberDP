package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.InnkeeperRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Innkeeper;
import forms.ActorForm;

@Service
@Transactional
public class InnkeeperService {

	// Managed repository
	@Autowired
	private InnkeeperRepository innkeeperRepository;

	// Suporting services

	@Autowired
	private Validator validator;

	// Constructors

	public InnkeeperService() {
		super();
	}

	// Simple CRUD methods

	public Innkeeper create() {
		Innkeeper res = new Innkeeper();

		UserAccount innkeeperAccount = new UserAccount();
		Authority authority = new Authority();

		authority.setAuthority("INNKEEPER");
		innkeeperAccount.addAuthority(authority);

		res.setUserAccount(innkeeperAccount);
		return res;
	}

	public Collection<Innkeeper> findAll() {
		Collection<Innkeeper> res;
		res = innkeeperRepository.findAll();
		return res;
	}

	public Innkeeper findOne(int innkeeperId) {
		Assert.isTrue(innkeeperId != 0);
		Innkeeper res;
		res = innkeeperRepository.findOne(innkeeperId);
		return res;
	}

	public Innkeeper save(Innkeeper innkeeper) {
		Innkeeper res;

		String pass = innkeeper.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		innkeeper.getUserAccount().setPassword(pass);

		res = this.innkeeperRepository.save(innkeeper);

		return res;

	}

	public Innkeeper saveForComment(Innkeeper innkeeper) {
		Innkeeper res;

		if (innkeeper.getId() == 0) {
			String pass = innkeeper.getUserAccount().getPassword();

			final Md5PasswordEncoder code = new Md5PasswordEncoder();

			pass = code.encodePassword(pass, null);

			innkeeper.getUserAccount().setPassword(pass);
		}

		res = this.innkeeperRepository.save(innkeeper);

		return res;

	}

	public void delete(Innkeeper innkeeper) {
		Assert.notNull(innkeeper);
		Assert.isTrue(innkeeper.getId() != 0);
		Assert.isTrue(innkeeperRepository.exists(innkeeper.getId()));
		innkeeperRepository.delete(innkeeper);
	}

	// Other business methods

	public Innkeeper findByPrincipal() {
		Innkeeper res;
		UserAccount innkeeperAccount;
		innkeeperAccount = LoginService.getPrincipal();
		res = innkeeperRepository.findInnkeeperByUserAccountId(innkeeperAccount
				.getId());
		// Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount innkeeperAccount;
		innkeeperAccount = LoginService.getPrincipal();
		Assert.notNull(innkeeperAccount);
		Collection<Authority> authority = innkeeperAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("INNKEEPER");
		Assert.isTrue(authority.contains(res));
	}

	public Innkeeper reconstruct(final ActorForm innkeeperForm,
			final BindingResult binding) {
		Assert.notNull(innkeeperForm);
		Innkeeper res = new Innkeeper();

		if (innkeeperForm.getId() != 0)
			res = this.findOne(innkeeperForm.getId());
		else
			res = this.create();

		res.setName(innkeeperForm.getName());
		res.setSurname(innkeeperForm.getSurname());
		res.setPictures(innkeeperForm.getPictures());
		res.setEmail(innkeeperForm.getEmail());
		res.setPhoneNumber(innkeeperForm.getPhoneNumber());
		res.setPostalAddress(innkeeperForm.getPostalAddress());
		res.getUserAccount().setUsername(innkeeperForm.getUsername());
		res.getUserAccount().setPassword(innkeeperForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}

	public ActorForm construct(Innkeeper innkeeper) {
		ActorForm res = new ActorForm();

		res.setId(innkeeper.getId());
		res.setName(innkeeper.getName());
		res.setSurname(innkeeper.getSurname());
		res.setPictures(innkeeper.getPictures());
		res.setPostalAddress(innkeeper.getPostalAddress());
		res.setPhoneNumber(innkeeper.getPhoneNumber());
		res.setEmail(innkeeper.getEmail());
		res.setUsername(innkeeper.getUserAccount().getUsername());

		return res;
	}

	public void flush() {
		this.innkeeperRepository.flush();
	}
}
