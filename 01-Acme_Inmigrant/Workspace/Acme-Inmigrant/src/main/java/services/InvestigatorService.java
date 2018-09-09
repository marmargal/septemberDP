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

import repositories.InvestigatorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Immigrant;
import domain.Investigator;
import domain.Report;
import forms.ActorForm;

@Service
@Transactional
public class InvestigatorService {
	// Managed repository

	@Autowired
	private InvestigatorRepository investigatorRepository;
	
	@Autowired
	private Validator		validator;

	// Supporting services
	
	
	// Constructors

	public InvestigatorService() {
		super();
	}

	// Simple CRUD methods
	
	public Investigator create() {
		Investigator res = new Investigator();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		Collection<Report> reports = new ArrayList<Report>();
		
		authority.setAuthority(Authority.INVESTIGATOR);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		res.setReports(reports);
		
		return res;
	}

	public Collection<Investigator> findAll() {
		Collection<Investigator> res;
		res = this.investigatorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Investigator findOne(int investigatorId) {
		Assert.isTrue(investigatorId != 0);
		Investigator res;
		res = this.investigatorRepository.findOne(investigatorId);
		Assert.notNull(res);
		return res;
	}

	public Investigator save(Investigator investigator) {
		Investigator res;
		
		String pass = investigator.getUserAccount().getPassword();
		
		final Md5PasswordEncoder code = new Md5PasswordEncoder();
		
		pass = code.encodePassword(pass, null);
		
		investigator.getUserAccount().setPassword(pass);

		res = this.investigatorRepository.save(investigator);
		
		return res;
	}

	public void delete(Investigator investigator) {
		Assert.notNull(investigator);
		Assert.isTrue(investigator.getId() != 0);
		Assert.isTrue(this.investigatorRepository.exists(investigator.getId()));
		this.investigatorRepository.delete(investigator);
	}

	// Other business methods

	public Investigator findByPrincipal() {
		Investigator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.investigatorRepository.findInvestigatorByUserAccountId(userAccount.getId());
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
		res.setAuthority("INVESTIGATOR");
		Assert.isTrue(authority.contains(res));
	}
	
	public ActorForm construct(Investigator investigator){
		ActorForm res = new ActorForm();
		
		res.setId(investigator.getId());
		res.setName(investigator.getName());
		res.setSurname(investigator.getSurname());
		res.setEmail(investigator.getEmail());
		res.setPhoneNumber(investigator.getPhoneNumber());
		res.setAddress(investigator.getAddress());
		res.setUsername(investigator.getUserAccount().getUsername());
		
		return res;
	}
	
	public Investigator reconstruct(ActorForm investigatorForm, BindingResult binding){
		Assert.notNull(investigatorForm);
		
		Investigator res = new Investigator();

		if (investigatorForm.getId() != 0)
			res = this.findOne(investigatorForm.getId());
		else
			res = this.create();
		
		res.setName(investigatorForm.getName());
		res.setSurname(investigatorForm.getSurname());
		res.setEmail(investigatorForm.getEmail());
		res.setPhoneNumber(investigatorForm.getPhoneNumber());
		res.setAddress(investigatorForm.getAddress());
		res.getUserAccount().setUsername(investigatorForm.getUsername());
		res.getUserAccount().setPassword(investigatorForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}
	
	public Collection<Application> findApplicationByOfficer(int officerId){
		Collection<Application> applications = new ArrayList<Application>();
		applications = this.investigatorRepository.findApplicationByOfficer(officerId); 
		return applications;
	}
	
	public Immigrant findImmigrantByApplication(int applicationId){
		Immigrant immigrant = new Immigrant();
		immigrant = this.investigatorRepository.findImmigrantByApplication(applicationId); 
		return immigrant;
	}
	
	public Investigator findInvestigatorByImmigrant(int immigrantId){
		Investigator investigator = new Investigator();
		investigator = this.investigatorRepository.findInvestigatorByImmigrant(immigrantId); 
		return investigator;
	}

	public void flush() {
		this.investigatorRepository.flush();
	}
	
}
