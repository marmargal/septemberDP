package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ImmigrantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Answer;
import domain.Application;
import domain.Immigrant;
import forms.ActorForm;

@Service
@Transactional
public class ImmigrantService {

	// Managed repository

	@Autowired
	private ImmigrantRepository immigrantRepository;

	// Supporting services

	//

	@Autowired
	private Validator validator;

	// Constructors

	public ImmigrantService() {
		super();
	}

	// Simple CRUD methods

	public Immigrant create() {
		Immigrant res = new Immigrant();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

		Collection<Application> applications = new ArrayList<Application>();
		Collection<Answer> anwsers = new ArrayList<Answer>();

		authority.setAuthority(Authority.IMMIGRANT);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);

		res.setApplications(applications);
		res.setAnswers(anwsers);

		return res;
	}

	public Collection<Immigrant> findAll() {
		Collection<Immigrant> res;
		res = this.immigrantRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Immigrant findOne(int immigrantId) {
		Assert.isTrue(immigrantId != 0);
		Immigrant res;
		res = this.immigrantRepository.findOne(immigrantId);
		Assert.notNull(res);
		return res;
	}

	public Immigrant save(Immigrant immigrant) {
		Immigrant res;

		if (immigrant.getId() == 0) {
			String pass = immigrant.getUserAccount().getPassword();

			final Md5PasswordEncoder code = new Md5PasswordEncoder();

			pass = code.encodePassword(pass, null);

			immigrant.getUserAccount().setPassword(pass);
		}
		res = this.immigrantRepository.save(immigrant);
		return res;
	}

	public void delete(Immigrant immigrant) {
		Assert.notNull(immigrant);
		Assert.isTrue(immigrant.getId() != 0);
		Assert.isTrue(this.immigrantRepository.exists(immigrant.getId()));
		this.immigrantRepository.delete(immigrant);
	}

	// Other business methods

	public Immigrant findByPrincipal() {
		Immigrant res;
		UserAccount immigrantAccount;
		immigrantAccount = LoginService.getPrincipal();
		res = this.immigrantRepository
				.findImmigrantByUserAccountId(immigrantAccount.getId());
		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount immigrantAccount;
		immigrantAccount = LoginService.getPrincipal();
		Assert.notNull(immigrantAccount);
		Collection<Authority> authority = immigrantAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("IMMIGRANT");
		Assert.isTrue(authority.contains(res));
	}

	public ActorForm construct(Immigrant immigrant) {
		ActorForm res = new ActorForm();
		
		res.setId(immigrant.getId());
		res.setName(immigrant.getName());
		res.setSurname(immigrant.getSurname());
		res.setPhoneNumber(immigrant.getPhoneNumber());
		res.setEmail(immigrant.getEmail());
		res.setAddress(immigrant.getAddress());
		
		return res;
	}

	public Immigrant reconstruct(final ActorForm immigrantForm,
			final BindingResult binding) {
		Assert.notNull(immigrantForm);
		Immigrant res = new Immigrant();
		
		if (immigrantForm.getId() != 0)
			res = this.findOne(immigrantForm.getId());
		else
			res = this.create();
		
		res.setName(immigrantForm.getName());
		res.setSurname(immigrantForm.getSurname());
		res.setEmail(immigrantForm.getEmail());
		res.setPhoneNumber(immigrantForm.getPhoneNumber());
		res.setAddress(immigrantForm.getAddress());
		res.getUserAccount().setUsername(immigrantForm.getUsername());
		res.getUserAccount().setPassword(immigrantForm.getPassword());
		
		this.validator.validate(res, binding);
		
		return res;
	}
	
	public Collection<Immigrant> findInvestigatedImmigrants(){
		return this.immigrantRepository.findInvestigatedImmigrants();
	}
}
