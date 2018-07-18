package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ImmigrantService;
import domain.Immigrant;
import forms.ImmigrantForm;

@Controller
@RequestMapping("/immigrant")
public class RegisterImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ImmigrantService immigrantService;

	// Constructors ---------------------------------------------------------

	public RegisterImmigrantController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		Immigrant immigrant = immigrantService.create();

		ImmigrantForm immigrantForm = new ImmigrantForm();
		immigrantForm = immigrantService.construct(immigrant);

//		res = new ModelAndView("immigrant/register_Immigrant");
//		res.addObject("immigrantForm", immigrantForm);
		
		res = this.createEditModelAndView(immigrantForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("immigrantForm") ImmigrantForm immigrantForm,
			final BindingResult binding) {
ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(immigrantForm, "immigrant.params.error");
		}else
			try{
				Immigrant immigrant = this.immigrantService.reconstruct(immigrantForm, binding);
				this.immigrantService.save(immigrant);
				res = new ModelAndView("redirect:/");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(immigrantForm, "immigrant.commit.error");
			}
		
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final ImmigrantForm immigrantForm) {
		ModelAndView result;

		result = this.createEditModelAndView(immigrantForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ImmigrantForm immigrantForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("immigrant/register");
		result.addObject("immigrantForm", immigrantForm);
		result.addObject("message", message);

		return result;
	}
}
