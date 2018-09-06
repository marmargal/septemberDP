package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;
import domain.Client;
import domain.Employee;
import domain.Veterinary;
import domain.Voluntary;
import forms.ActorForm;


@Service
@Transactional
public class AdministratorService {

	// Managed repository

	@Autowired
	private AdministratorRepository administratorRepository;
	
	// Supporting services
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private VoluntaryService voluntaryService;
	
	@Autowired
	private VeterinaryService veterinaryService;
	
	@Autowired
	private Validator		validator;
	
	// Constructors

	public AdministratorService() {
		super();
	}
	
	// Simple CRUD methods

	public Administrator create() {
		Administrator res = new Administrator();
		
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		authority.setAuthority(Authority.ADMIN);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		
		return res;
	}

	public Collection<Administrator> findAll() {
		Collection<Administrator> res;
		res = this.administratorRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Administrator findOne(int administratorId) {
		Assert.isTrue(administratorId != 0);
		Administrator res;
		res = this.administratorRepository.findOne(administratorId);
		Assert.notNull(res);
		return res;
	}

	public Administrator save(Administrator administrator) {
		Administrator res;
		
		String pass = administrator.getUserAccount().getPassword();
		
		final Md5PasswordEncoder code = new Md5PasswordEncoder();
		
		pass = code.encodePassword(pass, null);
		
		administrator.getUserAccount().setPassword(pass);

		res = this.administratorRepository.save(administrator);
		
		return res;
	}
	
	public void delete(Administrator administrator) {
		Assert.notNull(administrator);
		Assert.isTrue(administrator.getId() != 0);
		Assert.isTrue(this.administratorRepository.exists(administrator.getId()));
		this.administratorRepository.delete(administrator);
	}

	// Other business methods

	public Administrator findByPrincipal() {
		Administrator res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.administratorRepository.findAdministratorByPrincipal(userAccount.getId());
		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("ADMIN");
		Assert.isTrue(authority.contains(res));
	}	
	
	public void banEmployee(int employeeId){
		this.checkAuthority();
		Employee employee = this.employeeService.findOne(employeeId);
		employee.setBan(true);
		this.employeeService.save(employee);
	}
	
	public void banClient(int clientId){
		this.checkAuthority();
		Client client = this.clientService.findOne(clientId);
		client.setBan(true);
		this.clientService.save(client);
	}
	
	public void banVoluntary(int voluntaryId){
		this.checkAuthority();
		Voluntary voluntary = this.voluntaryService.findOne(voluntaryId);
		voluntary.setBan(true);
		this.voluntaryService.save(voluntary);
	}
	
	public void banVeterinary(int veterinaryId){
		this.checkAuthority();
		Veterinary veterinary = this.veterinaryService.findOne(veterinaryId);
		veterinary.setBan(true);
		this.veterinaryService.save(veterinary);
	}
	
	public void debanEmployee(int employeeId){
		this.checkAuthority();
		Employee employee = this.employeeService.findOne(employeeId);
		employee.setBan(false);
		this.employeeService.save(employee);
	}
	
	public void debanClient(int clientId){
		this.checkAuthority();
		Client client = this.clientService.findOne(clientId);
		client.setBan(false);
		this.clientService.save(client);
	}
	
	public void debanVoluntary(int voluntaryId){
		this.checkAuthority();
		Voluntary voluntary = this.voluntaryService.findOne(voluntaryId);
		voluntary.setBan(false);
		this.voluntaryService.save(voluntary);
	}
	
	public void debanVeterinary(int veterinaryId){
		this.checkAuthority();
		Veterinary veterinary = this.veterinaryService.findOne(veterinaryId);
		veterinary.setBan(false);
		this.veterinaryService.save(veterinary);
	}
	
	public ActorForm construct(Administrator administrator){
		ActorForm res = new ActorForm();
		
		res.setId(administrator.getId());
		res.setName(administrator.getName());
		res.setSurname(administrator.getSurname());
		res.setEmail(administrator.getEmail());
		res.setPhoneNumber(administrator.getPhoneNumber());
		res.setAddress(administrator.getAddress());
		res.setUsername(administrator.getUserAccount().getUsername());
		
		return res;
	}
	
	public Administrator reconstruct(ActorForm administratorForm, BindingResult binding){
		Assert.notNull(administratorForm);
		
		Administrator res = new Administrator();

		if (administratorForm.getId() != 0)
			res = this.findOne(administratorForm.getId());
		else
			res = this.create();
		
		res.setName(administratorForm.getName());
		res.setSurname(administratorForm.getSurname());
		res.setEmail(administratorForm.getEmail());
		res.setPhoneNumber(administratorForm.getPhoneNumber());
		res.setAddress(administratorForm.getAddress());
		res.getUserAccount().setUsername(administratorForm.getUsername());
		res.getUserAccount().setPassword(administratorForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}
	
	
	// dashboard
		public Collection<Double> dataApplicationPerClient() {
			return administratorRepository.dataApplicationPerClient();
		}

		public Collection<Double> dataNoticePerVoluntary() {
			return administratorRepository.dataNoticePerVoluntary();
		}
		public Collection<Double> dataApplicationPerEmployee() {
			return administratorRepository.dataApplicationPerEmployee();
		}

		public Collection<Double> dataReportPorEmpleado() {
			return administratorRepository.dataReportPorEmpleado();
		}
		public Collection<Double> dataMedicalReportPerVeterinary() {
			return administratorRepository.dataMedicalReportPerVeterinary();
		}

		public Collection<Double> dataApplicationPerClientLastWeek() {
			Date fecha=  new Date(System.currentTimeMillis() - 604800000);
			return administratorRepository.dataApplicationPerClientLastWeek(fecha);
		}
		public Collection<Double> dataMedicalReportPerVeterinaryLastWeek() {
			return administratorRepository.dataMedicalReportPerVeterinaryLastWeek();
		}

		public Collection<Double> dataReportPerEmployeeLastWeek() {
			return administratorRepository.dataReportPerEmployeeLastWeek();
		}
		
		//-----------------------------------------------
		
		public Collection<Client> clientsWithMoreApplications() {
			return administratorRepository.clientsWithMoreApplications();
		}

		public Collection<Veterinary> veterinariesWithMoreReports() {
			return administratorRepository.veterinariesWithMoreReports();
		}
		
		//una en dos
		public Collection<Veterinary> employeesWithMoreReports() {
			return administratorRepository.employeesWithMoreReports();
		}

		public Collection<Veterinary> employeesWithLessReports() {
			return administratorRepository.employeesWithLessReports();
		}
		//
		
		public Collection<Veterinary> dataUserMore75Chirps() {
			return administratorRepository.dataUserMore75Chirps();
		}
	

}
