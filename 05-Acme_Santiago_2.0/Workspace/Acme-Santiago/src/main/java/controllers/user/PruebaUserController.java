package controllers.user;

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

import services.PruebaService;
import services.RouteService;
import services.UserService;
import controllers.AbstractController;
import domain.Prueba;
import domain.Route;
import domain.User;
import forms.PruebaForm;

@Controller
@RequestMapping("/prueba/user")
public class PruebaUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private PruebaService pruebaService;
	
	@Autowired
	private UserService userService;

	@Autowired
	private RouteService routeService;

	
	// Constructors ---------------------------------------------------------

	public PruebaUserController() {
		super();
	}

	// Listing --------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Prueba> pruebas = new ArrayList<>();

		User user = userService.findByPrincipal();

		pruebas = this.pruebaService.findPruebasByUser(user.getId());
		res = new ModelAndView("prueba/list");
		res.addObject("requestURI", "prueba/user/list.do");
		res.addObject("pruebas", pruebas);

		return res;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0") int routeId) {
		ModelAndView res;
		
		PruebaForm pruebaForm = new PruebaForm();
		res = this.createEditModelAndView(pruebaForm);
		
		return res;
	}
	
	// Edition ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") int pruebaId) {
		ModelAndView res;
		try{
			Prueba prueba = this.pruebaService.findOne(pruebaId);
			Assert.isTrue(prueba.getRoute() == null);
			
			PruebaForm pruebaForm = this.pruebaService.construct(prueba);
			res = this.createEditModelAndView(pruebaForm);
		}catch (final Throwable oops) {
			res = new ModelAndView("redirect:/prueba/user/list.do");
		}
		
		
		return res;
	}

	// Saving --------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid PruebaForm pruebaForm, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(pruebaForm, "prueba.params.error");
		else
			try {
				Prueba prueba = this.pruebaService.reconstruct(pruebaForm, binding);
				this.pruebaService.save(prueba);
				res = new ModelAndView("redirect:/prueba/user/list.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(pruebaForm, "prueba.commit.error");
			}
		return res;
	}
	
	// Deleting -------------------------------------------------------------
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(int pruebaId, BindingResult binding) {
		ModelAndView res;
		Prueba prueba = new Prueba();
		try {
			prueba = this.pruebaService.findOne(pruebaId);
			this.pruebaService.delete(prueba);
			res = new ModelAndView("redirect:../prueba/user/list.do");
		} catch (Throwable oops) {
			res = new ModelAndView("redirect:../..");
		}

		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final PruebaForm pruebaForm) {
		ModelAndView result;

		result = this.createEditModelAndView(pruebaForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final PruebaForm pruebaForm, final String message) {
		ModelAndView result;
		
		Collection<Route> routes = new ArrayList<>();
		routes = this.routeService.findAll();

		result = new ModelAndView("prueba/edit");
		result.addObject("pruebaForm", pruebaForm);
		result.addObject("routes", routes);
		result.addObject("message", message);
		result.addObject("requestURI", "prueba/user/edit.do");

		return result;
	}
}
