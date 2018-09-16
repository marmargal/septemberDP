package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CambioService;

import controllers.AbstractController;
import domain.Cambio;
import domain.Cambio;
import domain.Cambio;

@Controller
@RequestMapping("/cambio/user")
public class CambioUserController extends AbstractController {

	@Autowired
	private CambioService cambioService;

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Cambio cambio;

		cambio = this.cambioService.create();
		res = this.createEditModelAndView(cambio);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int cambioId) {
		ModelAndView result;
		Cambio cambio;

		if (cambioId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.cambioService.findOne(cambioId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {

			cambio = this.cambioService.findOne(cambioId);
			result = this.createEditModelAndView(cambio);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Cambio cambio, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(cambio, "cambio.params.error");
		else
			try {
				this.cambioService.save(cambio);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this
						.createEditModelAndView(cambio, "cambio.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Cambio cambio,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.cambioService.delete(cambio);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(cambio, "cambio.commit.error");
		}
		return res;
	}

	private ModelAndView createEditModelAndView(final Cambio cambio) {
		ModelAndView result;

		result = this.createEditModelAndView(cambio, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Cambio cambio,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("cambio/user/edit");
		result.addObject("cambio", cambio);
		result.addObject("message", message);
		result.addObject("requestUri", "cambio/user/edit.do");

		return result;
	}
}
