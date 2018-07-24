package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OfficerService;
import controllers.AbstractController;
import domain.Officer;
import forms.ActorForm;

@Controller
@RequestMapping("/officer/administrator")
public class OfficerAdministratorController extends AbstractController{

	// Services
	
	@Autowired
	private OfficerService officerService;
	
	// Supporting services

	
	// Constructors
	
	public OfficerAdministratorController(){
		super();
	}
	
	
	// Create
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		ActorForm actorForm = new ActorForm();
		
		res = createEditModelAndView(actorForm);
		
		return res;
	}
	
	// Saving
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActorForm officerForm, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(officerForm, "actor.params.error");
		else
			try {
				Officer officer = this.officerService.reconstruct(officerForm, binding);
				this.officerService.save(officer); 
				res = new ModelAndView("redirect:../..");
				
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(officerForm, "officer.commit.error");
			}
		return res;
	}
	
	// Ancillary methods --------------------------------------------------

		protected ModelAndView createEditModelAndView(final ActorForm officerForm) {
			ModelAndView result;

			result = this.createEditModelAndView(officerForm, null);

			return result;
		}

		protected ModelAndView createEditModelAndView(final ActorForm officerForm,
				final String message) {
			ModelAndView result;
			
			result = new ModelAndView("actor/register");
			result.addObject("actorForm", officerForm);
			result.addObject("message", message);
			result.addObject("requestURI", "officer/administrator/edit.do");

			return result;
		}
}
