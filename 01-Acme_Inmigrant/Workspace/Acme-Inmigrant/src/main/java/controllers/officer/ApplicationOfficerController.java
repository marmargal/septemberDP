package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.OfficerService;
import services.QuestionService;
import controllers.AbstractController;
import domain.Application;
import domain.Officer;

@Controller
@RequestMapping("/application/officer")
public class ApplicationOfficerController extends AbstractController{

	// Services -----------------------------------
	
	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private OfficerService officerService;
	
	
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
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView listApplications(){
		ModelAndView res;
		Officer officer = new Officer();

		Collection<Application> allApplications = new ArrayList<Application>();
		Collection<Application> applicationsSelfAssigning = new ArrayList<Application>();
		Collection<Application> findApplicationsWhitDecisionByOfficer = new ArrayList<Application>();
		

		officer = this.officerService.findByPrincipal();
		allApplications = this.applicationService.findAll();
		applicationsSelfAssigning = this.applicationService.findApplicationsSelfAssigning();
		findApplicationsWhitDecisionByOfficer = this.applicationService.findApplicationsWhitDecisionByOfficer(officer.getId());
		
		res = new ModelAndView("application/list");
		res.addObject("application",allApplications);
		res.addObject("officer",officer);
		res.addObject("applicationsSelfAssigningAllOfficer",applicationsSelfAssigning);
		res.addObject("applicationsWhitDecisionByOfficer",findApplicationsWhitDecisionByOfficer);
		res.addObject("requestURI","application/officer/assign.do");
		
		return res;
	}
	
	@RequestMapping(value="/assign",method=RequestMethod.POST, params = "assign")
	public ModelAndView save(@RequestParam int applicationId){
		ModelAndView res;
		try{
			this.applicationService.checkApplicationIsNotCloser(applicationId);
			this.officerService.checkApplicationIsNotAssign(applicationId);
			
			Officer officer = new Officer();
			Application application = new Application();
			Collection<Application> applicationOfOfficer = new ArrayList<Application>();
			
			officer = this.officerService.findByPrincipal();
			applicationOfOfficer = officer.getApplications();		
			application = this.applicationService.findOne(applicationId);
			
			applicationOfOfficer.add(application);
			
			this.officerService.saveApplications(officer, application);

			res = new ModelAndView("redirect:/application/officer/list.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/application/officer/list.do");
		}
		
		return res;
	}
	
	
//	
//	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
//	public ModelAndView save(final Officer officer, final BindingResult binding){
//		ModelAndView res;
//		
//		if(binding.hasErrors()){
//			res = this.createEditModelAndView(officer, "application.params.error");
//		}else
//			try{
//		
//				this.officerService.saveApplications(officer);
//		
//				res = new ModelAndView("redirect:/application/officer/list.do");
//				
//
//			}catch (final Throwable oops) {
//				System.out.println(oops);
//				res = this.createEditModelAndView(officer, "application.commit.error");
//			}
//		
//		return res;
//	}
//	
//	
//	protected ModelAndView createEditModelAndView(final Officer officer) {
//		ModelAndView res;
//		
//		res = this.createEditModelAndView(officer,null);
//		
//		return res;
//	}
//
//	protected ModelAndView createEditModelAndView(final Officer officer, final String message) {
//		ModelAndView res;
//		
//		res = new ModelAndView("application/select");
//		
//		res.addObject("officer", officer);
//		res.addObject("message",message);
//		res.addObject("requestURI","application/officer/edit.do");
//		
//		return res;
//	}
	
	
	
}
