package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ConfigurationRepository;
import security.Authority;
import security.LoginService;
import domain.Configuration;

@Service
@Transactional
public class ConfigurationService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private ConfigurationRepository configurationRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	// Constructor ------------------------------------------------------------

	public ConfigurationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Collection<Configuration> findAll() {
		Collection<Configuration> res;
		res = this.configurationRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Configuration findOne(int id) {
		Assert.isTrue(id != 0);
		Configuration res;
		res = this.configurationRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Configuration save(Configuration configuration) {
		this.administratorService.checkAuthority();
		Assert.notNull(configuration);
		Configuration res;
		res = this.configurationRepository.save(configuration);
		return res;
	}

	// Other business method --------------------------------------------------

	public Collection<String> findTabooWords() {
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority user = new Authority();
		user.setAuthority("USER");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Authority agent = new Authority();
		agent.setAuthority("AGENT");
		
		Assert.isTrue(authority.contains(user) || authority.contains(admin)
				|| authority.contains(agent));
		Collection<String> res = new ArrayList<String>();
		res.addAll(this.configurationRepository.findTabooWords());
		return res;
	}

	public void flush() {
		this.configurationRepository.flush();
	}
}
