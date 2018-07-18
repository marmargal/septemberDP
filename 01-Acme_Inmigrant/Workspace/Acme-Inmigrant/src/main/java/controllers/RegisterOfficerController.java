package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OfficerService;
import domain.Officer;
import forms.ActorForm;

@Controller
@RequestMapping("/officer")
public class RegisterOfficerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private OfficerService officerService;

	// Constructors ---------------------------------------------------------

	public RegisterOfficerController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		Officer officer = new Officer();
		ActorForm officerForm = new ActorForm();
		
		officer = this.officerService.create();
		
		officerForm = officerService.construct(officer);

		res = this.createEditModelAndView(officerForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm officerForm, final BindingResult binding) {
		ModelAndView res;
		Officer officer;

		if (binding.hasErrors())
			res = this.createEditModelAndView(officerForm, "actor.params.error");
		else if (!officerForm.getRepeatPassword().equals(officerForm.getPassword()))
			res = this.createEditModelAndView(officerForm, "actor.commit.errorPassword");
		else if (officerForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(officerForm, "actor.params.errorTerms");
		} else
			try {
				officer = officerService.reconstruct(officerForm, binding);
				this.officerService.save(officer);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(officerForm, "actor.commit.error");
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
		result.addObject("requestURI","officer/register.do");

		return result;
	}
}
