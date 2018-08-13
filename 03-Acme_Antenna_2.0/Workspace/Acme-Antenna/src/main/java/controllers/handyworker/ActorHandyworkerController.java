package controllers.handyworker;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HandyworkerService;
import controllers.AbstractController;
import domain.Handyworker;

@Controller
@RequestMapping("/actor/handyworker")
public class ActorHandyworkerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private HandyworkerService handyworkerService;

	// Constructors ---------------------------------------------------------

	public ActorHandyworkerController() {
		super();
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Handyworker handyworker;

		handyworker = this.handyworkerService.findByPrincipal();
		result = this.createEditModelAndView(handyworker);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Handyworker handyworker, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(handyworker, "actor.params.error");
		else
			try {
				this.handyworkerService.save(handyworker);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(handyworker, "actor.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Handyworker handyworker) {
		ModelAndView result;

		result = this.createEditModelAndView(handyworker, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Handyworker handyworker,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("actor/handyworker/edit");
		result.addObject("actor", handyworker);
		result.addObject("message", message);
		result.addObject("requestUri", "actor/handyworker/edit.do");
		return result;

	}
}