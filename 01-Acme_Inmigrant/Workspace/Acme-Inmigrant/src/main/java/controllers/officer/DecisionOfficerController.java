package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Application;
import domain.Decision;
import domain.Officer;
import forms.DecisionForm;

import services.ApplicationService;
import services.DecisionService;
import services.OfficerService;

@Controller
@RequestMapping("/decision/officer")
public class DecisionOfficerController extends AbstractController{

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
	
	// Display ------------------------------------
		@RequestMapping(value="/display",method=RequestMethod.GET)
		public ModelAndView display(@RequestParam final int decisionId){
			ModelAndView res;
			Decision decision;
			
			Officer officerPrincipal = this.officerService.findByPrincipal();
			
			decision = this.decisionService.findOne(decisionId);
			
			res = new ModelAndView("decision/display");
			if(officerPrincipal.equals(decision.getApplication().getOfficer())){
				res.addObject("decision",decision);
				res.addObject("requestURI", "decision/officer/display.do");
			}else{
				try{
					res = new ModelAndView("redirect:/application/officer/list.do");
				}catch(final Throwable oops) {
					res.addObject("decision",decision);
					res.addObject("message","decision.commit.error");
					res.addObject("requestURI", "decision/officer/display.do");
				}
			}
//			res.addObject("requestURI", "decision/display.do");
			
			return res;
		}
	
	// Create --------------------------------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam final int applicationId){
//		Assert.isTrue(officerService.findByPrincipal().
//				equals(applicationService.findOne(applicationId).getOfficer()));
		
		ModelAndView res;
		Decision decision;
		DecisionForm decisionForm;
		
		Officer officerPrincipal = this.officerService.findByPrincipal();

		
		decision = this.decisionService.create(applicationId);
		decisionForm = this.decisionService.construct(decision);
		
		if(officerPrincipal.equals(decision.getApplication().getOfficer()))
			res = this.createEditModelAndView(decisionForm);
		else
			res = new ModelAndView("redirect:/application/officer/list.do");
		
		return res;
	}
	
	// Editing -------------------------------------
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int decisionId){
//		Assert.isTrue(officerService.findByPrincipal().
//				equals(decisionService.findOne(decisionId).getApplication().getOfficer()));
		ModelAndView res;
		Decision decision;
		DecisionForm decisionForm;
		
		Officer officerPrincipal = this.officerService.findByPrincipal();
		
		decision = this.decisionService.findOne(decisionId);
		decisionForm = this.decisionService.construct(decision);
		
		if(officerPrincipal.equals(decision.getApplication().getOfficer()))
			res = this.createEditModelAndView(decisionForm);
		else
			res = new ModelAndView("redirect:/application/officer/list.do");
		
		return res;
	}
	
	// Save ------------------------------------------
	@RequestMapping(value="/edit", method=RequestMethod.POST, params ="save")
	public ModelAndView save(@Valid final DecisionForm decisionForm, final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors())
			res = this.createEditModelAndView(decisionForm, "decision.params.error");
		else{
			try{
				Decision decision = this.decisionService.reconstruct(decisionForm, binding);
				this.decisionService.save(decision);
				
				res = new ModelAndView(
						"redirect:/application/officer/list.do");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(decisionForm, "decision.commit.error");
			}
		}
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
		Collection<Boolean> status= new ArrayList<Boolean>();
		
		applications.add(application);
		status.add(true);
		status.add(false);
		
		res.addObject("decisionForm",decisionForm);
		res.addObject("applications",applications);
		res.addObject("status",status);
		res.addObject("message",message);
		res.addObject("requestURI","decision/officer/edit.do");
		
		return res;
	}
}
