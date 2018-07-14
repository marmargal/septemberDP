package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Immigrant;
import domain.Investigator;
import domain.Report;

@Service
@Transactional
public class ReportService {
	
	// Managed repository

	@Autowired
	private ReportRepository reportRepository;

	// Supporting services
	
	@Autowired
	private InvestigatorService investigatorService;
	
	@Autowired
	private ImmigrantService immigrantService;
	
	// Constructors

	public ReportService() {
		super();
	}

	// Simple CRUD methods
	
	public Report create(Integer immigrantId) {
		Report res = new Report();
		Immigrant immigrant = new Immigrant();
		
		immigrant = this.immigrantService.findOne(immigrantId);
		
		res.setImmigrant(immigrant);
		
		return res;
	}

	public Collection<Report> findAll() {
		Collection<Report> res;
		res = this.reportRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Report findOne(int reportId) {
		Assert.isTrue(reportId != 0);
		Report res;
		res = this.reportRepository.findOne(reportId);
		Assert.notNull(res);
		return res;
	}

	public Report save(Report report) {
		Assert.notNull(report);
		investigatorService.checkAuthority();
		
		Report res;
		Collection<Report> reports = new ArrayList<Report>();
		
		res = this.reportRepository.save(report);
		
		Investigator investigator = this.investigatorService.findByPrincipal();
		reports.add(res);
		investigator.setReports(reports);
		
		Assert.notNull(res);
		return res;
	}

	public void delete(Report report) {
		Assert.notNull(report);
		Assert.isTrue(report.getId() != 0);
		Assert.isTrue(this.reportRepository.exists(report.getId()));
		this.reportRepository.delete(report);
	}

	// Other business methods

	public Collection<Report> findReportsByImmigrant(int immigrantId) {
		Collection<Report> res = new ArrayList<Report>();

		res.addAll(reportRepository.findReportByImmigrantId(immigrantId));
		Assert.notNull(res);
		return res;
	}

}
