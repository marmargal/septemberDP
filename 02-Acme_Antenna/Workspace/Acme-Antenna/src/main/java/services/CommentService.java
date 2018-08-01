package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
import domain.Tutorial;
import domain.User;

@Service
@Transactional
public class CommentService {

	// Managed repository

	@Autowired
	private CommentRepository commentRepository;

	// Suporting services

	@Autowired
	private UserService userService;
	
	@Autowired
	private TutorialService tutorialService;

	// Constructors

	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {
		this.userService.checkAuthority();
		Comment res;
		res = new Comment();
		
		Date moment = new Date();
		res.setMoment(moment);
		
		System.out.println("Moment de comment inicial: " + res.getMoment());

		return res;
	}
	
	public Collection<Comment> findAll() {
		Collection<Comment> res;
		res = this.commentRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Comment findOne(final int id) {
		Assert.isTrue(id != 0);
		Comment res;
		res = this.commentRepository.findOne(id);
		Assert.notNull(res);
		return res;
	}
	
	public Comment save(final Comment comment) {
		this.userService.checkAuthority();
		Assert.notNull(comment);
		
		Date moment = new Date();
		comment.setMoment(moment);
		
		User user;
		user = this.userService.findByPrincipal();
		comment.setUser(user);
		
		Comment res;
		res = this.commentRepository.saveAndFlush(comment);
		
		Collection<Comment> comments = new ArrayList<Comment>();
		comments = user.getComments();
		
		comments.add(res);
		user.setComments(comments);
		
		this.userService.save(user);
		
		Tutorial tutorial = res.getTutorial();
		
		Collection<Comment> tutorialComments = new ArrayList<Comment>();
		tutorialComments = tutorial.getComments();
		tutorialComments.add(res);
		
		tutorial.setComments(tutorialComments);

		this.tutorialService.save(tutorial);
		
		return res;
	}
	
	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		this.commentRepository.delete(comment);
	}

	// Other business method --------------------------------------------------

}
