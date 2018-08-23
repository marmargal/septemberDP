package services;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AgentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

import domain.Agent;
import domain.Innkeeper;

@Service
@Transactional
public class AgentService {

	// Managed repository
	@Autowired
	private AgentRepository agentRepository;
	
	// Suporting services
	
	
	//Constructors
	public AgentService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Agent create(){
		Agent res = new Agent();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		authority.setAuthority("AGENT");
		userAccount.addAuthority(authority);
		
		res.setUserAccount(userAccount);
		return res;
	}
	
	public Collection<Agent> findAll(){
		Collection<Agent> res;
		res = agentRepository.findAll();
		return res;
	}
	
	public Agent findOne(final int id){
		Assert.isTrue(id!=0);
		Agent res;
		res = this.agentRepository.findOne(id);
		return res;
	}
	
	public Agent save(Agent agent) {
		Agent res;

		String pass = agent.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		agent.getUserAccount().setPassword(pass);

		res = this.agentRepository.save(agent);

		return res;

	}
	
	public Agent saveForComment(Agent agent) {
		Agent res;

		if (agent.getId() == 0) {
			String pass = agent.getUserAccount().getPassword();

			final Md5PasswordEncoder code = new Md5PasswordEncoder();

			pass = code.encodePassword(pass, null);

			agent.getUserAccount().setPassword(pass);
		}

		res = this.agentRepository.save(agent);

		return res;

	}
	
	public void delete(Agent agent) {
		Assert.notNull(agent);
		Assert.isTrue(agent.getId() != 0);
		Assert.isTrue(agentRepository.exists(agent.getId()));
		agentRepository.delete(agent);
	}
	
	
	// Other business methods
	
	public Agent findByPrincipal() {
		Agent res;
		UserAccount agentAccount;
		agentAccount = LoginService.getPrincipal();
		res = agentRepository.findAgentByUserAccountId(agentAccount
				.getId());
		return res;
	}

	public void checkAuthority() {
		UserAccount agentAccount;
		agentAccount = LoginService.getPrincipal();
		Assert.notNull(agentAccount);
		Collection<Authority> authority = agentAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("AGENT");
		Assert.isTrue(authority.contains(res));
	}

}
