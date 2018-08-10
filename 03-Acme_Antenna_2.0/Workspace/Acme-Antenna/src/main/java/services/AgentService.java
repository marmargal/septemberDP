package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AgentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Agent;
import forms.ActorForm;

@Service
@Transactional
public class AgentService {

	// Managed repository
	@Autowired
	private AgentRepository agentRepository;

	// Suporting services
	@Autowired
	private Validator validator;

	// Constructors

	public AgentService() {
		super();
	}

	// Simple CRUD methods

	public Agent create() {
		Agent res = new Agent();

		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();

		authority.setAuthority("AGENT");
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		return res;
	}

	public Collection<Agent> findAll() {
		Collection<Agent> res;
		res = agentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Agent findOne(int agentId) {
		Assert.isTrue(agentId != 0);
		Agent res;
		res = agentRepository.findOne(agentId);
		Assert.notNull(res);
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

	public void delete(Agent agent) {
		Assert.notNull(agent);
		Assert.isTrue(agent.getId() != 0);
		Assert.isTrue(agentRepository.exists(agent.getId()));
		agentRepository.delete(agent);
	}

	// Other business methods

	public Agent findByPrincipal() {
		Agent res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		res = agentRepository.findAgentByUserAccountId(userAccount.getId());
//		Assert.notNull(res);
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("AGENT");
		Assert.isTrue(authority.contains(res));
	}

	public ActorForm construct(Agent agent) {
		ActorForm res = new ActorForm();

		res.setId(agent.getId());
		res.setName(agent.getName());
		res.setSurname(agent.getSurname());
		res.setPictures(agent.getPictures());
		res.setPostalAddress(agent.getPostalAddress());
		res.setPhoneNumber(agent.getPhoneNumber());
		res.setEmail(agent.getEmail());
		res.setUsername(agent.getUserAccount().getUsername());

		return res;
	}

	public Agent reconstruct(final ActorForm agentForm,
			final BindingResult binding) {
		Assert.notNull(agentForm);
		Agent res = new Agent();

		if (agentForm.getId() != 0)
			res = this.findOne(agentForm.getId());
		else
			res = this.create();

		res.setName(agentForm.getName());
		res.setSurname(agentForm.getSurname());
		res.setPictures(agentForm.getPictures());
		res.setEmail(agentForm.getEmail());
		res.setPhoneNumber(agentForm.getPhoneNumber());
		res.setPostalAddress(agentForm.getPostalAddress());
		res.getUserAccount().setUsername(agentForm.getUsername());
		res.getUserAccount().setPassword(agentForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}

}
