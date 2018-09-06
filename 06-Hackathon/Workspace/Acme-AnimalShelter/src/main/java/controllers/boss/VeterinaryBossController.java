/*
 * VeterinaryController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.boss;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.VeterinaryService;
import controllers.AbstractController;
import domain.Veterinary;
import forms.ActorForm;

@Controller
@RequestMapping("/veterinary/boss")
public class VeterinaryBossController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private VeterinaryService veterinaryService;
	
	
	// Constructors -----------------------------------------------------------

	public VeterinaryBossController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		ActorForm clientForm = new ActorForm();
		
		res = this.createEditModelAndView(clientForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm veterinaryForm, final BindingResult binding) {
		ModelAndView res;
		Veterinary veterinary;

		if (binding.hasErrors())
			res = this.createEditModelAndView(veterinaryForm, "actor.params.error");
		else if (!veterinaryForm.getRepeatPassword().equals(veterinaryForm.getPassword()))
			res = this.createEditModelAndView(veterinaryForm, "actor.commit.errorPassword");
		else if (veterinaryForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(veterinaryForm, "actor.params.errorTerms");
		} else
			try {
				veterinary = veterinaryService.reconstruct(veterinaryForm, binding);
				this.veterinaryService.save(veterinary);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(veterinaryForm, "actor.commit.error");
			}

		return res;
	}
	
	protected ModelAndView createEditModelAndView(final ActorForm veterinaryForm) {
		ModelAndView result;

		result = this.createEditModelAndView(veterinaryForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm veterinaryForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", veterinaryForm);
		result.addObject("message", message);
		result.addObject("requestURI","veterinary/boss/register.do");

		return result;
	}

}
