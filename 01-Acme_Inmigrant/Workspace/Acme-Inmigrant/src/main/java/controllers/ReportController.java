package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ReportService;
import domain.Report;

@Controller
@RequestMapping("/report")
public class ReportController extends AbstractController {
	
	// Services ----------------------
	@Autowired
	private ReportService reportService;
	
	// Constructors ------------------
	public ReportController(){
		super();
	}


	// Display
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam final int reportId){
		ModelAndView res;
		Report report;
	
		report = this.reportService.findOne(reportId);
		
		res = new ModelAndView("report/display");
		res.addObject("report",report);
		
		return res;
	}
}
