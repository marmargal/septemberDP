package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ClientRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Client;


@Service
@Transactional
public class ClientService {

	// Managed repository

	@Autowired
	private ClientRepository clientRepository;
	
	// Supporting services
	
	
	// Constructors

	public ClientService() {
		super();
	}
	
	// Simple CRUD methods

	public Client create() {
		Client res = new Client();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		authority.setAuthority(Authority.CLIENT);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		
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
	
	

}
