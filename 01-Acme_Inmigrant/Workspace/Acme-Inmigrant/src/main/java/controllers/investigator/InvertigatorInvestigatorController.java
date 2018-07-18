package controllers.investigator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.InvestigatorService;
import controllers.AbstractController;
import domain.Investigator;

@Controller
@RequestMapping("/investigator/investigator")
public class InvertigatorInvestigatorController extends AbstractController {

	@Autowired
	private InvestigatorService investigatorService;

	public InvertigatorInvestigatorController() {
		super();
	}
	
	//edition
	
	

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;

		final Investigator investigator = this.investigatorService
				.findByPrincipal();
		result = this.createEditModelAndView(investigator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Investigator investigator,
			final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(investigator);
		else
			try {
				this.investigatorService.save(investigator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(investigator,
						"investigator.commit.error");
			}
		return result;
	}

	// Ancillary methods
	// ------------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			final Investigator investigator) {

		ModelAndView result;

		result = this.createEditModelAndView(investigator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final Investigator investigator, final String message) {

		Assert.notNull(investigator);

		ModelAndView result;

		result = new ModelAndView("investigator/edit");
		result.addObject("investigator", investigator);
		result.addObject("message", message);

		return result;
	}
}
