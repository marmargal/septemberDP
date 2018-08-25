package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Compostela;

import repositories.CompostelaRepository;

@Service
@Transactional
public class CompostelaService {

	// Managed repository 
	@Autowired
	private CompostelaRepository compostelaRepository;
	
	// Supporting services
	
	
	// Constructors
	public CompostelaService(){
		super();
	}
	
	// Simple CRUD methods
	
	public Compostela create(){
		Compostela res = new Compostela();
		return res;
	}
	
	public Collection<Compostela> findAll(){
		Collection<Compostela> res;
		res = this.compostelaRepository.findAll();
		return res;
	}
	
	public Compostela findOne(final int id){
		Assert.isTrue(id!=0);
		Compostela res;
		res = this.compostelaRepository.findOne(id);
		return res;
	}
	
	public Compostela save(Compostela compostela){
		Assert.notNull(compostela);
		Compostela res;
		
		res = this.compostelaRepository.save(compostela);
		
		return res;
	}
	
	public void delete(Compostela compostela){
		Assert.notNull(compostela);
		Assert.isTrue(compostela.getId() != 0);
		Assert.isTrue(this.compostelaRepository.exists(compostela.getId()));
		this.compostelaRepository.delete(compostela);
	}
	
	public Collection<Compostela> findCompostelaByFinallyDecision(boolean decision){
		return this.compostelaRepository.findCompostelaByFinallyDecision(decision);
	}
}
