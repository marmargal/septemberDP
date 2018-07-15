package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialSectionRepository;
import domain.SocialSection;
import domain.Immigrant;

@Service
@Transactional
public class SocialSectionService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private SocialSectionRepository socialSectionRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;

	// Constructors -----------------------------------------------------------

	public SocialSectionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public SocialSection create() {
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		SocialSection res = new SocialSection();

		return res;
	}

	public Collection<SocialSection> findAll() {
		Collection<SocialSection> res;
		res = socialSectionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public SocialSection findOne(int socialSectionId) {
		Assert.isTrue(socialSectionId != 0);
		SocialSection res;
		res = socialSectionRepository.findOne(socialSectionId);
		Assert.notNull(res);
		return res;
	}

	public SocialSection save(SocialSection socialSection) {
		SocialSection res;
		res = socialSectionRepository.save(socialSection);
		return res;
	}

	public void delete(SocialSection socialSection) {
		Assert.notNull(socialSection);
		Assert.isTrue(socialSection.getId() != 0);
		Assert.isTrue(socialSectionRepository.exists(socialSection.getId()));
		socialSectionRepository.delete(socialSection);
	}
	
	// Other business methods -------------------------------------------------

}
