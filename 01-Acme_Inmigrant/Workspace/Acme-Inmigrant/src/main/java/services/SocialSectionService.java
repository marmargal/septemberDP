package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SocialSectionRepository;
import domain.Application;
import domain.Immigrant;
import domain.SocialSection;

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
		this.immigrantService.checkAuthority();
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
		this.immigrantService.checkAuthority();
		SocialSection res;
		
		if(socialSection.getId() != 0){
			res = socialSectionRepository.save(socialSection);
		}else{
			Application a = socialSection.getApplication();
			List<SocialSection> socialSections = new ArrayList<SocialSection>();
			socialSections = a.getSocialSection();
			socialSections.add(socialSection);
			a.setSocialSection(socialSections);
			
			res = socialSectionRepository.save(socialSection);
		}
		
		return res;
	}

	public void delete(SocialSection socialSection) {
		Assert.notNull(socialSection);
		Assert.isTrue(socialSection.getId() != 0);
		Assert.isTrue(socialSectionRepository.exists(socialSection.getId()));
		
		Application application = this.findApplicationbySocialSection(socialSection.getId());
		List<SocialSection> ss = application.getSocialSection();
		ss.remove(socialSection);
		application.setSocialSection(ss);
		
		socialSectionRepository.delete(socialSection);
	}
	
	// Other business methods -------------------------------------------------
	
	public Application findApplicationbySocialSection(Integer id) {
		Application res = new Application();
		
		res = socialSectionRepository.findApplicationbySocialSection(id);
		
		return res;
	}
	
	public void flush() {
		this.socialSectionRepository.flush();
	}

}
