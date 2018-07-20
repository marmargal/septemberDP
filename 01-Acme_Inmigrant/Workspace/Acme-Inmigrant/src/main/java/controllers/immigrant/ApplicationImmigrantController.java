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
import services.ImmigrantService;
import controllers.AbstractController;
import domain.Application;
import domain.ContactSection;
import domain.EducationSection;
import domain.Immigrant;
import domain.PersonalSection;
import domain.SocialSection;
import domain.WorkSection;

@Controller
@RequestMapping("/application/immigrant")
public class ApplicationImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ImmigrantService immigrantService;

	// Constructors ---------------------------------------------------------

	public ApplicationImmigrantController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Collection<Application> application = new ArrayList<Application>();
		Collection<PersonalSection> personalS = new ArrayList<PersonalSection>();
		Collection<ContactSection> contactS = new ArrayList<ContactSection>();
		Collection<WorkSection> workS = new ArrayList<WorkSection>();
		Collection<SocialSection> socialS = new ArrayList<SocialSection>();
		Collection<EducationSection> educationS = new ArrayList<EducationSection>();

		Immigrant i = immigrantService.findByPrincipal();
		int rangerId = i.getId();
		application = applicationService.getApplicationByImmigrant(rangerId);

		for (Application a : application) {
			personalS.add(a.getPersonalSection());
			contactS.addAll(a.getContactSection());
			workS.addAll(a.getWorkSection());
			socialS.addAll(a.getSocialSection());
			educationS.addAll(a.getEducationSection());

		}

		Immigrant currentImmigrant = immigrantService.findByPrincipal();
		int currentImmigrantId = currentImmigrant.getId();

		result = new ModelAndView("application/display");
		result.addObject("application", application);
		result.addObject("currentImmigrantId", currentImmigrantId);
		result.addObject("requestURI", "application/immigrant/display.do");

		return result;
	}
	
	@RequestMapping(value = "/sectionDisplay", method = RequestMethod.GET)
	public ModelAndView sectionDisplay(@RequestParam final int applicationId) {
		ModelAndView result;
		Collection<Application> application = new ArrayList<Application>();
		PersonalSection personalS = new PersonalSection();
		Collection<ContactSection> contactS = new ArrayList<ContactSection>();
		Collection<WorkSection> workS = new ArrayList<WorkSection>();
		Collection<SocialSection> socialS = new ArrayList<SocialSection>();
		Collection<EducationSection> educationS = new ArrayList<EducationSection>();

		Application applicationS;
		applicationS = applicationService.findOne(applicationId);

		personalS = applicationS.getPersonalSection();
		contactS = applicationS.getContactSection();
		workS = applicationS.getWorkSection();
		socialS = applicationS.getSocialSection();
		educationS = applicationS.getEducationSection();

		Immigrant currentImmigrant = immigrantService.findByPrincipal();
		int currentImmigrantId = currentImmigrant.getId();

		result = new ModelAndView("application/sectionDisplay");
		result.addObject("currentImmigrantId", currentImmigrantId);
		result.addObject("personalSection", personalS);
		result.addObject("contactSection", contactS);
//		result.addObject("workSection", workS);
//		result.addObject("socialSection", socialS);
//		result.addObject("educationSection", educationS);
		result.addObject("requestURI", "application/immigrant/sectionDisplay.do");

		return result;
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		application = applicationService.findOne(applicationId);
		Assert.notNull(application);
		result = this.createEditModelAndView(application);

		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Application application,
			BindingResult binding) {
		ModelAndView res;
		
		System.out.println(binding.getFieldError());
		System.out.println(binding.getFieldErrors());
		
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(application,
					"application.params.error");
		} else
			try {
				this.applicationService.save(application);
				res = new ModelAndView(
						"redirect:../../application/immigrant/display.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(application,
						"application.commit.error");
			}

		return res;
	}

	// Deleting -------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Application application, BindingResult binding) {
		ModelAndView res;

		try {
			this.applicationService.delete(application);
			res = new ModelAndView(
					"redirect:../../application/immigrant/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(application,
					"application.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Application a;

		a = this.applicationService.create();
		result = this.createEditModelAndView(a);

		return result;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;
		result = this.createEditModelAndView(application, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(
			final Application application, final String message) {

		ModelAndView result;
		result = new ModelAndView("application/immigrant/edit");
		result.addObject("application", application);
		result.addObject("message", message);
		return result;
	}
}
