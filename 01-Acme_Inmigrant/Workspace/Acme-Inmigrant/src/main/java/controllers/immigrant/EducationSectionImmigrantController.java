package controllers.immigrant;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.EducationSectionService;
import services.ImmigrantService;
import controllers.AbstractController;
import domain.Application;
import domain.EducationSection;
import domain.Immigrant;

@Controller
@RequestMapping("/educationSection/immigrant")
public class EducationSectionImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private EducationSectionService educationSectionService;
	
	// Supporting services --------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ImmigrantService immigrantService;

	// Constructors ---------------------------------------------------------

	public EducationSectionImmigrantController() {
		super();
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int educationSectionId) {
		ModelAndView result;
		EducationSection educationSection;

		educationSection = educationSectionService.findOne(educationSectionId);
		Assert.notNull(educationSection);
		result = this.createEditModelAndView(educationSection);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid EducationSection educationSection,
			BindingResult binding) {
		ModelAndView res;
		
		System.out.println(binding.getFieldError());
		System.out.println(binding.getFieldErrors());
		
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(educationSection,
					"educationSection.params.error");
		} else
			try {
				this.educationSectionService.save(educationSection);
				res = new ModelAndView(
						"redirect:../../application/immigrant/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(educationSection,
						"educationSection.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(EducationSection educationSection, BindingResult binding) {
		ModelAndView res;

		try {
			this.educationSectionService.delete(educationSection);
			res = new ModelAndView(
					"redirect:../../application/immigrant/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(educationSection,
					"educationSection.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		EducationSection a;

		a = this.educationSectionService.create();
		result = this.createEditModelAndView(a);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final EducationSection educationSection) {
		ModelAndView result;
		result = this.createEditModelAndView(educationSection, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(
			final EducationSection educationSection, final String message) {

		ModelAndView result;
		Immigrant immigrant = immigrantService.findByPrincipal();
		Collection<Application> applications = new ArrayList<Application>();
		applications = applicationService.getApplicationByImmigrant(immigrant.getId());
		
		result = new ModelAndView("educationSection/immigrant/edit");
		result.addObject("educationSection", educationSection);
		result.addObject("application", applications);
		result.addObject("message", message);
		return result;
	}
}
