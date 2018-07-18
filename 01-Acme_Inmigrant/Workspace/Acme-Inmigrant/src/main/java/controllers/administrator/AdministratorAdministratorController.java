package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Administrator;

@Controller
@RequestMapping("/administrator")
public class AdministratorAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService administratorService;

	// Constructor -------------------------------------------------------------

	public AdministratorAdministratorController() {
		super();
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;

		final Administrator administrator = this.administratorService
				.findByPrincipal();
		result = this.createEditModelAndView(administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Administrator administrator,
			final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(administrator);
		else
			try {
				this.administratorService.save(administrator);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(administrator,
						"administrator.commit.error");
			}
		return result;
	}

	// Ancillary methods
	// ------------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			final Administrator administrator) {

		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			final Administrator administrator, final String message) {

		Assert.notNull(administrator);

		ModelAndView result;

		result = new ModelAndView("administrator/edit");
		result.addObject("administrator", administrator);
		result.addObject("message", message);

		return result;
	}

}
