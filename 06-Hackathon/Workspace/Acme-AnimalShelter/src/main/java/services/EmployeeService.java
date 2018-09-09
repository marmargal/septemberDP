package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.EmployeeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Boss;
import domain.Center;
import domain.Employee;
import domain.Folder;
import domain.Report;
import forms.ActorForm;
import forms.AssignEmployeeForm;

@Service
@Transactional
public class EmployeeService {

	// Managed repository

	@Autowired
	private EmployeeRepository employeeRepository;

	// Supporting services

	@Autowired
	private ReportService reportService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private BossService bossService;
	
	@Autowired
	private Validator		validator;

	// Constructors

	public EmployeeService() {
		super();
	}

	// Simple CRUD methods

	public Employee create() {
		Employee res = new Employee();

		Collection<Folder> folders = new ArrayList<Folder>();
		Collection<Report> reports = new ArrayList<Report>();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		Folder inBox = this.folderService.create();
		Folder outBox = this.folderService.create();
		Folder trash = this.folderService.create();

		authority.setAuthority(Authority.EMPLOYEE);
		userAccount.addAuthority(authority);

		inBox.setName("In Box");
		outBox.setName("Out Box");
		trash.setName("Trash Box");
		this.folderService.save(inBox);
		this.folderService.save(outBox);
		this.folderService.save(trash);
		folders.add(inBox);
		folders.add(outBox);
		folders.add(trash);

		res.setUserAccount(userAccount);
		res.setFolders(folders);
		res.setReports(reports);
		res.setBan(false);

		return res;
	}

	public Collection<Employee> findAll() {
		Collection<Employee> res;
		res = this.employeeRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Employee findOne(int employeeId) {
		Assert.isTrue(employeeId != 0);
		Employee res;
		res = this.employeeRepository.findOne(employeeId);
		Assert.notNull(res);
		return res;
	}

	public Employee save(Employee employee) {
		Employee res;

		String pass = employee.getUserAccount().getPassword();

		final Md5PasswordEncoder code = new Md5PasswordEncoder();

		pass = code.encodePassword(pass, null);

		employee.getUserAccount().setPassword(pass);

		res = this.employeeRepository.save(employee);

		return res;
	}

	public void delete(Employee employee) {
		Assert.notNull(employee);
		Assert.isTrue(employee.getId() != 0);
		Assert.isTrue(this.employeeRepository.exists(employee.getId()));

		// Eliminamos sus relaciones
		Collection<Report> reports = new ArrayList<Report>();
		reports = employee.getReports();
		for (Report report : reports) {
			this.reportService.delete(report);
		}

		this.employeeRepository.delete(employee);
	}

	// Other business methods

	public Employee findByPrincipal() {
		Employee res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.employeeRepository.findEmployeeByPrincipal(userAccount
				.getId());
		Assert.notNull(res);

		return res;
	}

	public void checkAuthority() {
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		Collection<Authority> authority = userAccount.getAuthorities();
		Assert.notNull(authority);
		Authority res = new Authority();
		res.setAuthority("EMPLOYEE");
		Assert.isTrue(authority.contains(res));
	}

	public ActorForm construct(Employee employee) {
		ActorForm res = new ActorForm();

		res.setId(employee.getId());
		res.setName(employee.getName());
		res.setSurname(employee.getSurname());
		res.setEmail(employee.getEmail());
		res.setPhoneNumber(employee.getPhoneNumber());
		res.setCenter(employee.getCenter());
		res.setAddress(employee.getAddress());
		res.setUsername(employee.getUserAccount().getUsername());

		return res;
	}

	public Employee reconstruct(ActorForm employeeForm, BindingResult binding) {
		Assert.notNull(employeeForm);

		Employee res = new Employee();

		if (employeeForm.getId() != 0)
			res = this.findOne(employeeForm.getId());
		else
			res = this.create();

		res.setCenter(employeeForm.getCenter());
		res.setName(employeeForm.getName());
		res.setSurname(employeeForm.getSurname());
		res.setEmail(employeeForm.getEmail());
		res.setPhoneNumber(employeeForm.getPhoneNumber());
		res.setAddress(employeeForm.getAddress());
		res.getUserAccount().setUsername(employeeForm.getUsername());
		res.getUserAccount().setPassword(employeeForm.getPassword());

		this.validator.validate(res, binding);

		return res;
	}
	
	public Employee reconstruct(AssignEmployeeForm assignEmployeeForm, BindingResult binding){
		Employee employee = this.findOne(assignEmployeeForm.getEmployeeId());
		employee.setCenter(assignEmployeeForm.getCenter());
		return employee;
	}

	public Collection<Employee> findByCenter(int centerId) {
		Collection<Employee> employees = new ArrayList<Employee>();
		employees = this.employeeRepository.findByCenter(centerId);
		return employees;
	}
	
	public Collection<Employee> findEmployeesByCentersBoss(){
		Boss boss = this.bossService.findByPrincipal();
		Collection<Employee> employees = new ArrayList<Employee>();
		for(Center center: boss.getCenters()){
			if(this.employeeRepository.findEmployeesByCentersBoss(center.getId())!= null)
					employees.addAll(this.employeeRepository.findEmployeesByCentersBoss(center.getId()));
		}
		return employees;
	}

	public void flush() {
		this.employeeRepository.flush();

	}
	
}
