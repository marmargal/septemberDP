package services;

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

	// Constructors

	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {
		Comment res;
		res = new Comment();
		
		Date moment = new Date(System.currentTimeMillis() - 1000);
		String title = "title";
		String text = "text";
		String pictures = "http://www.google.es";
		
		Tutorial tutorial = new Tutorial();
		User user;
		user = this.userService.findByPrincipal();
		
		res.setMoment(moment);
		res.setTitle(title);
		res.setText(text);
		res.setPictures(pictures);
		res.setTutorial(tutorial);
		res.setUser(user);

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
		Assert.isTrue(comment.getUser().equals(
				this.userService.findByPrincipal()));
		Assert.notNull(comment);
		Comment res;
		res = this.commentRepository.save(comment);
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
