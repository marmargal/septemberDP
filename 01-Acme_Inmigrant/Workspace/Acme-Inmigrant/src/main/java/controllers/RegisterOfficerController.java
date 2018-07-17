package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(value = "/register_Officer", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		Officer officer = officerService.create();

		OfficerForm officerForm = new OfficerForm();
		officerForm = officerService.construct(officer);

//		res = new ModelAndView("officer/register_Officer");
//		res.addObject("officerForm", officerForm);
		
		res = this.createEditModelAndView(officerForm);

		return res;
	}

	@RequestMapping(value = "/register_Officer", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("officerForm") OfficerForm officerForm,
			final BindingResult binding) {
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(officerForm, "officer.params.error");
		}else
			try{
				Officer officer = this.officerService.reconstruct(officerForm, binding);
				this.officerService.save(officer);
				res = new ModelAndView("redirect:/");
			}catch (final Throwable oops) {
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
