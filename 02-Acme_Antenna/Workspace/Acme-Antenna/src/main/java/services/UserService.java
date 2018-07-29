package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.User;
import forms.ActorForm;

@Service
@Transactional
public class UserService {

	// Managed repository
	@Autowired
	private UserRepository userRepository;

	// Suporting services

	@Autowired
	private Validator validator;

	// Constructors

	public UserService() {
		super();
	}

	// Simple CRUD methods

	public User create() {
		User res = new User();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

		authority.setAuthority("USER");
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		return res;
	}

	public Collection<User> findAll() {
		Collection<User> res;
		res = userRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public User findOne(int userId) {
		Assert.isTrue(userId != 0);
		User res;
		res = userRepository.findOne(userId);
		Assert.notNull(res);
		return res;
	}

	public User save(User user) {
		User res;

		String pass = user.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		user.getUserAccount().setPassword(pass);

		res = this.userRepository.save(user);

		return res;

	}

	public void delete(User user) {
		Assert.notNull(user);
		Assert.isTrue(user.getId() != 0);
		Assert.isTrue(userRepository.exists(user.getId()));
		userRepository.delete(user);
	}

	// Other business methods

	public User findByPrincipal() {
		User res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = userRepository.findUserByUserAccountId(userAccount.getId());
		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("USER");
		Assert.isTrue(authority.contains(res));
	}

	public ActorForm construct(User user) {
		ActorForm res = new ActorForm();

		res.setId(user.getId());
		res.setName(user.getName());
		res.setSurname(user.getSurname());
		res.setPictures(user.getPictures());
		res.setPostalAddress(user.getPostalAddress());
		res.setPhoneNumber(user.getPhoneNumber());
		res.setEmail(user.getEmail());
		res.setUsername(user.getUserAccount().getUsername());

		return res;
	}

	public User reconstruct(final ActorForm userForm,
			final BindingResult binding) {
		Assert.notNull(userForm);
		User res = new User();

		if (userForm.getId() != 0)
			res = this.findOne(userForm.getId());
		else
			res = this.create();

		res.setName(userForm.getName());
		res.setSurname(userForm.getSurname());
		res.setPictures(userForm.getPictures());
		res.setEmail(userForm.getEmail());
		res.setPhoneNumber(userForm.getPhoneNumber());
		res.setPostalAddress(userForm.getPostalAddress());
		res.getUserAccount().setUsername(userForm.getUsername());
		res.getUserAccount().setPassword(userForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}
}
