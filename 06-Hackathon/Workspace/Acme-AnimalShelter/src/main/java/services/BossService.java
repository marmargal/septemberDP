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

import repositories.BossRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Boss;
import domain.Center;
import domain.Folder;
import forms.ActorForm;


@Service
@Transactional
public class BossService {

	// Managed repository

	@Autowired
	private BossRepository bossRepository;
	
	// Supporting services
	
	@Autowired
	private Validator		validator;
	
	@Autowired
	private FolderService folderService;
	
	// Constructors

	public BossService() {
		super();
	}
	
	// Simple CRUD methods

	public Boss create() {
		Boss res = new Boss();
		
		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Center> centers = new ArrayList<Center>();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Folder inBox = this.folderService.create();
		Folder outBox = this.folderService.create();
		Folder trash = this.folderService.create();
		
		authority.setAuthority(Authority.BOSS);
		userAccount.addAuthority(authority);
		
		inBox.setName("In Box");
		outBox.setName("Out Box");
		trash.setName("Trash");
		this.folderService.save(inBox);
		this.folderService.save(outBox);
		this.folderService.save(trash);
		folders.add(inBox);
		folders.add(outBox);
		folders.add(trash);

		res.setUserAccount(userAccount);
		res.setFolders(folders);
		res.setCenters(centers);
		
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
	
	public ActorForm construct(Boss boss){
		ActorForm res = new ActorForm();
		
		res.setId(boss.getId());
		res.setName(boss.getName());
		res.setSurname(boss.getSurname());
		res.setEmail(boss.getEmail());
		res.setPhoneNumber(boss.getPhoneNumber());
		res.setAddress(boss.getAddress());
		res.setUsername(boss.getUserAccount().getUsername());
		
		return res;
	}
	
	public Boss reconstruct(ActorForm bossForm, BindingResult binding){
		Assert.notNull(bossForm);
		
		Boss res = new Boss();

		if (bossForm.getId() != 0)
			res = this.findOne(bossForm.getId());
		else
			res = this.create();
		
		res.setName(bossForm.getName());
		res.setSurname(bossForm.getSurname());
		res.setEmail(bossForm.getEmail());
		res.setPhoneNumber(bossForm.getPhoneNumber());
		res.setAddress(bossForm.getAddress());
		res.getUserAccount().setUsername(bossForm.getUsername());
		res.getUserAccount().setPassword(bossForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}
	
	

}
