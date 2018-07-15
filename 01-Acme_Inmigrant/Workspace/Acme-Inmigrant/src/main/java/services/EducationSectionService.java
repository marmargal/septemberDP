package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EducationSectionRepository;
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
		EducationSection res;
		res = educationSectionRepository.save(educationSection);
		return res;
	}

	public void delete(EducationSection educationSection) {
		Assert.notNull(educationSection);
		Assert.isTrue(educationSection.getId() != 0);
		Assert.isTrue(educationSectionRepository.exists(educationSection.getId()));
		educationSectionRepository.delete(educationSection);
	}
	
	// Other business methods -------------------------------------------------

}
