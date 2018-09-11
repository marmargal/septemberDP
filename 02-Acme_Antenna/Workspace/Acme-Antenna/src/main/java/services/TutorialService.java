package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TutorialRepository;
import domain.Actor;
import domain.Tutorial;
import forms.TutorialForm;

@Service
@Transactional
public class TutorialService {

	// Managed repository

	@Autowired
	private TutorialRepository tutorialRepository;

	// Suporting services
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Validator validator;

	// Constructors

	public TutorialService() {
		super();
	}

	// Simple CRUD methods

	public Tutorial create() {
		userService.checkAuthority();
		Tutorial res;
		res = new Tutorial();

		Date moment = new Date(System.currentTimeMillis() - 1000);

		Actor actor;
		actor = this.actorService.findByPrincipal();

		res.setMoment(moment);
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
		Assert.notNull(tutorial);
		Assert.isTrue(this.actorService.findByPrincipal()
				.equals(tutorial.getActor()));
		
		Tutorial res;
		res = this.tutorialRepository.save(tutorial);
		return res;
	}
	
	public Tutorial saveForComment(final Tutorial tutorial) {
		Assert.notNull(tutorial);
		
		Tutorial res;
		res = this.tutorialRepository.save(tutorial);
		return res;
	}

	public void delete(Tutorial tutorial) {
		administratorService.checkAuthority();
		Assert.notNull(tutorial);
		Assert.isTrue(tutorial.getId() != 0);
//		Assert.isTrue(this.tutorialRepository.exists(tutorial.getId()));
		
		tutorial.getActor().getTutorials().remove(tutorial);
		
		if(tutorial.getComments().size()>0)
			this.commentService.deleteAll(tutorial.getComments());
		
		this.tutorialRepository.delete(tutorial);
	}

	
	// Other business method --------------------------------------------------
	
	public TutorialForm construct(Tutorial tutorial) {
		Assert.notNull(tutorial);
		TutorialForm res = new TutorialForm();
		
		res.setPictures(tutorial.getPictures());
		res.setId(tutorial.getId());
		res.setTitle(tutorial.getTitle());
		res.setText(tutorial.getText());
		res.setActorId(tutorial.getActor().getId());
		
		return res;
	}

	public Tutorial reconstruct(TutorialForm tutorialForm,
			BindingResult binding){
		Assert.notNull(tutorialForm);
		Tutorial res;
//		Date moment = new Date(System.currentTimeMillis() - 1000);
		
		if(tutorialForm.getId()!=0)
			res = this.findOne(tutorialForm.getId());
		else
			res = this.create();
		
		res.setTitle(tutorialForm.getTitle());
		res.setText(tutorialForm.getText());
		res.setPictures(tutorialForm.getPictures());
		
		if(binding!=null)
			this.validator.validate(res,binding);
		
		return res;
	}
	
	public void flush(){
		this.tutorialRepository.flush();
	}
}
