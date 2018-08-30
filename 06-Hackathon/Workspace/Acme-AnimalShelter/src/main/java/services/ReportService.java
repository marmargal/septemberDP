package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.ReportRepository;
import domain.Report;


@Service
@Transactional
public class ReportService {

	// Managed repository

	@Autowired
	private ReportRepository reportRepository;

	// Suporting services

	// Constructors

	public ReportService() {
		super();
	}

	// Simple CRUD methods

	public Report create() {
		Report res = new Report();
		
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
		Report res;
		res = reportRepository.findOne(reportId);
		Assert.notNull(res);
		return res;
	}

	public Report save(Report report) {
		Report res;
		res = reportRepository.save(report);
		return res;
	}

	public void delete(Report report) {
		Assert.notNull(report);
		Assert.isTrue(report.getId() != 0);
		Assert.isTrue(reportRepository.exists(report.getId()));
		reportRepository.delete(report);
	}

	// Other business methods
	
	

}