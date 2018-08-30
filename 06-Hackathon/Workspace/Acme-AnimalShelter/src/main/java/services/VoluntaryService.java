package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VoluntaryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Voluntary;


@Service
@Transactional
public class VoluntaryService {

	// Managed repository

	@Autowired
	private VoluntaryRepository voluntaryRepository;
	
	// Supporting services
	
	
	// Constructors

	public VoluntaryService() {
		super();
	}
	
	// Simple CRUD methods

	public Voluntary create() {
		Voluntary res = new Voluntary();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		authority.setAuthority(Authority.VOLUNTARY);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		
		return res;
	}

	public Collection<Voluntary> findAll() {
		Collection<Voluntary> res;
		res = this.voluntaryRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Voluntary findOne(int voluntaryId) {
		Assert.isTrue(voluntaryId != 0);
		Voluntary res;
		res = this.voluntaryRepository.findOne(voluntaryId);
		Assert.notNull(res);
		return res;
	}

	public Voluntary save(Voluntary voluntary) {
		Voluntary res;
		
		String pass = voluntary.getUserAccount().getPassword();
		
		final Md5PasswordEncoder code = new Md5PasswordEncoder();
		
		pass = code.encodePassword(pass, null);
		
		voluntary.getUserAccount().setPassword(pass);

		res = this.voluntaryRepository.save(voluntary);
		
		return res;
	}
	
	public void delete(Voluntary voluntary) {
		Assert.notNull(voluntary);
		Assert.isTrue(voluntary.getId() != 0);
		Assert.isTrue(this.voluntaryRepository.exists(voluntary.getId()));
		this.voluntaryRepository.delete(voluntary);
	}

	// Other business methods

	public Voluntary findByPrincipal() {
		Voluntary res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.voluntaryRepository.findVoluntaryByPrincipal(userAccount.getId());
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("VOLUNTARY");
		Assert.isTrue(authority.contains(res));
	}	
	
	

}
