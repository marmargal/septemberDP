package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import security.Authority;
import security.LoginService;
import domain.Application;
import domain.Report;

@Service
@Transactional
public class ReportService {

	// Managed repository

	@Autowired
	private ReportRepository reportRepository;

	// Suporting services

	@Autowired
	private ApplicationService applicationService;

	// Constructors

	public ReportService() {
		super();
	}

	// Simple CRUD methods

	public Report create() {
		Report res = new Report();
		res.setMakeMoment(new Date(System.currentTimeMillis() - 1000));
		return res;

	}

	public Collection<Report> findAll() {
		Collection<Report> res;
		res = reportRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Report findOne(int reportId) {
		Assert.isTrue(reportId != 0);
		Assert.isTrue(reportRepository.exists(reportId));
		Collection<Authority> authority = LoginService.getPrincipal()
				.getAuthorities();
		Assert.notNull(authority);
		Authority employee = new Authority();
		employee.setAuthority("EMPLOYEE");
		Authority admin = new Authority();
		admin.setAuthority("ADMIN");
		Authority veterinary = new Authority();
		veterinary.setAuthority("VETERINARY");
		Assert.isTrue(authority.contains(employee) || authority.contains(admin)
				|| authority.contains(veterinary));
		Report res;
		res = reportRepository.findOne(reportId);
		Assert.notNull(res);
		return res;
	}

	public Report save(Report report) {
		Assert.notNull(report);
		Report res;

		Assert.notNull(report.getApplication());
		Application application = report.getApplication();
		application.setClosed(true);
		applicationService.save(application);

		// TODO
		if (report.getSuitable()) {
			for (Application app : application.getPet().getApplication()) {
				app.setClosed(true);
			}
		}

		res = reportRepository.save(report);
		return res;
	}

	public void delete(Report report) {
		Assert.notNull(report);
		Assert.isTrue(report.getId() != 0);
		Assert.isTrue(reportRepository.exists(report.getId()));

		Application application = report.getApplication();
		application.setReport(null);
		this.applicationService.save(application);

		reportRepository.delete(report);
	}

	// Other business methods

}
