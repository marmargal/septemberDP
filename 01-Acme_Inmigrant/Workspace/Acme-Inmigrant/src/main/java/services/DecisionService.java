package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DecisionRepository;
import domain.Application;
import domain.Decision;

@Service
@Transactional
public class DecisionService {

	// Managed repository

	@Autowired
	private DecisionRepository decisionRepository;

	// Suporting services

	// Constructors

	public DecisionService() {
		super();
	}

	// Simple CRUD methods

	public Decision create() {
		Decision res = new Decision();

		Boolean accept = false;
		Date momment = new Date(System.currentTimeMillis() - 1000);

		Application application = new Application();

		res.setAccept(accept);
		res.setMomment(momment);
		res.setApplication(application);

		return res;
	}

	public Collection<Decision> findAll() {
		Collection<Decision> res;
		res = decisionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Decision findOne(int decisionId) {
		Assert.isTrue(decisionId != 0);
		Decision res;
		res = decisionRepository.findOne(decisionId);
		Assert.notNull(res);
		return res;
	}

	public Decision save(Decision decision) {
		Decision res;
		res = decisionRepository.save(decision);
		return res;
	}

	public void delete(Decision decision) {
		Assert.notNull(decision);
		Assert.isTrue(decision.getId() != 0);
		Assert.isTrue(decisionRepository.exists(decision.getId()));
		decisionRepository.delete(decision);
	}

}
