package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StandRepository;
import security.Authority;
import security.LoginService;
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
	
	@Autowired
	private BossService bossService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private AdministratorService administratorService;

	// Constructors

	public StandService() {
		super();
	}

	// Simple CRUD methods

	public Stand create() {
		this.bossService.checkAuthority();
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
		
		try {
			this.administratorService.checkAuthority();
		} catch (Exception e) {
			
			this.bossService.checkAuthority();
			Assert.isTrue(stand.getCompany().getEvent().getCenter().getBoss().equals(this.bossService.findByPrincipal()));	
		}
		Assert.notNull(stand);
		Assert.isTrue(stand.getId() != 0);
		Assert.isTrue(standRepository.exists(stand.getId()));
//		stand.getEmployee().setStand(null);
		standRepository.delete(stand);
	}

	// Other business methods

	public Stand untieVoluntary(Stand stand) {
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority employee = new Authority();
		employee.setAuthority("EMPLOYEE");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Authority boss = new Authority();
		boss.setAuthority("BOSS");
		Authority voluntary = new Authority();
		voluntary.setAuthority("VOLUNTARY");
		Assert.isTrue(authority.contains(employee) || authority.contains(admin)
				|| authority.contains(boss)|| authority.contains(voluntary));

		
		
		stand.setVoluntaries(null);
		this.save(stand);
		return stand;
	}

	public void joinVoluntary(Stand stand) {
		Voluntary voluntary = voluntaryService.findByPrincipal();

		if (!stand.getVoluntaries().contains(voluntary)) {
			Collection<Voluntary> voluntaries = new ArrayList<>();
			voluntaries = stand.getVoluntaries();
			voluntaries.add(voluntary);
			stand.setVoluntaries(voluntaries);
			this.save(stand);
		} else {
			if (stand.getVoluntaries().size() < stand.getNumMaxVoluntaries()) {
				Collection<Voluntary> voluntaries = new ArrayList<>();
				voluntaries = stand.getVoluntaries();
				voluntaries.remove(voluntary);
				stand.setVoluntaries(voluntaries);
				this.save(stand);
			}
		}

	}

	public void flush() {
		this.standRepository.flush();
	}

}
