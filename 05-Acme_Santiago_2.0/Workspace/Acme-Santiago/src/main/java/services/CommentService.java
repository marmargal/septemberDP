package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;
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
	private ConfigurationService configurationService;

	// Constructors

	public CommentService() {
		super();
	}

	// Simple CRUD methods

	public Comment create() {
		Comment res;
		res = new Comment();

		User user = userService.findByPrincipal();
		res.setUser(user);

		return res;
	}

	public Collection<Comment> findAll() {
		Collection<Comment> res;
		res = this.commentRepository.findAll();
		return res;
	}

	public Comment findOne(final int id) {
		Assert.isTrue(id != 0);
		Comment res;
		res = this.commentRepository.findOne(id);
		return res;
	}

	public Comment save(final Comment comment) {
		Assert.notNull(comment);
		Comment res;
		Collection<String> tabooWords = new ArrayList<String>();
		tabooWords = configurationService.findTabooWords();
		for (String s : tabooWords) {
			if (comment.getTitle().toLowerCase().contains(s.toLowerCase())
					|| comment.getText().toLowerCase()
							.contains(s.toLowerCase())) {
				comment.setTaboo(true);
			}
		}
		res = this.commentRepository.save(comment);
		return res;
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);
		Assert.isTrue(comment.getId() != 0);
		Assert.isTrue(this.commentRepository.exists(comment.getId()));
		this.userService.findOne(comment.getUser().getId()).getComments()
				.remove(comment);
		this.commentRepository.delete(comment);
	}

	// Other business methods

	public void flush() {
		this.commentRepository.flush();
	}

	public Collection<Comment> findCommentTaboo() {
		return commentRepository.findCommentTaboo();
	}

}
