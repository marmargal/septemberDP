package controllers.officer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.QuestionService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/officer")
public class ApplicationOfficerController extends AbstractController{

	// Services -----------------------------------
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private QuestionService questionService;
	
	
	// Constructors --------------------------------
	public ApplicationOfficerController(){
		super();
	}
	
	// Listing ------------------------------------
	@RequestMapping(value="/listAccepted",method=RequestMethod.GET)
	public ModelAndView listAccepted(){
		ModelAndView res;
		Application application = new Application();
		
		application = this.questionService.findApplicationSelfAsign();
		
		res = new ModelAndView("application/display");
		res.addObject("application",application);
		res.addObject("requestURI", "application/officer/list.do");
		
		return res;
	}
	
	@RequestMapping(value="/listRejected",method=RequestMethod.GET)
	public ModelAndView listRejected(){
		ModelAndView res;
		Application application = new Application();
		
		application = this.applicationService.findApplicationRejected();
		
		res = new ModelAndView("application/display");
		res.addObject("application",application);
		res.addObject("requestURI", "application/officer/list.do");
		
		return res;
	}
	
	@RequestMapping(value="/listNoDecision",method=RequestMethod.GET)
	public ModelAndView listNoDecision(){
		ModelAndView res;
		Application application = new Application();
		
//		application = this.applicationService.;
		
		res = new ModelAndView("application/display");
//		res.addObject("application",application);
		res.addObject("requestURI", "application/officer/list.do");
		
		return res;
	}
	
	
	
	
}
