package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HandyworkerService;
import domain.Handyworker;
import forms.ActorForm;

@Controller
@RequestMapping("/handyworker")
public class RegisterHandyworkerController extends AbstractController {
	// Services -------------------------------------------------------------

	@Autowired
	private HandyworkerService handyworkerService;

	// Constructors ---------------------------------------------------------

	public RegisterHandyworkerController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;

		ActorForm handyWorkerForm = new ActorForm();

		res = this.createEditModelAndView(handyWorkerForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm handyworkerForm,
			final BindingResult binding) {
		ModelAndView res;
		Handyworker handyworker;

		if (binding.hasErrors())
			res = this.createEditModelAndView(handyworkerForm,
					"actor.params.error");
		else if (!handyworkerForm.getRepeatPassword().equals(
				handyworkerForm.getPassword()))
			res = this.createEditModelAndView(handyworkerForm,
					"actor.commit.errorPassword");
		else if (handyworkerForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(handyworkerForm,
					"actor.params.errorTerms");
		} else
			try {
				handyworker = handyworkerService.reconstruct(handyworkerForm,
						binding);
				this.handyworkerService.save(handyworker);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(handyworkerForm,
						"actor.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(
			final ActorForm handyworkerForm) {
		ModelAndView result;

		result = this.createEditModelAndView(handyworkerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final ActorForm handyworkerForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("handyworker/register");
		result.addObject("actorForm", handyworkerForm);
		result.addObject("message", message);
		result.addObject("requestURI","handyworker/register.do");

		return result;
	}
}
