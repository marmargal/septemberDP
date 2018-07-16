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

import services.VisaService;
import controllers.AbstractController;
import domain.Visa;

@Controller
@RequestMapping("/visa/administrator")
public class VisaAdministratorController extends AbstractController {

	@Autowired
	private VisaService visaService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Visa> visas = new ArrayList<>();
		visas = visaService.findAll();
		result = new ModelAndView("visa/list");
		result.addObject("requestURI", "visa/administrator/list.do");
		result.addObject("visas", visas);
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final Visa visa = visaService.create();
		result = this.createEditModelAndView(visa);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int visaId) {
		ModelAndView result;
		final Visa visa = visaService.findOne(visaId);
		if (visa == null) {
			result = this.list();
		} else {
			result = this.createEditModelAndView(visa);

		}

		return result;
	}

	@RequestMapping(value = "/edit", params = "save", method = RequestMethod.POST)
	public ModelAndView edit(@Valid final Visa visa,
			final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(visa);
		else {
			try {
				visaService.save(visa);
				result = new ModelAndView("redirect:visa/administrator/list.do");
			} catch (Throwable ooops) {

				result = this.createEditModelAndView(visa, "visa.commit.error");

			}
		}

		return result;
	}

	@RequestMapping(value = "/abrogate", params = "save", method = RequestMethod.POST)
	public ModelAndView invalidate(@Valid final Visa visa,
			final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(visa);
		else {
			try {
				visa.setInvalidate(true);
				visaService.save(visa);
				result = new ModelAndView("redirect:visa/administrator/list.do");
			} catch (Throwable ooops) {

				result = this.createEditModelAndView(visa, "visa.commit.error");

			}
		}

		return result;
	}

	protected ModelAndView createEditModelAndView(final Visa visa) {
		final ModelAndView result;
		result = this.createEditModelAndView(visa, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final Visa visa,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("visa/administrator/edit");
		result.addObject("visa", visa);
		result.addObject("message", message);
		result.addObject("requestUri", "visa/administrator/edit.do");
		return result;
	}
}
