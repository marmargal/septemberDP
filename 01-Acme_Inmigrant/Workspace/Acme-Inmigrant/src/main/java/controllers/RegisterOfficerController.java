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
		
		ActorForm officerForm = new ActorForm();
		
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
				System.out.println(oops);
				res = this.createEditModelAndView(officerForm, "actor.commit.error");
			}

		return res;
	}
	
	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Officer officer = this.officerService.findByPrincipal();
		ActorForm officerForm = this.officerService.construct(officer);
		
		res = createEditModelAndViewEdit(officerForm);
		res.addObject("actrForm", officerForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm officerForm, final BindingResult binding) {
		ModelAndView res;
		Officer officer;

		if (binding.hasErrors())
			res = this.createEditModelAndView(officerForm, "actor.params.error");
		else if (!officerForm.getRepeatPassword().equals(officerForm.getPassword()))
			res = this.createEditModelAndView(officerForm, "actor.commit.errorPassword");
		else
			try {
				officer = officerService.reconstruct(officerForm, binding);
				this.officerService.save(officer);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				System.out.println(oops);
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
		result.addObject("requestURI","officer/edit.do");

		return result;
	}
}
