package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InvestigatorService;
import domain.Investigator;
import forms.ActorForm;

@Controller
@RequestMapping("/investigator/administrator")
public class InvestigatorAdministratorController {

	// Services
	
		@Autowired
		private InvestigatorService investigatorService;
		
		// Supporting services

		
		// Constructors
		
		public InvestigatorAdministratorController(){
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
		public ModelAndView save(@Valid ActorForm investigatorForm, final BindingResult binding) {
			ModelAndView res;
			if (binding.hasErrors())
				res = this.createEditModelAndView(investigatorForm, "actor.params.error");
			else
				try {
					Investigator investigator = this.investigatorService.reconstruct(investigatorForm, binding);
					this.investigatorService.save(investigator); 
					res = new ModelAndView("redirect:../..");
					
				} catch (final Throwable oops) {
					res = this.createEditModelAndView(investigatorForm, "actor.commit.error");
				}
			return res;
		}
		
		// Ancillary methods --------------------------------------------------

				protected ModelAndView createEditModelAndView(final ActorForm investigatorForm) {
					ModelAndView result;

					result = this.createEditModelAndView(investigatorForm, null);

					return result;
				}

				protected ModelAndView createEditModelAndView(final ActorForm investigatorForm,
						final String message) {
					ModelAndView result;
					
					result = new ModelAndView("actor/register");
					result.addObject("actorForm", investigatorForm);
					result.addObject("message", message);
					result.addObject("requestURI", "investigator/administrator/edit.do");

					return result;
				}
}
