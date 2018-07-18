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
import forms.OfficerForm;

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
		OfficerForm officerForm = new OfficerForm();
		
		officer = this.officerService.create();
		
		officerForm = officerService.construct(officer);

		res = this.createEditModelAndView(officerForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final OfficerForm officerForm, final BindingResult binding) {
		ModelAndView res;
		Officer officer;

		if (binding.hasErrors())
			res = this.createEditModelAndView(officerForm, "officer.params.error");
		else if (!officerForm.getRepeatPassword().equals(officerForm.getPassword()))
			res = this.createEditModelAndView(officerForm, "officer.commit.errorPassword");
		else if (officerForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(officerForm, "officer.params.errorTerms");
		} else
			try {
				officer = officerService.reconstruct(officerForm, binding);
				this.officerService.save(officer);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(officerForm, "officer.commit.error");
			}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final OfficerForm officerForm) {
		ModelAndView result;

		result = this.createEditModelAndView(officerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final OfficerForm officerForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("officer/register");
		result.addObject("officerForm", officerForm);
		result.addObject("message", message);

		return result;
	}
}
