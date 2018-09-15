package controllers.investigator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InvestigatorService;
import services.ReportService;
import controllers.AbstractController;
import domain.Investigator;
import domain.Report;
import forms.ReportForm;

@Controller
@RequestMapping("/report/investigator")
public class ReportInvestigatorController extends AbstractController {
	
	// Services ----------------------
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private InvestigatorService investigatorService;
	
	// Constructors ------------------
	public ReportInvestigatorController(){
		super();
	}
	
	// Listing ------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Investigator investigator = this.investigatorService.findByPrincipal();
		
		Collection<Report> reports = investigator.getReports();
		
		res = new ModelAndView("report/list");
		res.addObject("report",reports);
		res.addObject("requestURI","report/investigator/list.do");
		
		return res;
	}
	
	// Create
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView create(final int immigrantId) {
		ModelAndView result;
		ReportForm reportForm = new ReportForm();
		
		reportForm.setImmigrantId(immigrantId);
		result = this.createEditModelAndView(reportForm);
		
		return result;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ReportForm reportForm, final BindingResult binding){
		
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(reportForm, "report.params.error");
		}else
			try{
				Report report = this.reportService.reconstruct(reportForm, binding);
				this.reportService.save(report);
				res = new ModelAndView("redirect:/report/investigator/list.do");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(reportForm, "report.commit.error");
			}
		
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final ReportForm reportForm) {
		ModelAndView result;

		result = this.createEditModelAndView(reportForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ReportForm reportForm, final String message) {
		ModelAndView result;
		
		result = new ModelAndView("report/edit");
		result.addObject("reportForm", reportForm);
		result.addObject("message", message);

		return result;
	}
	
}
