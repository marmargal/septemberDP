package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DecisionService;
import controllers.AbstractController;
import domain.Decision;

@Controller
@RequestMapping("/decision/administrator")
public class DecisionAdministratorController extends AbstractController {

	@Autowired
	private DecisionService decisionService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Decision decision;

		decision = this.decisionService.create();
		res = this.createEditModelAndView(decision);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int decisionId) {
		ModelAndView result;
		Decision decision;

		if (decisionId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.decisionService.findOne(decisionId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {

			decision = this.decisionService.findOne(decisionId);
			result = this.createEditModelAndView(decision);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Decision decision,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this
					.createEditModelAndView(decision, "decision.params.error");
		else
			try {
				this.decisionService.save(decision);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(decision,
						"decision.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Decision decision,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.decisionService.delete(decision);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this
					.createEditModelAndView(decision, "decision.commit.error");
		}
		return res;
	}

	private ModelAndView createEditModelAndView(final Decision decision) {
		ModelAndView result;

		result = this.createEditModelAndView(decision, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Decision decision,
			final String message) {
		ModelAndView result;
		Collection<Boolean> aproves = new ArrayList<>();
		aproves.add(true);
		aproves.add(false);
		result = new ModelAndView("decision/administrator/edit");
		result.addObject("decision", decision);
		result.addObject("message", message);
		result.addObject("aproves", aproves);

		result.addObject("requestUri", "decision/administrator/edit.do");

		return result;
	}
}
