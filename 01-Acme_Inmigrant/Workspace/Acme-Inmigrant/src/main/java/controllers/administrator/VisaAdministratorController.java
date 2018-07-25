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

import services.CategoryService;
import services.CountryService;
import services.VisaService;
import controllers.AbstractController;
import domain.Category;
import domain.Country;
import domain.Visa;

@Controller
@RequestMapping("/visa/administrator")
public class VisaAdministratorController extends AbstractController {

	@Autowired
	private VisaService visaService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CategoryService categoryService;

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
		if (bindingResult.hasErrors()) {
			result = this.createEditModelAndView(visa);
		} else {
			try {
				visaService.save(visa);

				result = new ModelAndView("redirect:list.do");
			} catch (Throwable ooops) {

				result = this.createEditModelAndView(visa, "visa.commit.error");

			}
		}

		return result;
	}

	@RequestMapping(value = "/invalidate", params = "save", method = RequestMethod.POST)
	public ModelAndView invalidate(@Valid final Visa visa,
			final BindingResult bindingResult) {
		ModelAndView result;
		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(visa);
		else {
			try {
				visa.setInvalidate(true);
				visaService.save(visa);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable ooops) {

				if (visa.getPrice() == null) {
					result = this.createEditModelAndView(visa,
							"visa.commit.error.price");

				} else {
					result = this.createEditModelAndView(visa,
							"visa.commit.error");

				}

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
		Collection<Country> countries = countryService.findAll();
		Collection<Category> categories = categoryService.findAll();
		result = new ModelAndView("visa/administrator/edit");
		Collection<Boolean> invalidate = new ArrayList<>();
		invalidate.add(false);
		invalidate.add(true);
		result.addObject("visa", visa);
		result.addObject("countries", countries);
		result.addObject("categories", categories);
		result.addObject("invalidate", invalidate);
		result.addObject("message", message);
		result.addObject("requestUri", "visa/administrator/edit.do");
		return result;
	}
}
