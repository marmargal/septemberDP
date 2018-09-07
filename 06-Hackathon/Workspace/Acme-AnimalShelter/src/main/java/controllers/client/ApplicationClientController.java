package controllers.client;

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
import services.ClientService;
import services.PetService;
import controllers.AbstractController;
import domain.Application;
import domain.Client;
import domain.Pet;

@Controller
@RequestMapping("/application/client")
public class ApplicationClientController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private PetService petService;
	
	@Autowired
	private ClientService clientService;

	// Constructors -----------------------------------------------------------

	public ApplicationClientController() {
		super();
	}
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Application> applications = this.applicationService.findApplicationsAprobed();

		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("requestURI", "application/client/list.do");
		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0") int petId) {
		ModelAndView res;
		Application application;
		Pet pet;

		if (petId == 0) {
			res = new ModelAndView("redirect:../../");
		} else if (this.petService.findOne(petId) == null) {
			res = new ModelAndView("redirect:../../");
		} else {
			pet = petService.findOne(petId);
			if(pet.getStatus() == true){
				application = this.applicationService.create(pet);
				res = this.createEditModelAndView(application);
			} else {
				res = new ModelAndView("redirect:../../");
			}
		}

		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Application application, final BindingResult binding) {
		ModelAndView res;
		Application savedApplication;
		Pet pet;
		Client client;
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(application, "application.params.error");
		else
			try {
				savedApplication = this.applicationService.save(application);
				
				pet = savedApplication.getPet();
				client = savedApplication.getClient();
				
				pet.getApplication().add(savedApplication);
				client.getApplications().add(savedApplication);
				
				petService.save(pet);
				clientService.saveForApplication(client);
				
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this
						.createEditModelAndView(application, "application.commit.error");
			}
		return res;
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

		result = new ModelAndView("application/client/edit");
		result.addObject("application", application);
		result.addObject("message", message);
		result.addObject("requestURI", "application/client/edit.do");

		return result;
	}
}
