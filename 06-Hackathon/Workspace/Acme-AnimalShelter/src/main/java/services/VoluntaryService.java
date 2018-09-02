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

import repositories.VoluntaryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Message;
import domain.Voluntary;
import forms.ActorForm;


@Service
@Transactional
public class VoluntaryService {

	// Managed repository

	@Autowired
	private VoluntaryRepository voluntaryRepository;
	
	// Supporting services
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private Validator		validator;
	
	// Constructors

	public VoluntaryService() {
		super();
	}
	
	// Simple CRUD methods

	public Voluntary create() {
		Voluntary res = new Voluntary();
		
		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Message> messages = new ArrayList<Message>();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Folder inBox = this.folderService.create();
		Folder outBox = this.folderService.create();
		Folder trash = this.folderService.create();
		
		authority.setAuthority(Authority.VOLUNTARY);
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
		res.setSent(messages);
		res.setBan(false);
		
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
	
	public ActorForm construct(Voluntary voluntary){
		ActorForm res = new ActorForm();
		
		res.setId(voluntary.getId());
		res.setName(voluntary.getName());
		res.setSurname(voluntary.getSurname());
		res.setEmail(voluntary.getEmail());
		res.setPhoneNumber(voluntary.getPhoneNumber());
		res.setAddress(voluntary.getAddress());
		res.setUsername(voluntary.getUserAccount().getUsername());
		
		return res;
	}
	
	public Voluntary reconstruct(ActorForm voluntaryForm, BindingResult binding){
		Assert.notNull(voluntaryForm);
		
		Voluntary res = new Voluntary();

		if (voluntaryForm.getId() != 0)
			res = this.findOne(voluntaryForm.getId());
		else
			res = this.create();
		
		res.setName(voluntaryForm.getName());
		res.setSurname(voluntaryForm.getSurname());
		res.setEmail(voluntaryForm.getEmail());
		res.setPhoneNumber(voluntaryForm.getPhoneNumber());
		res.setAddress(voluntaryForm.getAddress());
		res.getUserAccount().setUsername(voluntaryForm.getUsername());
		res.getUserAccount().setPassword(voluntaryForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}

}
