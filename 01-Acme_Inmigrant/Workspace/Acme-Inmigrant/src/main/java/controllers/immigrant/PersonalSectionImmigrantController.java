package controllers.immigrant;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PersonalSectionService;
import controllers.AbstractController;
import domain.PersonalSection;

@Controller
@RequestMapping("/personalSection/immigrant")
public class PersonalSectionImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private PersonalSectionService personalSectionService;

	// Constructors ---------------------------------------------------------

	public PersonalSectionImmigrantController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int personalSectionId) {
		ModelAndView result;
		PersonalSection personalSection;

		personalSection = personalSectionService.findOne(personalSectionId);
		Assert.notNull(personalSection);
		result = this.createEditModelAndView(personalSection);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid PersonalSection personalSection,
			BindingResult binding) {
		ModelAndView res;
		
		System.out.println(binding.getFieldError());
		System.out.println(binding.getFieldErrors());
		
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(personalSection,
					"personalSection.params.error");
		} else
			try {
				this.personalSectionService.save(personalSection);
				res = new ModelAndView(
						"redirect:../../application/immigrant/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(personalSection,
						"personalSection.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(PersonalSection personalSection, BindingResult binding) {
		ModelAndView res;

		try {
			this.personalSectionService.delete(personalSection);
			res = new ModelAndView(
					"redirect:../../application/immigrant/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(personalSection,
					"personalSection.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		PersonalSection a;

		a = this.personalSectionService.create();
		result = this.createEditModelAndView(a);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final PersonalSection personalSection) {
		ModelAndView result;
		result = this.createEditModelAndView(personalSection, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(
			final PersonalSection personalSection, final String message) {

		ModelAndView result;
		result = new ModelAndView("personalSection/immigrant/edit");
		result.addObject("personalSection", personalSection);
		result.addObject("message", message);
		return result;
	}
}
