package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ImmigrantService;
import domain.Immigrant;
import forms.ActorForm;

@Controller
@RequestMapping("/immigrant")
public class RegisterImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ImmigrantService immigrantService;

	// Constructors ---------------------------------------------------------

	public RegisterImmigrantController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		ActorForm actorForm = new ActorForm();
		
		res = this.createEditModelAndView(actorForm);

		return res;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("actorForm") ActorForm actorForm,
			final BindingResult binding) {
		ModelAndView res;
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(actorForm, "actor.params.error");
		else if (!actorForm.getRepeatPassword().equals(actorForm.getPassword()))
			res = this.createEditModelAndView(actorForm, "actor.commit.errorPassword");
		else if (actorForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(actorForm, "actor.params.errorTerms");
		} else
			try{
				Immigrant immigrant = this.immigrantService.reconstruct(actorForm, binding);
				this.immigrantService.save(immigrant);
				res = new ModelAndView("redirect:/");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(actorForm, "actor.commit.error");
			}
		
		return res;
	}
	
	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Immigrant immigrant = this.immigrantService.findByPrincipal();
		ActorForm immigrantForm = this.immigrantService.construct(immigrant);
		
		res = createEditModelAndViewEdit(immigrantForm);
		res.addObject("actrForm", immigrantForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@ModelAttribute("actorForm") ActorForm actorForm,
			final BindingResult binding) {
		ModelAndView res;
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(actorForm, "actor.params.error");
		else if (!actorForm.getRepeatPassword().equals(actorForm.getPassword()))
			res = this.createEditModelAndView(actorForm, "actor.commit.errorPassword");
		else 
			try{
				Immigrant immigrant = this.immigrantService.reconstruct(actorForm, binding);
				this.immigrantService.save(immigrant);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(actorForm, "actor.commit.error");
			}
		
		return res;
	}

	

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm actorForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI","immigrant/register.do");

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
		result.addObject("requestURI","immigrant/edit.do");

		return result;
	}
}
