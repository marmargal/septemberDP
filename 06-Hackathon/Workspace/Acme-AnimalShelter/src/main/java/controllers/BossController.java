package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BossService;
import domain.Boss;
import forms.ActorForm;

@Controller
@RequestMapping("/boss")
public class BossController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private BossService bossService;

	// Constructors ---------------------------------------------------------

	public BossController() {
		super();
	}

	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Boss boss = this.bossService.findByPrincipal();
		ActorForm bossForm = this.bossService.construct(boss);
		
		res = createEditModelAndViewEdit(bossForm);
		res.addObject("actrForm", bossForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm bossForm, final BindingResult binding) {
		ModelAndView res;
		Boss boss;

		if (binding.hasErrors())
			res = this.createEditModelAndViewEdit(bossForm, "actor.params.error");
		else if (!bossForm.getRepeatPassword().equals(bossForm.getPassword()))
			res = this.createEditModelAndViewEdit(bossForm, "actor.commit.errorPassword");
		else
			try {
				boss = bossService.reconstruct(bossForm, binding);
				this.bossService.save(boss);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewEdit(bossForm, "actor.commit.error");
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
		result.addObject("requestURI","boss/edit.do");

		return result;
	}
}
