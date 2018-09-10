package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ReportRepository;
import domain.Immigrant;
import domain.Investigator;
import domain.Report;
import forms.ReportForm;

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
	
	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private Validator validator;
	
	// Constructors

	public ReportService() {
		super();
	}

	// Simple CRUD methods
	
	public Report create(Integer immigrantId) {
		investigatorService.checkAuthority();
		Report res = new Report();
		Immigrant immigrant = new Immigrant();
		
		Investigator investigator = this.investigatorService.findByPrincipal();
		immigrant = this.immigrantService.findOne(immigrantId);
		
		res.setImmigrant(immigrant);
		res.setWriter(investigator);
		
		Assert.isTrue(immigrant.getInvestigator().equals(investigator));
		
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
		
		res = this.reportRepository.save(report);
		
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

	public Collection<Report> findReportsByInvestigatorId(int investigatorId) {
		this.officerService.checkAuthority();
		Collection<Report> res = new ArrayList<Report>();

		res.addAll(reportRepository.findReportsByInvestigatorId(investigatorId));
		Assert.notNull(res);
		return res;
	}
	
	public ReportForm construct(Report report){
		ReportForm res = new ReportForm();
		
		res.setId(report.getId());
		res.setText(report.getText());
		res.setPicture(report.getPicture());
		res.setImmigrantId(report.getImmigrant().getId());
		
		return res;
	}
	
	public Report reconstruct(ReportForm reportForm, BindingResult binding){
		Assert.notNull(reportForm);
		Report res = new Report();
		
		if (reportForm.getId() != 0)
			res = this.findOne(reportForm.getId());
		else
			res = this.create(reportForm.getImmigrantId());
		
		res.setId(reportForm.getId());
		res.setText(reportForm.getText());
		res.setPicture(reportForm.getPicture());
		
		if(binding!=null)
			validator.validate(res, binding);
		
		return res;
	}
	
	public void flush() {
		this.reportRepository.flush();
	}

}
