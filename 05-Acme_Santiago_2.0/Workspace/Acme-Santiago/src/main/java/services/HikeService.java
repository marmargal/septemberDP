package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HikeRepository;
import domain.Comment;
import domain.Hike;

@Service
@Transactional
public class HikeService {

	// Managed repository
	@Autowired
	private HikeRepository hikeRepository;

	// Suporting services
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CommentService commentService;
	
	// Constructors

	public HikeService() {
		super();
	}

	// Simple CRUD methods

	public Hike create() {
		Hike res;
		res = new Hike();
		
		userService.findByPrincipal();

		return res;
	}

	public Collection<Hike> findAll() {
		Collection<Hike> res;
		res = this.hikeRepository.findAll();
		return res;
	}

	public Hike findOne(final int id) {
		Assert.isTrue(id != 0);
		Hike res;
		res = this.hikeRepository.findOne(id);
		return res;
	}

	public Hike save(final Hike hike) {
		Assert.notNull(hike);
		Hike res;

		res = this.hikeRepository.save(hike);
		return res;
	}

	public void delete(Hike hike) {
		Assert.notNull(hike);
		Assert.isTrue(hike.getId() != 0);
		Assert.isTrue(this.hikeRepository.exists(hike.getId()));
		
		Collection<Comment> comments = new ArrayList<Comment>();
		comments = hike.getComments();
		for (Comment comment : comments) {
			this.commentService.delete(comment);
		}
		this.hikeRepository.delete(hike);
		
	}

	// Other business methods
	
	public Collection<Hike> findHikeByRoute(int id) {
		Collection<Hike> res;
		res = this.hikeRepository.findHikeByRoute(id);
		return res;
	}

	public void flush() {
		this.hikeRepository.flush();
	}
	
	public Collection<Hike> findHikeByAdvertisement(int id){
		return this.hikeRepository.findHikeByAdvertisement(id);
	}
}
