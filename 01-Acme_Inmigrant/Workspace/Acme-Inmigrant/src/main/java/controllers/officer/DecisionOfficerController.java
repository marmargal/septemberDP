package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Application;
import domain.Decision;
import forms.DecisionForm;

import services.ApplicationService;
import services.DecisionService;
import services.OfficerService;

@Controller
@RequestMapping("/decision/office")
public class DecisionOfficerController {

	// Services -----------------------------------
	@Autowired
	private DecisionService decisionService;
	
	@Autowired
	private OfficerService officerService;
	
	@Autowired
	private ApplicationService applicationService;
	
	// Constructors --------------------------------
	public DecisionOfficerController(){
		super();
	}
	
	// Editing -------------------------------------
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId){
		this.officerService.checkAuthority();
		ModelAndView res;
		Decision decision;
		DecisionForm decisionForm;
		
		decision = this.decisionService.create(applicationId);
		decisionForm = this.decisionService.construct(decision);
		
		res = this.createEditModelAndView(decisionForm);
		return res;
	}
	
	// Ancillary methods -----------------------------
	protected ModelAndView createEditModelAndView(final DecisionForm decisionForm){
		ModelAndView res;
		
		res = this.createEditModelAndView(decisionForm,null);
		
		return res;
	}

	private ModelAndView createEditModelAndView(final DecisionForm decisionForm,
			final String message) {
		ModelAndView res;
		res = new ModelAndView("decision/edit");
		int applicationId = decisionForm.getApplicationId();
		Application application = this.applicationService.findOne(applicationId);
		Collection<Application> applications = new ArrayList<Application>();
		applications.add(application);
		
		
		res.addObject("decisionForm",decisionForm);
		res.addObject("applications",applications);
//		res.addObject("status",status);
		res.addObject("message",message);
		
		return res;
	}
}
