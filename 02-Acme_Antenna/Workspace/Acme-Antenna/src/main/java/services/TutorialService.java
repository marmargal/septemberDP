package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TutorialRepository;
import domain.Actor;
import domain.Tutorial;

@Service
@Transactional
public class TutorialService {

	// Managed repository

	@Autowired
	private TutorialRepository tutorialRepository;

	// Suporting services

	@Autowired
	private ActorService actorService;

	// Constructors

	public TutorialService() {
		super();
	}

	// Simple CRUD methods

	public Tutorial create() {
		Tutorial res;
		res = new Tutorial();

		Date moment = new Date(System.currentTimeMillis() - 1000);
		String title = "title";
		String text = "text";
		String pictures = "http://www.google.es";

		Actor actor;
		actor = this.actorService.findByPrincipal();

		res.setMoment(moment);
		res.setText(text);
		res.setTitle(title);
		res.setPictures(pictures);
		res.setActor(actor);

		return res;
	}

	public Collection<Tutorial> findAll() {
		Collection<Tutorial> res;
		res = this.tutorialRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Tutorial findOne(final int id) {
		Assert.isTrue(id != 0);
		Tutorial res;
		res = this.tutorialRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}

	public Tutorial save(final Tutorial tutorial) {
		Assert.isTrue(tutorial.getActor().equals(
				this.actorService.findByPrincipal()));
		Assert.notNull(tutorial);
		Tutorial res;
		res = this.tutorialRepository.save(tutorial);
		return res;
	}

	public void delete(Tutorial tutorial) {
		Assert.notNull(tutorial);
		Assert.isTrue(tutorial.getId() != 0);
		Assert.isTrue(this.tutorialRepository.exists(tutorial.getId()));
		this.tutorialRepository.delete(tutorial);
	}

	// Other business method --------------------------------------------------
}
