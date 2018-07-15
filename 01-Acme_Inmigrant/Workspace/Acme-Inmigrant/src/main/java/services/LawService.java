package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LawRepository;
import domain.Law;
import domain.Requirement;

@Service
@Transactional
public class LawService {

	// Managed repository
	
	@Autowired
	private LawRepository lawRepository;
	
	// Suporting services
	
	// Constructors
	
	public LawService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Law create(){
		Law res;
		res = new Law();
		
		String title = "title";
		String text = "text";
		Date enactmentDate = new Date(System.currentTimeMillis()-1000);
		Date abrogationTime = new Date(System.currentTimeMillis()-100);
		
		List<Requirement> requirement;
		
		requirement = new ArrayList<Requirement>();
		
		res.setTitle(title);
		res.setText(text);
		res.setEnactmentDate(enactmentDate);
		res.setAbrogationTime(abrogationTime);
		res.setRequirement(requirement);
		
		return res;
	}
	
	public Collection<Law> findAll(){
		Collection<Law> res;
		res = lawRepository.findAll();
		Assert.notNull(res);
		return res;
	}
	
	public Law findOne(int lawId){
		Assert.isTrue(lawId != 0);
		Law res;
		res = lawRepository.findOne(lawId);
		Assert.notNull(res);
		return res;
	}
	
	public Law save(Law law){
		Law res;
		res = lawRepository.save(law);
		return res;
	}
	
	public void delete(Law law){
		Assert.notNull(law);
		Assert.isTrue(law.getId() != 0);
		Assert.isTrue(lawRepository.exists(law.getId()));
		lawRepository.delete(law);
	}
	
}
