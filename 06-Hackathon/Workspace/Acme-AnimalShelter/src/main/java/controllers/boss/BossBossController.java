/*
 * BossController.java
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

import services.BossService;
import controllers.AbstractController;
import domain.Boss;
import forms.ActorForm;

@Controller
@RequestMapping("/boss/boss")
public class BossBossController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private BossService bossService;
	
	
	// Constructors -----------------------------------------------------------

	public BossBossController() {
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
	public ModelAndView save(@Valid final ActorForm bossForm, final BindingResult binding) {
		ModelAndView res;
		Boss boss;

		if (binding.hasErrors())
			res = this.createEditModelAndView(bossForm, "actor.params.error");
		else if (!bossForm.getRepeatPassword().equals(bossForm.getPassword()))
			res = this.createEditModelAndView(bossForm, "actor.commit.errorPassword");
		else if (bossForm.getTermsAndConditions() == false) {
			res = this.createEditModelAndView(bossForm, "actor.params.errorTerms");
		} else
			try {
				boss = bossService.reconstruct(bossForm, binding);
				this.bossService.save(boss);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(bossForm, "actor.commit.error");
			}

		return res;
	}
	
	protected ModelAndView createEditModelAndView(final ActorForm bossForm) {
		ModelAndView result;

		result = this.createEditModelAndView(bossForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm bossForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", bossForm);
		result.addObject("message", message);
		result.addObject("requestURI","boss/boss/register.do");

		return result;
	}

}
