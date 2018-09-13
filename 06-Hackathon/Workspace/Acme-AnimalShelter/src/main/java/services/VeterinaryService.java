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

import repositories.VeterinaryRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.MedicalReport;
import domain.Veterinary;
import forms.ActorForm;


@Service
@Transactional
public class VeterinaryService {

	// Managed repository

	@Autowired
	private VeterinaryRepository veterinaryRepository;
	
	// Supporting services
	
	@Autowired
	private Validator		validator;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private BossService bossService;
	
	// Constructors

	public VeterinaryService() {
		super();
	}
	
	// Simple CRUD methods

	public Veterinary create() {
		this.bossService.checkAuthority();
		Veterinary res = new Veterinary();
		
		Collection<MedicalReport> medicalReports = new ArrayList<MedicalReport>();
		Collection<Folder> folders = new ArrayList<Folder>();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Folder inBox = this.folderService.create();
		Folder outBox = this.folderService.create();
		Folder trash = this.folderService.create();
		
		authority.setAuthority(Authority.VETERINARY);
		userAccount.addAuthority(authority);
		
		inBox.setName("In Box");
		outBox.setName("Out Box");
		trash.setName("Trash Box");
		this.folderService.save(inBox);
		this.folderService.save(outBox);
		this.folderService.save(trash);
		folders.add(inBox);
		folders.add(outBox);
		folders.add(trash);

		res.setUserAccount(userAccount);
		res.setFolders(folders);
		res.setBan(false);
		res.setMedicalReport(medicalReports);
		
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
	
	public ActorForm construct(Veterinary veterinary){
		ActorForm res = new ActorForm();
		
		res.setId(veterinary.getId());
		res.setName(veterinary.getName());
		res.setSurname(veterinary.getSurname());
		res.setEmail(veterinary.getEmail());
		res.setPhoneNumber(veterinary.getPhoneNumber());
		res.setAddress(veterinary.getAddress());
		res.setUsername(veterinary.getUserAccount().getUsername());
		
		return res;
	}
	
	public Veterinary reconstruct(ActorForm veterinaryForm, BindingResult binding){
		Assert.notNull(veterinaryForm);
		
		Veterinary res = new Veterinary();

		if (veterinaryForm.getId() != 0)
			res = this.findOne(veterinaryForm.getId());
		else
			res = this.create();
		
		res.setName(veterinaryForm.getName());
		res.setSurname(veterinaryForm.getSurname());
		res.setEmail(veterinaryForm.getEmail());
		res.setPhoneNumber(veterinaryForm.getPhoneNumber());
		res.setAddress(veterinaryForm.getAddress());
		res.getUserAccount().setUsername(veterinaryForm.getUsername());
		res.getUserAccount().setPassword(veterinaryForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}
	
	public void flush() {
		this.veterinaryRepository.flush();

	}

}
