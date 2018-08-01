package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import domain.Administrator;
import forms.ActorForm;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors ---------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Administrator administrator = this.administratorService.findByPrincipal();
		ActorForm administratorForm = this.administratorService.construct(administrator);
		
		res = createEditModelAndViewEdit(administratorForm);
		res.addObject("actrForm", administratorForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm administratorForm, final BindingResult binding) {
		ModelAndView res;
		Administrator administrator;

		if (binding.hasErrors())
			res = this.createEditModelAndView(administratorForm, "actor.params.error");
		else if (!administratorForm.getRepeatPassword().equals(administratorForm.getPassword()))
			res = this.createEditModelAndView(administratorForm, "actor.commit.errorPassword");
		else
			try {
				administrator = administratorService.reconstruct(administratorForm, binding);
				this.administratorService.save(administrator);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(administratorForm, "actor.commit.error");
			}

		return res;
	}
	

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm administratorForm) {
		ModelAndView result;

		result = this.createEditModelAndView(administratorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm administratorForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", administratorForm);
		result.addObject("message", message);
		result.addObject("requestURI","administrator/register.do");

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
		result.addObject("requestURI","administrator/edit.do");

		return result;
	}
}
