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

import repositories.ClientRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Application;
import domain.Client;
import domain.Folder;
import forms.ActorForm;


@Service
@Transactional
public class ClientService {

	// Managed repository

	@Autowired
	private ClientRepository clientRepository;
	
	// Supporting services
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private Validator		validator;
	
	// Constructors

	public ClientService() {
		super();
	}
	
	// Simple CRUD methods

	public Client create() {
		Client res = new Client();
		
		Collection<Application> applications = new ArrayList<Application>();
		Collection<Folder> folders = new ArrayList<Folder>();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Folder inBox = this.folderService.create();
		Folder outBox = this.folderService.create();
		Folder trash = this.folderService.create();
		
		authority.setAuthority(Authority.CLIENT);
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
		res.setApplications(applications);
		res.setFolders(folders);
		res.setBan(false);

		return res;
	}

	public Collection<Client> findAll() {
		Collection<Client> res;
		res = this.clientRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Client findOne(int clientId) {
		Assert.isTrue(clientId != 0);
		Client res;
		res = this.clientRepository.findOne(clientId);
		Assert.notNull(res);
		return res;
	}

	public Client save(Client client) {
		Client res;
		
		String pass = client.getUserAccount().getPassword();
		
		final Md5PasswordEncoder code = new Md5PasswordEncoder();
		
		pass = code.encodePassword(pass, null);
		
		client.getUserAccount().setPassword(pass);

		res = this.clientRepository.save(client);
		
		return res;
	}
	
	public Client saveForApplication(Client client) {
		Client res;

		res = this.clientRepository.save(client);
		
		return res;
	}
	
	public void delete(Client client) {
		Assert.notNull(client);
		Assert.isTrue(client.getId() != 0);
		Assert.isTrue(this.clientRepository.exists(client.getId()));
		this.clientRepository.delete(client);
	}

	// Other business methods

	public Client findByPrincipal() {
		Client res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.clientRepository.findClientByPrincipal(userAccount.getId());
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("CLIENT");
		Assert.isTrue(authority.contains(res));
	}	
	
	public ActorForm construct(Client client){
		ActorForm res = new ActorForm();
		
		res.setId(client.getId());
		res.setName(client.getName());
		res.setSurname(client.getSurname());
		res.setEmail(client.getEmail());
		res.setPhoneNumber(client.getPhoneNumber());
		res.setAddress(client.getAddress());
		res.setUsername(client.getUserAccount().getUsername());
		
		return res;
	}
	
	public Client reconstruct(ActorForm clientForm, BindingResult binding){
		Assert.notNull(clientForm);
		
		Client res = new Client();

		if (clientForm.getId() != 0)
			res = this.findOne(clientForm.getId());
		else
			res = this.create();
		
		res.setName(clientForm.getName());
		res.setSurname(clientForm.getSurname());
		res.setEmail(clientForm.getEmail());
		res.setPhoneNumber(clientForm.getPhoneNumber());
		res.setAddress(clientForm.getAddress());
		res.getUserAccount().setUsername(clientForm.getUsername());
		res.getUserAccount().setPassword(clientForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}

	public void flush() {
		this.clientRepository.flush();

	}
	
}
