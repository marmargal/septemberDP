package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdvertisementRepository;
import security.Authority;
import security.LoginService;
import domain.Advertisement;
import domain.Agent;

@Service
@Transactional
public class AdvertisementService {

	// Managed repository

	@Autowired
	private AdvertisementRepository advertisementRepository;

	// Supporting services

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private ConfigurationService configurationService;

	@Autowired
	private AgentService agentService;

	// Constructors

	public AdvertisementService() {
		super();
	}

	// Simple CRUD methods

	public Advertisement create() {
		Advertisement res = new Advertisement();
		return res;
	}

	public Collection<Advertisement> findAll() {
		Collection<Advertisement> res;
		res = this.advertisementRepository.findAll();
		return res;
	}

	public Advertisement findOne(final int id) {
		Assert.isTrue(id != 0);
		Advertisement res;
		res = this.advertisementRepository.findOne(id);
		return res;
	}

	public Advertisement save(final Advertisement advertisement) {
		Assert.notNull(advertisement);
		Advertisement res;
		Collection<String> tabooWords = new ArrayList<String>();
		tabooWords = configurationService.findTabooWords();
		for (String s : tabooWords) {
			if (advertisement.getTitle().toLowerCase()
					.contains(s.toLowerCase())) {
				advertisement.setTaboo(true);
			}
		}

		res = this.advertisementRepository.save(advertisement);
		if (advertisement.getId() == 0) {
			Agent agent = this.agentService.findByPrincipal();
			Collection<Advertisement> advertisements = new ArrayList<>();
			advertisements.addAll(agent.getAdvertisements());
			advertisements.add(res);

			agent.setAdvertisements(advertisements);
			this.agentService.save(agent);
		}
		return res;
	}

	public void delete(Advertisement advertisement) {
		Assert.notNull(advertisement);
		Assert.isTrue(advertisement.getId() != 0);
		Assert.isTrue(this.advertisementRepository.exists(advertisement.getId()));
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority user = new Authority();
		user.setAuthority("USER");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Authority agentAux = new Authority();
		agentAux.setAuthority("AGENT");
		Assert.isTrue(authority.contains(user) || authority.contains(admin)
				|| authority.contains(agentAux));
		domain.Agent agent = this.findAgentByAdvertisement(advertisement
				.getId());
		Collection<Advertisement> advertisements = new ArrayList<>();
		advertisements = agent.getAdvertisements();
		advertisements.remove(advertisement);

		this.advertisementRepository.delete(advertisement);
	}

	// Other business methods

	public Collection<Advertisement> findAdvertisementByHike(int hikeId) {
		Collection<Advertisement> res;
		res = this.advertisementRepository.findAdvertisementByHike(hikeId);
		return res;
	}

	public domain.Agent findAgentByAdvertisement(int advertisementId) {
		domain.Agent res;
		res = this.advertisementRepository
				.findAgentByAdvertisement(advertisementId);
		return res;
	}

	public Collection<Advertisement> findAdvertisementTaboo() {
		this.administratorService.checkAuthority();
		return advertisementRepository.findAdvertisementTaboo();
	}
}
