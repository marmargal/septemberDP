package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WorkSectionRepository;
import domain.WorkSection;
import domain.Immigrant;

@Service
@Transactional
public class WorkSectionService {
	
	// Managed repository -----------------------------------------------------

	@Autowired
	private WorkSectionRepository workSectionRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ImmigrantService immigrantService;

	// Constructors -----------------------------------------------------------

	public WorkSectionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------
	
	public WorkSection create() {
		final Immigrant immigrant = this.immigrantService.findByPrincipal();
		Assert.notNull(immigrant);
		WorkSection res = new WorkSection();

		return res;
	}

	public Collection<WorkSection> findAll() {
		Collection<WorkSection> res;
		res = workSectionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public WorkSection findOne(int workSectionId) {
		Assert.isTrue(workSectionId != 0);
		WorkSection res;
		res = workSectionRepository.findOne(workSectionId);
		Assert.notNull(res);
		return res;
	}

	public WorkSection save(WorkSection workSection) {
		WorkSection res;
		res = workSectionRepository.save(workSection);
		return res;
	}

	public void delete(WorkSection workSection) {
		Assert.notNull(workSection);
		Assert.isTrue(workSection.getId() != 0);
		Assert.isTrue(workSectionRepository.exists(workSection.getId()));
		workSectionRepository.delete(workSection);
	}
	
	// Other business methods -------------------------------------------------

}
