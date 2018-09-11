package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.ClientService;
import domain.Client;
import forms.ActorForm;

@Controller
@RequestMapping("/client")
public class RegisterClientController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ActorService actorService;

	// Constructors ---------------------------------------------------------

	public RegisterClientController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		ActorForm clientForm = new ActorForm();
		
		res = this.createEditModelAndView(clientForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm clientForm, final BindingResult binding) {
		ModelAndView res;
		Client client;
		boolean validPhone = this.actorService.validPhoneNumber(clientForm.getPhoneNumber());
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(clientForm, "actor.params.error");
		else if (!clientForm.getRepeatPassword().equals(clientForm.getPassword()))
			res = this.createEditModelAndView(clientForm, "actor.commit.errorPassword");
		else if (clientForm.getTermsAndConditions() == false) 
			res = this.createEditModelAndView(clientForm, "actor.params.errorTerms");
		else if (!validPhone && (clientForm.getAceptPhoneNumberConditions() == null || clientForm.getAceptPhoneNumberConditions() == false)) {
			clientForm.setAceptPhoneNumberConditions(false);
			res = this.createEditModelAndView(clientForm, "actor.params.mustAcceptPhoneNumber");
		} else
			try {
				client = clientService.reconstruct(clientForm, binding);
				this.clientService.save(client);
				res = new ModelAndView("redirect:../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(clientForm, "actor.commit.error");
			}

		return res;
	}
	
	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Client client = this.clientService.findByPrincipal();
		ActorForm clientForm = this.clientService.construct(client);
		
		res = createEditModelAndViewEdit(clientForm);
		res.addObject("actrForm", clientForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm clientForm, final BindingResult binding) {
		ModelAndView res;
		Client client;
		boolean validPhone = this.actorService.validPhoneNumber(clientForm.getPhoneNumber());

		if (binding.hasErrors())
			res = this.createEditModelAndViewEdit(clientForm, "actor.params.error");
		else if (!clientForm.getRepeatPassword().equals(clientForm.getPassword()))
			res = this.createEditModelAndViewEdit(clientForm, "actor.commit.errorPassword");
		else if (!validPhone && (clientForm.getAceptPhoneNumberConditions() == null || clientForm.getAceptPhoneNumberConditions() == false)) {
			clientForm.setAceptPhoneNumberConditions(false);
			res = this.createEditModelAndViewEdit(clientForm, "actor.params.mustAcceptPhoneNumber");
		} else
			try {
				client = clientService.reconstruct(clientForm, binding);
				this.clientService.save(client);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewEdit(clientForm, "actor.commit.error");
			}

		return res;
	}
	

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm clientForm) {
		ModelAndView result;

		result = this.createEditModelAndView(clientForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm clientForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", clientForm);
		result.addObject("message", message);
		result.addObject("requestURI","client/register.do");

		return result;
	}
	
	protected ModelAndView createEditModelAndViewEdit(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndViewEdit(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewEdit(final ActorForm actorForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI","client/edit.do");

		return result;
	}
}
