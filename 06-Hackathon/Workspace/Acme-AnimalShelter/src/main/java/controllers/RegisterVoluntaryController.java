package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.VoluntaryService;
import domain.Voluntary;
import forms.ActorForm;

@Controller
@RequestMapping("/voluntary")
public class RegisterVoluntaryController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private VoluntaryService voluntaryService;
	
	@Autowired
	private ActorService actorService;

	// Constructors ---------------------------------------------------------

	public RegisterVoluntaryController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		ActorForm voluntaryForm = new ActorForm();
		
		res = this.createEditModelAndView(voluntaryForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm voluntaryForm, final BindingResult binding) {
		ModelAndView res;
		Voluntary voluntary;
		boolean validPhone = this.actorService.validPhoneNumber(voluntaryForm.getPhoneNumber());

		if (binding.hasErrors())
			res = this.createEditModelAndView(voluntaryForm, "actor.params.error");
		else if (!voluntaryForm.getRepeatPassword().equals(voluntaryForm.getPassword()))
			res = this.createEditModelAndView(voluntaryForm, "actor.commit.errorPassword");
		else if (voluntaryForm.getTermsAndConditions() == false) 
			res = this.createEditModelAndView(voluntaryForm, "actor.params.errorTerms");
		else if (!validPhone && (voluntaryForm.getAceptPhoneNumberConditions() == null || voluntaryForm.getAceptPhoneNumberConditions() == false)) {
			voluntaryForm.setAceptPhoneNumberConditions(false);
			res = this.createEditModelAndView(voluntaryForm, "actor.params.mustAcceptPhoneNumber");
		} else
			try {
				voluntary = voluntaryService.reconstruct(voluntaryForm, binding);
				this.voluntaryService.save(voluntary);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(voluntaryForm, "actor.commit.error");
			}

		return res;
	}
	
	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Voluntary voluntary = this.voluntaryService.findByPrincipal();
		ActorForm voluntaryForm = this.voluntaryService.construct(voluntary);
		
		res = createEditModelAndViewEdit(voluntaryForm);
		res.addObject("actrForm", voluntaryForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm voluntaryForm, final BindingResult binding) {
		ModelAndView res;
		Voluntary voluntary;
		boolean validPhone = this.actorService.validPhoneNumber(voluntaryForm.getPhoneNumber());

		if (binding.hasErrors())
			res = this.createEditModelAndViewEdit(voluntaryForm, "actor.params.error");
		else if (!voluntaryForm.getRepeatPassword().equals(voluntaryForm.getPassword()))
			res = this.createEditModelAndViewEdit(voluntaryForm, "actor.commit.errorPassword");
		else if (!validPhone && (voluntaryForm.getAceptPhoneNumberConditions() == null || voluntaryForm.getAceptPhoneNumberConditions() == false)) {
			voluntaryForm.setAceptPhoneNumberConditions(false);
			res = this.createEditModelAndViewEdit(voluntaryForm, "actor.params.mustAcceptPhoneNumber");
		} else
			try {
				voluntary = voluntaryService.reconstruct(voluntaryForm, binding);
				this.voluntaryService.save(voluntary);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewEdit(voluntaryForm, "actor.commit.error");
			}

		return res;
	}
	

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm voluntaryForm) {
		ModelAndView result;

		result = this.createEditModelAndView(voluntaryForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm voluntaryForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", voluntaryForm);
		result.addObject("message", message);
		result.addObject("requestURI","voluntary/register.do");

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
		result.addObject("requestURI","voluntary/edit.do");

		return result;
	}
}
