package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CenterRepository;
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
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private PetService petService;

	// Constructors

	public CenterService() {
		super();
	}

	// Simple CRUD methods

	public Center create() {
		Center res = new Center();
		
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
		this.administratorService.checkAuthority();
		Assert.notNull(center);
		Assert.isTrue(center.getId() != 0);
		Assert.isTrue(centerRepository.exists(center.getId()));
		
		//Eliminamos sus relaciones
		Collection<Employee> employees = new ArrayList<Employee>();
		employees = this.employeeService.findByCenter(center.getId());
		for(Employee employee: employees){
			this.employeeService.delete(employee);
		}
		
		Collection<Event> events = new ArrayList<Event>(); 
		events = this.eventService.findEventByCenter(center.getId());
		for(Event event: events){
			this.eventService.delete(event);
		}
		
		Collection<Pet> pets = new ArrayList<Pet>(); 
		pets = this.petService.findPetsByCenter(center.getId());
		for(Pet pet: pets){
			this.petService.delete(pet);
		}
		
		centerRepository.delete(center);
	}

	// Other business methods
	
	public Center findByPet(int petId){
		Center center = new Center();
		center = this.centerRepository.findByPet(petId);
		return center;
	}
	
}
