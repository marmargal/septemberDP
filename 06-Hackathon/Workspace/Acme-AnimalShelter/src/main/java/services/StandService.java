package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StandRepository;
import domain.Stand;
import domain.Voluntary;


@Service
@Transactional
public class StandService {

	// Managed repository

	@Autowired
	private StandRepository standRepository;

	// Suporting services
	
	@Autowired
	private VoluntaryService voluntaryService;

	// Constructors

	public StandService() {
		super();
	}

	// Simple CRUD methods

	public Stand create() {
		Stand res = new Stand();
		
		return res;

	}

	public Collection<Stand> findAll() {
		Collection<Stand> res;
		res = standRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Stand findOne(int standId) {
		Assert.isTrue(standId != 0);
		Stand res;
		res = standRepository.findOne(standId);
		Assert.notNull(res);
		return res;
	}

	public Stand save(Stand stand) {
		Stand res;
		res = standRepository.save(stand);
		return res;
	}

	public void delete(Stand stand) {
		Assert.notNull(stand);
		Assert.isTrue(stand.getId() != 0);
		Assert.isTrue(standRepository.exists(stand.getId()));
		standRepository.delete(stand);
	}

	// Other business methods
	
	public Stand untieVoluntary(Stand stand){
		stand.setVoluntary(null);
		this.save(stand);
		return stand;
	}
	
	public void joinVoluntary(Stand stand) {
		if (stand.getVoluntary() == null) {
			Voluntary voluntary = voluntaryService.findByPrincipal();
			stand.setVoluntary(voluntary);
			this.save(stand);
		}
	}
	
}
