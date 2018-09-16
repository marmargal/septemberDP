package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DecisionService;
import services.PruebaService;
import controllers.AbstractController;
import domain.Decision;
import domain.Prueba;
import forms.DecisionForm;

@Controller
@RequestMapping("/decision/administrator")
public class DecisionAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private DecisionService decisionService;
	
	@Autowired
	private PruebaService pruebaService;
	
	// Constructors ---------------------------------------------------------

	public DecisionAdministratorController() {
		super();
	}

	// Display --------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam(defaultValue = "0") int decisionId) {
		ModelAndView res;
		Decision decision = new Decision();
		
		decision = this.decisionService.findOne(decisionId);
		res = new ModelAndView("decision/display");
		res.addObject("requestURI", "decision/administrator/list.do");
		res.addObject("decision", decision);

		return res;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0") int pruebaId) {
		ModelAndView res;
		
		Prueba prueba = this.pruebaService.findOne(pruebaId);
		
		DecisionForm decisionForm = new DecisionForm();
		decisionForm.setPrueba(prueba);
		res = this.createEditModelAndView(decisionForm);
		
		return res;
	}
	

	// Saving --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid DecisionForm decisionForm, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(decisionForm, "decision.params.error");
		else
			try {
				Decision decision = this.decisionService.reconstruct(decisionForm, binding);
				this.decisionService.save(decision);
				res = new ModelAndView("redirect:/prueba/administrator/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(decisionForm, "decision.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final DecisionForm decisionForm) {
		ModelAndView result;

		result = this.createEditModelAndView(decisionForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final DecisionForm decisionForm, final String message) {
		ModelAndView result;
		
		result = new ModelAndView("decision/edit");
		result.addObject("decisionForm", decisionForm);
		result.addObject("message", message);
		result.addObject("requestURI", "decision/administrator/edit.do");

		return result;
	}
}
