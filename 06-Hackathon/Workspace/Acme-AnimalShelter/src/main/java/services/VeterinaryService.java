package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.VeterinaryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Veterinary;


@Service
@Transactional
public class VeterinaryService {

	// Managed repository

	@Autowired
	private VeterinaryRepository veterinaryRepository;
	
	// Supporting services
	
	
	// Constructors

	public VeterinaryService() {
		super();
	}
	
	// Simple CRUD methods

	public Veterinary create() {
		Veterinary res = new Veterinary();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		authority.setAuthority(Authority.VETERINARY);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		
		return res;
	}

	public Collection<Veterinary> findAll() {
		Collection<Veterinary> res;
		res = this.veterinaryRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Veterinary findOne(int veterinaryId) {
		Assert.isTrue(veterinaryId != 0);
		Veterinary res;
		res = this.veterinaryRepository.findOne(veterinaryId);
		Assert.notNull(res);
		return res;
	}

	public Veterinary save(Veterinary veterinary) {
		Veterinary res;
		
		String pass = veterinary.getUserAccount().getPassword();
		
		final Md5PasswordEncoder code = new Md5PasswordEncoder();
		
		pass = code.encodePassword(pass, null);
		
		veterinary.getUserAccount().setPassword(pass);

		res = this.veterinaryRepository.save(veterinary);
		
		return res;
	}
	
	public void delete(Veterinary veterinary) {
		Assert.notNull(veterinary);
		Assert.isTrue(veterinary.getId() != 0);
		Assert.isTrue(this.veterinaryRepository.exists(veterinary.getId()));
		this.veterinaryRepository.delete(veterinary);
	}

	// Other business methods

	public Veterinary findByPrincipal() {
		Veterinary res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.veterinaryRepository.findVeterinaryByPrincipal(userAccount.getId());
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("VETERINARY");
		Assert.isTrue(authority.contains(res));
	}	
	
	

}
