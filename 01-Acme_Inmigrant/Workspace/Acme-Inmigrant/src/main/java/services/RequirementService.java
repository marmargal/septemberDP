package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequirementRepository;
import domain.Law;
import domain.Requirement;

@Service
@Transactional
public class RequirementService {

	// Managed repository

	@Autowired
	private RequirementRepository requirementRepository;

	// Suporting services

	// Constructors

	public RequirementService() {
		super();
	}

	// Simple CRUD methods

	public Requirement create() {
		Requirement res = new Requirement();

		String title = "title";
		String description = "description";

		res.setTitle(title);
		res.setDescription(description);

		return res;
	}

	public Collection<Requirement> findAll() {
		Collection<Requirement> res;
		res = requirementRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Requirement findOne(int requirementId) {
		Assert.isTrue(requirementId != 0);
		Requirement res;
		res = requirementRepository.findOne(requirementId);
		Assert.notNull(res);
		return res;
	}

	public Requirement save(Requirement requirement) {
		Requirement res;
		res = requirementRepository.save(requirement);
		return res;
	}

	public void delete(Requirement requirement) {
		Assert.notNull(requirement);
		Assert.isTrue(requirement.getId() != 0);
		Assert.isTrue(requirementRepository.exists(requirement.getId()));
		requirementRepository.delete(requirement);
	}
}
