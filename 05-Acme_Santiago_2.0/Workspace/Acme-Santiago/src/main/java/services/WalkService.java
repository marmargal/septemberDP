package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.WalkRepository;
import domain.Comment;
import domain.Walk;

@Service
@Transactional
public class WalkService {

	// Managed repository
	@Autowired
	private WalkRepository walkRepository;
	
	// Supporting services
	
	
	// Constructors
	public WalkService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Walk create(){
		Walk res = new Walk();
		
		Collection<Date> daysOfEachHike = new ArrayList<Date>();
		Collection<Comment> comments = new ArrayList<Comment>();
		
		res.setDaysOfEachHike(daysOfEachHike);
		res.setComments(comments);
		
		return res;
	}
	
	public Collection<Walk> findAll(){
		Collection<Walk> res;
		res = this.walkRepository.findAll();
		return res;
	}
	
	public Walk findOne(final int id){
		Assert.isTrue(id!=0);
		Walk res;
		res = this.walkRepository.findOne(id);
		return res;
	}
	
	public Walk save(Walk walk){
		Assert.notNull(walk);
		Walk res;
		res = this.walkRepository.save(walk);
		return res;
	}
	
	public void delete(Walk walk){
		Assert.notNull(walk);
		Assert.isTrue(walk.getId() != 0);
		Assert.isTrue(this.walkRepository.exists(walk.getId()));
		this.walkRepository.delete(walk);
	}
	
	public Collection<Walk> findWalkByUser(int userId){
		return this.walkRepository.findWalkByUser(userId);
	}
}
