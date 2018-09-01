package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.EmployeeRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Employee;
import domain.Folder;


@Service
@Transactional
public class EmployeeService {

	// Managed repository

	@Autowired
	private EmployeeRepository employeeRepository;
	
	// Supporting services
	
	
	// Constructors

	public EmployeeService() {
		super();
	}
	
	// Simple CRUD methods

	public Employee create() {
		Employee res = new Employee();
		
		Collection<Folder> folders = new ArrayList<Folder>();
		UserAccount userAccount = new UserAccount();
		Authority authority = new Authority();
		
		authority.setAuthority(Authority.EMPLOYEE);
		userAccount.addAuthority(authority);

		res.setUserAccount(userAccount);
		res.setFolders(folders);
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
		this.employeeRepository.delete(employee);
	}

	// Other business methods

	public Employee findByPrincipal() {
		Employee res;
		UserAccount userAccount;
		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		res = this.employeeRepository.findEmployeeByPrincipal(userAccount.getId());
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
	
	

}
