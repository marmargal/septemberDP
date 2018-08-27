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

import repositories.AgentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Advertisement;
import domain.Agent;
import forms.AgentForm;

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
		return res;
	}

	public Agent findOne(final int id) {
		Assert.isTrue(id != 0);
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
		res = agentRepository.findAgentByUserAccountId(agentAccount.getId());
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

	public AgentForm reconstruct(final AgentForm agentForm,
			final BindingResult binding) {
		Agent res;
		AgentForm agentFinal = null;
		res = agentForm.getAgent();
		if (res.getId() == 0) {
			Collection<Advertisement> advertisements;
			UserAccount userAccount;
			Authority authority;
			userAccount = agentForm.getAgent().getUserAccount();
			authority = new Authority();
			advertisements = new ArrayList<Advertisement>();

			agentForm.getAgent().setUserAccount(userAccount);
			authority.setAuthority(Authority.AGENT);
			userAccount.addAuthority(authority);
			agentForm.getAgent().setAdvertisements(advertisements);

			agentFinal = agentForm;
		} else {
			res = this.agentRepository.findOne(agentForm.getAgent().getId());
			agentForm.getAgent().setId(res.getId());
			agentForm.getAgent().setVersion(res.getVersion());
			agentForm.getAgent().setUserAccount(res.getUserAccount());
			agentForm.getAgent().setAdvertisements(res.getAdvertisements());

			agentFinal = agentForm;
		}
		this.validator.validate(agentFinal, binding);
		return agentFinal;
	}

	public Agent reconstruct(final Agent agent, final BindingResult binding) {
		Agent res;
		Agent agentFinal;
		if (agent.getId() == 0) {
			UserAccount userAccount;
			Authority authority;
			userAccount = agent.getUserAccount();
			agent.setUserAccount(userAccount);
			authority = new Authority();
			authority.setAuthority(Authority.AGENT);
			userAccount.addAuthority(authority);
			String password = "";
			password = agent.getUserAccount().getPassword();
			agent.getUserAccount().setPassword(password);
			agentFinal = agent;
		} else {
			res = this.agentRepository.findOne(agent.getId());
			agent.setId(res.getId());
			agent.setVersion(res.getVersion());
			agent.setUserAccount(res.getUserAccount());
			agent.getUserAccount().setPassword(
					agent.getUserAccount().getPassword());
			agent.getUserAccount().setAuthorities(
					agent.getUserAccount().getAuthorities());
			agentFinal = agent;
		}
		this.validator.validate(agentFinal, binding);
		return agentFinal;
	}

}
