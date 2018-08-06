package controllers.immigrant;

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

import services.ApplicationService;
import services.ImmigrantService;
import services.VisaService;
import controllers.AbstractController;
import domain.Application;
import domain.ContactSection;
import domain.EducationSection;
import domain.Immigrant;
import domain.PersonalSection;
import domain.SocialSection;
import domain.Visa;
import domain.WorkSection;
import forms.ApplicationForm;

@Controller
@RequestMapping("/application/immigrant")
public class ApplicationImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;

	@Autowired
	private ImmigrantService immigrantService;
	
	@Autowired
	private VisaService visaService;

	// Constructors ---------------------------------------------------------

	public ApplicationImmigrantController() {
		super();
	}

	// Listing --------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application = new Application();
		application = applicationService.findOne(applicationId);
		result = new ModelAndView("application/list");
		result.addObject("requestURI", "application/immigrant/list.do");
		result.addObject("application", application);
		return result;
	}
	
	@RequestMapping(value = "/listClosed", method = RequestMethod.GET)
	public ModelAndView listClosed() {
		ModelAndView result;
		Collection<Application> application = new ArrayList<Application>();
		application = applicationService.findApplicationClosed();
		result = new ModelAndView("application/listClosed");
		result.addObject("requestURI", "application/immigrant/list.do");
		result.addObject("application", application);
		return result;
	}
	
	@RequestMapping(value = "/listAccepted", method = RequestMethod.GET)
	public ModelAndView listAccepted() {
		ModelAndView result;
		Collection<Application> application = new ArrayList<Application>();
		application = applicationService.findApplicationAccepted();
		result = new ModelAndView("application/listAccepted");
		result.addObject("requestURI", "application/immigrant/list.do");
		result.addObject("application", application);
		return result;
	}
	
	@RequestMapping(value = "/listRejected", method = RequestMethod.GET)
	public ModelAndView listRejected() {
		ModelAndView result;
		Collection<Application> application = new ArrayList<Application>();
		application = applicationService.findApplicationRejectedbyImmigrant();
		result = new ModelAndView("application/listRejected");
		result.addObject("requestURI", "application/immigrant/list.do");
		result.addObject("application", application);
		return result;
	}

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
		int immigrantId = i.getId();
		application = applicationService.getApplicationByImmigrant(immigrantId);

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
	try {
		
	
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


		result = new ModelAndView("application/sectionDisplay");
		result.addObject("personalSection", personalS);
		result.addObject("contactSection", contactS);
		result.addObject("workSection", workS);
		result.addObject("socialSection", socialS);
		result.addObject("educationSection", educationS);
		result.addObject("applicationId", applicationId);
		result.addObject("requestURI", "application/immigrant/sectionDisplay.do");
	} catch (Exception e) {
		System.out.println(e.getMessage());
		result = new ModelAndView("application/sectionDisplay");

	}
		return result;
	}

	// Editing --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		
		ApplicationForm applicationForm;
		Application application;
		Immigrant immigrant;

		immigrant = this.immigrantService.findByPrincipal();
		application = applicationService.findOne(applicationId);
		
		if (immigrant.getApplications().contains(application)) {
			applicationForm = this.applicationService.construct(application);
			result = this.createEditModelAndView(applicationForm);
		} else {
			result = new ModelAndView("redirect:../../");
		}
		return result;
	}
	

	// Saving --------------------------------------------------------------
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ApplicationForm applicationForm, final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(applicationForm, "application.params.error");
		}else
			try{
				
				Application application = this.applicationService.reconstruct(applicationForm, binding);
				this.applicationService.save(application);

				res = new ModelAndView("redirect:../../");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(applicationForm, "application.commit.error");
			}
		
		return res;
	}

	// Deleting -------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(ApplicationForm applicationForm, BindingResult binding) {
		ModelAndView res;
		Application application = new Application();
		try {
			application = this.applicationService.reconstruct(applicationForm, binding);
			this.applicationService.delete(application);
			res = new ModelAndView(
					"redirect:../../application/immigrant/display.do");
		} catch (Throwable oops) {
			res = createEditModelAndView(applicationForm,
					"application.commit.error");
		}

		return res;
	}

	// Creating ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ApplicationForm applicationForm = new ApplicationForm();
		
		result = this.createEditModelAndView(applicationForm);

		return result;
	}

	// Ancillary methods --------------------------------------------------
	protected ModelAndView createEditModelAndView(final ApplicationForm applicationForm) {
		ModelAndView result;
		result = this.createEditModelAndView(applicationForm, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(
			final ApplicationForm applicationForm, final String message) {

		ModelAndView result;
		Collection<Visa> visas = new ArrayList<Visa>();
		
		visas = this.visaService.findAll();
		
		result = new ModelAndView("application/immigrant/edit");
		result.addObject("applicationForm", applicationForm);
		result.addObject("visa", visas);
		result.addObject("message", message);
		return result;
	}
	
}
