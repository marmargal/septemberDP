package controllers.officer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;

import controllers.AbstractController;
import domain.Report;

@Controller
@RequestMapping("/report/officer")
public class ReportOfficeController extends AbstractController {
	
	// Services ----------------------
	@Autowired
	private ReportService reportService;
	
	// Constructors ------------------
	public ReportOfficeController(){
		super();
	}
	
	// Listing ------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam final int investigatorId){
		ModelAndView res;
		Collection<Report> reports;
	
		reports = this.reportService.findReportsByInvestigatorId(investigatorId);
		
		res = new ModelAndView("report/list");
		res.addObject("report",reports);
		res.addObject("requestURI","report/officer/list.do");
		
		return res;
	}
}
