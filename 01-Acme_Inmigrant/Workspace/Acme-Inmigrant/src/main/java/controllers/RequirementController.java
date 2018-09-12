package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RequirementService;
import domain.Requirement;

@Controller
@RequestMapping("/requirement")
public class RequirementController extends AbstractController {
	
	// Services ----------------------
	@Autowired
	private RequirementService requirementService;
	
	// Constructors ------------------
	public RequirementController(){
		super();
	}


	// Listing
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam final int visaId){
		ModelAndView res;
		
		Collection<Requirement> requirements = this.requirementService.findRequirementByVisaId(visaId);
		
		res = new ModelAndView("requirement/list");
		res.addObject("requirement",requirements);
		res.addObject("requestURI", "requirement/list.do");
		
		return res;
	}

	
	// Display
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam final int requirementId){
		ModelAndView res;
		Requirement requirement;
	
		requirement = this.requirementService.findOne(requirementId);
		
		res = new ModelAndView("requirement/display");
		res.addObject("requirement",requirement);
		
		return res;
	}

	
	
	
	
	
	
	
	
}
