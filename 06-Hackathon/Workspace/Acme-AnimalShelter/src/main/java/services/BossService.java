package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BossRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Boss;


@Service
@Transactional
public class BossService {

	// Managed repository

	@Autowired
	private BossRepository bossRepository;
	
	// Supporting services
	
	
	// Constructors

	public BossService() {
		super();
	}
	
	// Simple CRUD methods

	public Boss create() {
		Boss res = new Boss();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		authority.setAuthority(Authority.BOSS);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		
		return res;
	}

	public Collection<Boss> findAll() {
		Collection<Boss> res;
		res = this.bossRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Boss findOne(int bossId) {
		Assert.isTrue(bossId != 0);
		Boss res;
		res = this.bossRepository.findOne(bossId);
		Assert.notNull(res);
		return res;
	}

	public Boss save(Boss boss) {
		Boss res;
		
		String pass = boss.getUserAccount().getPassword();
		
		final Md5PasswordEncoder code = new Md5PasswordEncoder();
		
		pass = code.encodePassword(pass, null);
		
		boss.getUserAccount().setPassword(pass);

		res = this.bossRepository.save(boss);
		
		return res;
	}
	
	public void delete(Boss boss) {
		Assert.notNull(boss);
		Assert.isTrue(boss.getId() != 0);
		Assert.isTrue(this.bossRepository.exists(boss.getId()));
		this.bossRepository.delete(boss);
	}

	// Other business methods

	public Boss findByPrincipal() {
		Boss res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.bossRepository.findBossByPrincipal(userAccount.getId());
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("BOSS");
		Assert.isTrue(authority.contains(res));
	}	
	
	

}
