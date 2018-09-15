package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CompanyRepository;
import domain.Company;
import domain.Stand;

@Service
@Transactional
public class CompanyService {

	// Managed repository

	@Autowired
	private CompanyRepository companyRepository;

	// Suporting services

	@Autowired
	private BossService bossService;
	
	@Autowired
	private AdministratorService administratorService;

	// Constructors

	public CompanyService() {
		super();
	}

	// Simple CRUD methods

	public Company create() {
		bossService.checkAuthority();
		Company res = new Company();

		Collection<Stand> stands = new ArrayList<Stand>();

		res.setStands(stands);

		return res;

	}

	public Collection<Company> findAll() {
		Collection<Company> res;
		res = companyRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Company findOne(int companyId) {
		Assert.isTrue(companyId != 0);
		Company res;
		res = companyRepository.findOne(companyId);
		Assert.notNull(res);
		return res;
	}

	public Company save(Company company) {
		try {
			this.administratorService.checkAuthority();
		} catch (Exception e) {

			this.bossService.checkAuthority();
		}
		Company res;
		res = companyRepository.save(company);
		return res;
	}

	public void delete(Company company) {
		this.bossService.checkAuthority();
		Assert.notNull(company);
		Assert.isTrue(company.getId() != 0);
		Assert.isTrue(companyRepository.exists(company.getId()));
		companyRepository.delete(company);
	}

	// Other business methods

	public void flush() {
		this.companyRepository.flush();
	}

}
