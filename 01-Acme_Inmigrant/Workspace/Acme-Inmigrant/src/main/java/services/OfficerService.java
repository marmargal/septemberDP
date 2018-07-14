package services;

import java.util.ArrayList;
import java.util.Collection;

import org.aspectj.weaver.patterns.TypePatternQuestions.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.OfficerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Decision;
import domain.Officer;

@Service
@Transactional
public class OfficerService {

	// Managed repository

	@Autowired
	private OfficerRepository officerRepository;

	// Supporting services
	
	
	// Constructors

	public OfficerService() {
		super();
	}
	
	// Simple CRUD methods

	public Officer create() {
		Officer res = new Officer();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		Collection<Application> applications = new ArrayList<Application>();
		Decision decisions = new Decision();
		Collection<Question> questions = new ArrayList<Question>();
		
		authority.setAuthority(Authority.OFFICER);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		res.setApplications(applications);
		res.setDecision(decisions);
		res.setQuestions(questions);
		
		return res;
	}

	public Collection<Officer> findAll() {
		Collection<Officer> res;
		res = this.officerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Officer findOne(int officerId) {
		Assert.isTrue(officerId != 0);
		Officer res;
		res = this.officerRepository.findOne(officerId);
		Assert.notNull(res);
		return res;
	}

	public Officer save(Officer officer) {
		Officer res;
		
		if (officer.getId() == 0) {
			String pass = officer.getUserAccount().getPassword();
			
			final Md5PasswordEncoder code = new Md5PasswordEncoder();
			
			pass = code.encodePassword(pass, null);
			
			officer.getUserAccount().setPassword(pass);
		}
		res = this.officerRepository.save(officer);
		return res;
	}

	public void delete(Officer officer) {
		Assert.notNull(officer);
		Assert.isTrue(officer.getId() != 0);
		Assert.isTrue(this.officerRepository.exists(officer.getId()));
		this.officerRepository.delete(officer);
	}

	// Other business methods

	public Officer findByPrincipal() {
		Officer res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = this.officerRepository.findOfficerByUserAccountId(userAccount.getId());
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
		res.setAuthority("OFFICER");
		Assert.isTrue(authority.contains(res));
	}	
}
