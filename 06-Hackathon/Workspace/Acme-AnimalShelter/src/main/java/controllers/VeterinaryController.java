package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.VeterinaryService;
import domain.Veterinary;
import forms.ActorForm;

@Controller
@RequestMapping("/veterinary")
public class VeterinaryController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private VeterinaryService veterinaryService;
	
	@Autowired
	private ActorService actorService;

	// Constructors ---------------------------------------------------------

	public VeterinaryController() {
		super();
	}

	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Veterinary veterinary = this.veterinaryService.findByPrincipal();
		ActorForm veterinaryForm = this.veterinaryService.construct(veterinary);
		
		res = createEditModelAndViewEdit(veterinaryForm);
		res.addObject("actrForm", veterinaryForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm veterinaryForm, final BindingResult binding) {
		ModelAndView res;
		Veterinary veterinary;
		boolean validPhone = this.actorService.validPhoneNumber(veterinaryForm.getPhoneNumber());

		if (binding.hasErrors())
			res = this.createEditModelAndViewEdit(veterinaryForm, "actor.params.error");
		else if (!veterinaryForm.getRepeatPassword().equals(veterinaryForm.getPassword()))
			res = this.createEditModelAndViewEdit(veterinaryForm, "actor.commit.errorPassword");
		else if (!validPhone && (veterinaryForm.getAceptPhoneNumberConditions() == null || veterinaryForm.getAceptPhoneNumberConditions() == false)) {
			veterinaryForm.setAceptPhoneNumberConditions(false);
			res = this.createEditModelAndViewEdit(veterinaryForm, "actor.params.mustAcceptPhoneNumber");
		} else
			try {
				veterinary = veterinaryService.reconstruct(veterinaryForm, binding);
				this.veterinaryService.save(veterinary);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewEdit(veterinaryForm, "actor.commit.error");
			}

		return res;
	}
	

	// Ancillary methods --------------------------------------------------

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
		result.addObject("requestURI","veterinary/edit.do");

		return result;
	}
}
