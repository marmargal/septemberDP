package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MedicalReportRepository;
import security.Authority;
import security.LoginService;
import domain.MedicalReport;
import domain.Pet;
import domain.Veterinary;

@Service
@Transactional
public class MedicalReportService {

	// Managed repository

	@Autowired
	private MedicalReportRepository medicalReportRepository;

	// Suporting services

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private PetService petService;

	@Autowired
	private VeterinaryService veterinaryService;

	// Constructors

	public MedicalReportService() {
		super();
	}

	// Simple CRUD methods

	public MedicalReport create(int petId) {
		this.veterinaryService.checkAuthority();
		MedicalReport res = new MedicalReport();

		Pet pet = this.petService.findOne(petId);
		Veterinary veterinary = this.veterinaryService.findByPrincipal();

		res.setDate(new Date(System.currentTimeMillis() - 1));
		res.setVeterinary(veterinary);
		res.setPet(pet);

		return res;

	}

	public Collection<MedicalReport> findAll() {
		Collection<MedicalReport> res;
		res = medicalReportRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public MedicalReport findOne(int medicalReportId) {
		Assert.isTrue(medicalReportId != 0);
		MedicalReport res;
		res = medicalReportRepository.findOne(medicalReportId);
		Assert.notNull(res);
		return res;
	}

	public MedicalReport save(MedicalReport medicalReport) {
		MedicalReport res;
		res = medicalReportRepository.save(medicalReport);
		return res;
	}

	public void delete(MedicalReport medicalReport) {
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority employee = new Authority();
		employee.setAuthority("EMPLOYEE");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Authority boss = new Authority();
		boss.setAuthority("BOSS");
		Assert.isTrue(authority.contains(employee) || authority.contains(admin)|| authority.contains(boss));
		// Assert.isTrue(medicalReport.getVeterinary().isBan());
		Assert.notNull(medicalReport);
		Assert.isTrue(medicalReport.getId() != 0);
		Assert.isTrue(medicalReportRepository.exists(medicalReport.getId()));

		Pet pet = medicalReport.getPet();
		pet.setMedicalReport(null);
		this.petService.save(pet);

		medicalReportRepository.delete(medicalReport);
	}

	// Other business methods

	public Collection<MedicalReport> findMedicalReportsVeterinaryBanned() {
		Collection<MedicalReport> medicalReports = new ArrayList<MedicalReport>();
		medicalReports = this.medicalReportRepository
				.findMedicalReportsVeterinaryBanned();
		return medicalReports;
	}

	public void flush() {
		this.medicalReportRepository.flush();

	}

}
