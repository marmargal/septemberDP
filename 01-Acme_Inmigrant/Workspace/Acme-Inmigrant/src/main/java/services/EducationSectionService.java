package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationSectionRepository;
import domain.Application;
import domain.EducationSection;
import domain.Immigrant;

@Service
@Transactional
public class EducationSectionService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private EducationSectionRepository educationSectionRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;

	// Constructors -----------------------------------------------------------

	public EducationSectionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public EducationSection create() {
		this.immigrantService.checkAuthority();
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		EducationSection res = new EducationSection();

		return res;
	}

	public Collection<EducationSection> findAll() {
		Collection<EducationSection> res;
		res = educationSectionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public EducationSection findOne(int educationSectionId) {
		Assert.isTrue(educationSectionId != 0);
		EducationSection res;
		res = educationSectionRepository.findOne(educationSectionId);
		Assert.notNull(res);
		return res;
	}

	public EducationSection save(EducationSection educationSection) {
		this.immigrantService.checkAuthority();
		EducationSection res;
		
		if(educationSection.getId() != 0){
			res = educationSectionRepository.save(educationSection);
		}else{
			Application a = educationSection.getApplication();
			List<EducationSection> educationSections = new ArrayList<EducationSection>();
			educationSections = a.getEducationSection();
			educationSections.add(educationSection);
			a.setEducationSection(educationSections);
			
			res = educationSectionRepository.save(educationSection);
		}
		
		return res;
	}

	public void delete(EducationSection educationSection) {
		Assert.notNull(educationSection);
		Assert.isTrue(educationSection.getId() != 0);
		Assert.isTrue(educationSectionRepository.exists(educationSection.getId()));
		
		Application application = this.findApplicationbyEducationSection(educationSection.getId());
		List<EducationSection> cs = application.getEducationSection();
		cs.remove(educationSection);
		application.setEducationSection(cs);
		
		educationSectionRepository.delete(educationSection);
	}
	
	// Other business methods -------------------------------------------------
	
	public Application findApplicationbyEducationSection(Integer id) {
		Application res = new Application();
		
		res = educationSectionRepository.findApplicationbyEducationSection(id);
		
		return res;
	}
	
	public void flush() {
		this.educationSectionRepository.flush();
	}

}
