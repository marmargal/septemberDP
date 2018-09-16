package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.DecisionRepository;
import domain.Decision;

@Service
@Transactional
public class DecisionService {

	// Managed repository
	@Autowired
	private DecisionRepository decisionRepository;

	// Supporting services

	// Constructors
	public DecisionService() {
		super();
	}

	// Simple CRUD methods

	public Decision create() {
		
		//revisar identifier
		Decision res = new Decision();
		return res;
	}

	public Collection<Decision> findAll() {

		Collection<Decision> res;
		res = this.decisionRepository.findAll();
		return res;
	}

	public Decision findOne(final int id) {
		Assert.isTrue(id != 0);
		Decision res;
		res = this.decisionRepository.findOne(id);
		return res;
	}

	public Decision save(final Decision decision) {
		Assert.notNull(decision);
		Decision res;
		
		
		res = this.decisionRepository.save(decision);

		return res;
	}

	public void delete(Decision decision) {
		Assert.notNull(decision);
		Assert.isTrue(decision.getId() != 0);
		Assert.isTrue(this.decisionRepository.exists(decision.getId()));

		this.decisionRepository.delete(decision);
	}

	public void flush() {
		this.decisionRepository.flush();
	}

}
