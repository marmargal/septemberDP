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

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Antenna;
import domain.Comment;
import domain.Subscription;
import domain.User;
import forms.UserForm;

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
	
	public User saveForComment(User user) {
		User res;

		if(user.getId() == 0){
			String pass = user.getUserAccount().getPassword();

			final Md5PasswordEncoder code = new Md5PasswordEncoder();

			pass = code.encodePassword(pass, null);

			user.getUserAccount().setPassword(pass);
		}

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

	public UserForm reconstruct(final UserForm userForm, final BindingResult binding) {
		User res;
		UserForm userFinal = null;
		res = userForm.getUser();
		if (res.getId() == 0) {
			Collection<Comment> comment;
			Collection<Subscription> subscription;
			Collection<Antenna> antenna;
			UserAccount userAccount;
			Authority authority;
			userAccount = userForm.getUser().getUserAccount();
			authority = new Authority();
			comment = new ArrayList<Comment>();
			subscription = new ArrayList<Subscription>();
			antenna = new ArrayList<Antenna>();
			userForm.getUser().setUserAccount(userAccount);
			authority.setAuthority(Authority.USER);
			userAccount.addAuthority(authority);
			userForm.getUser().setComments(comment);
			userForm.getUser().setSubscriptions(subscription);
			userForm.getUser().setAntennas(antenna);
			userFinal = userForm;
		} else {
			res = this.userRepository.findOne(userForm.getUser().getId());
			userForm.getUser().setId(res.getId());
			userForm.getUser().setVersion(res.getVersion());
			userForm.getUser().setUserAccount(res.getUserAccount());
			userForm.getUser().setComments(res.getComments());
			userForm.getUser().setSubscriptions(res.getSubscriptions());
			userForm.getUser().setAntennas(res.getAntennas());
			userFinal = userForm;
		}
		this.validator.validate(userFinal, binding);
		return userFinal;
	}

	public User reconstruct(final User user, final BindingResult binding) {
		User res;
		User userFinal;
		if (user.getId() == 0) {
			UserAccount userAccount;
			Authority authority;
			userAccount = user.getUserAccount();
			user.setUserAccount(userAccount);
			authority = new Authority();
			authority.setAuthority(Authority.USER);
			userAccount.addAuthority(authority);
			String password = "";
			password = user.getUserAccount().getPassword();
			user.getUserAccount().setPassword(password);
			userFinal = user;
		} else {
			res = this.userRepository.findOne(user.getId());
			user.setId(res.getId());
			user.setVersion(res.getVersion());
			user.setUserAccount(res.getUserAccount());
			user.getUserAccount().setPassword(user.getUserAccount().getPassword());
			user.getUserAccount().setAuthorities(user.getUserAccount().getAuthorities());
			userFinal = user;
		}
		this.validator.validate(userFinal, binding);
		return userFinal;
	}

	public void flush() {
		this.userRepository.flush();
	}
}
