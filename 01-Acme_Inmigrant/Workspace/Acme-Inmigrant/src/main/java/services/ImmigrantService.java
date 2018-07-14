package services;

import java.util.ArrayList;
import java.util.Collection;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Application;
import domain.Immigrant;
import domain.Report;

import repositories.ImmigrantRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class ImmigrantService {
	
	// Managed repository

	@Autowired
	private ImmigrantRepository immigrantRepository;

	// Supporting services
	
	
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
		Collection<Report> reports = new ArrayList<Report>();
		Collection<Question> questions = new ArrayList<Question>();
		
		authority.setAuthority(Authority.IMMIGRANT);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		//TODO: Descomentar
//		res.setApplication(applications);
//		res.setReport(reports);
//		res.setQuestion(questions);
		
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
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.immigrantRepository.findImmigrantByUserAccountId(userAccount.getId());
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
		res.setAuthority("IMMIGRANT");
		Assert.isTrue(authority.contains(res));
	}

}
