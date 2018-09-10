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

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import domain.Antenna;
import forms.ActorForm;

@Service
@Transactional
public class AdministratorService {

	// Managed repository
	@Autowired
	private AdministratorRepository administratorRepository;

	// Suporting services

	@Autowired
	private Validator validator;

	// Constructors

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods

	public Administrator create() {
		Administrator res = new Administrator();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

		authority.setAuthority("ADMIN");
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res;
		res = administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator res;
		res = administratorRepository.findOne(administratorId);
		Assert.notNull(res);
		return res;
	}

	public Administrator save(Administrator administrator) {
		Administrator res;

		String pass = administrator.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		administrator.getUserAccount().setPassword(pass);

		res = this.administratorRepository.save(administrator);

		return res;

	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(administratorRepository.exists(administrator.getId()));
		administratorRepository.delete(administrator);
	}

	// Other business methods

	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = administratorRepository
				.findAdministratorByUserAccountId(userAccount.getId());
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
		res.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(res));
	}

	public ActorForm construct(Administrator administrator) {
		ActorForm res = new ActorForm();

		res.setId(administrator.getId());
		res.setName(administrator.getName());
		res.setSurname(administrator.getSurname());
		res.setPictures(administrator.getPictures());
		res.setPostalAddress(administrator.getPostalAddress());
		res.setPhoneNumber(administrator.getPhoneNumber());
		res.setEmail(administrator.getEmail());
		res.setUsername(administrator.getUserAccount().getUsername());

		return res;
	}

	public Administrator reconstruct(final ActorForm administratorForm,
			final BindingResult binding) {
		Assert.notNull(administratorForm);
		Administrator res = new Administrator();

		if (administratorForm.getId() != 0)
			res = this.findOne(administratorForm.getId());
		else
			res = this.create();

		res.setName(administratorForm.getName());
		res.setSurname(administratorForm.getSurname());
		res.setPictures(administratorForm.getPictures());
		res.setEmail(administratorForm.getEmail());
		res.setPhoneNumber(administratorForm.getPhoneNumber());
		res.setPostalAddress(administratorForm.getPostalAddress());
		res.getUserAccount().setUsername(administratorForm.getUsername());
		res.getUserAccount().setPassword(administratorForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}

	// methods for dashboard
	public Collection<Double> dataAntennasPerUser() {
		return administratorRepository.dataAntennasPerUser();

	}

	public Collection<Double> dataQualityPerAntennas() {
		return administratorRepository.dataQualityPerAntennas();

	}

	public Collection<String> dataNumAntennasPerModel() {
//		Collection<String> res = new ArrayList<String>();
//		for (Object o : administratorRepository.dataNumAntennasPerModel()) {
//			if (o instanceof String) {
//				String ins = (String) o;
//				res.add(ins);
//			}
//		}

		return administratorRepository.dataNumAntennasPerModel();

	}
	

	public Collection<Antenna> Top3AntennaPerpopularity() {
		return administratorRepository.Top3AntennaPerpopularity();

	}

	public Collection<Double> dataTutorialPerUser() {
		return administratorRepository.dataTutorialPerUser();

	}

	public Collection<Double> dataNumCommentPerTutorial() {
		return administratorRepository.dataNumCommentPerTutorial();

	}

	public Collection<Actor> actorHasPublished() {
		return administratorRepository.actorHasPublished();

	}

	public Collection<Double> dataNumRepliesPerComment() {
		return administratorRepository.dataNumRepliesPerComment();

	}

	public Collection<Double> dataNumLengthOfComments() {
		return administratorRepository.dataNumLengthOfComments();

	}

	// TODO: Query por descomentar
//	public Collection<Double> dataNumPicturesPerTutorial() {
//		return administratorRepository.dataNumPicturesPerTutorial();
//
//	}

	// TODO: Query por descomentar
//	public Collection<Double> dataNumPicturesPerComment() {
//		return administratorRepository.dataNumPicturesPerComment();
//
//	}
}
