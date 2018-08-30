package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CenterRepository;
import domain.Boss;
import domain.Center;
import domain.Employee;
import domain.Event;
import domain.Pet;


@Service
@Transactional
public class CenterService {

	// Managed repository

	@Autowired
	private CenterRepository centerRepository;

	// Suporting services

	// Constructors

	public CenterService() {
		super();
	}

	// Simple CRUD methods

	public Center create() {
		Center res = new Center();
		
		Collection<Pet> pets = new ArrayList<Pet>();
		Collection<Employee> employees = new ArrayList<Employee>();
		Collection<Event> events = new ArrayList<Event>();
		Boss boss = new Boss();
		
		res.setPets(pets);
		res.setEmployees(employees);
		res.setEvents(events);
		res.setBoss(boss);

		return res;

	}

	public Collection<Center> findAll() {
		Collection<Center> res;
		res = centerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Center findOne(int centerId) {
		Assert.isTrue(centerId != 0);
		Center res;
		res = centerRepository.findOne(centerId);
		Assert.notNull(res);
		return res;
	}

	public Center save(Center center) {
		Center res;
		res = centerRepository.save(center);
		return res;
	}

	public void delete(Center center) {
		Assert.notNull(center);
		Assert.isTrue(center.getId() != 0);
		Assert.isTrue(centerRepository.exists(center.getId()));
		centerRepository.delete(center);
	}

	// Other business methods
	
	

}
