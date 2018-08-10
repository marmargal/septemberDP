package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Agent;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	// Managed repository

	@Autowired
	private BannerRepository bannerRepository;

	// Suporting services
	@Autowired
	private AgentService agentService;

	// Constructors

	public BannerService() {
		super();
	}

	// Simple CRUD methods

	public Banner create() {
		Banner res;
		res = new Banner();
		
		Agent agent = agentService.findByPrincipal();
		res.setAgent(agent);

		return res;
	}

	public Collection<Banner> findAll() {
		Collection<Banner> res;
		res = this.bannerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Banner findOne(final int id) {
		Assert.isTrue(id != 0);
		Banner res;
		res = this.bannerRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Banner save(final Banner banner) {
		Assert.notNull(banner);
		Banner res;

		
		res = this.bannerRepository.save(banner);
		return res;
	}

	public void delete(Banner banner) {
		Assert.notNull(banner);
		Assert.isTrue(banner.getId() != 0);
		Assert.isTrue(this.bannerRepository.exists(banner.getId()));
		this.bannerRepository.delete(banner);
	}

	// Other business method --------------------------------------------------

}
