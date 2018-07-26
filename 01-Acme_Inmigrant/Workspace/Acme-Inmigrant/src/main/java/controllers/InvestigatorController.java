package controllers;

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
@RequestMapping("/investigator")
public class InvestigatorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private InvestigatorService investigatorService;

	// Constructors ---------------------------------------------------------

	public InvestigatorController() {
		super();
	}

	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Investigator investigator = this.investigatorService.findByPrincipal();
		ActorForm investigatorForm = this.investigatorService.construct(investigator);
		
		res = createEditModelAndViewEdit(investigatorForm);
		res.addObject("actrForm", investigatorForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm investigatorForm, final BindingResult binding) {
		ModelAndView res;
		Investigator investigator;

		if (binding.hasErrors())
			res = this.createEditModelAndView(investigatorForm, "actor.params.error");
		else if (!investigatorForm.getRepeatPassword().equals(investigatorForm.getPassword()))
			res = this.createEditModelAndView(investigatorForm, "actor.commit.errorPassword");
		else
			try {
				investigator = investigatorService.reconstruct(investigatorForm, binding);
				this.investigatorService.save(investigator);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				System.out.println(oops);
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
		result.addObject("requestURI","investigator/register.do");

		return result;
	}
	
	protected ModelAndView createEditModelAndViewEdit(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndViewEdit(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewEdit(final ActorForm actorForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI","investigator/edit.do");

		return result;
	}
}
