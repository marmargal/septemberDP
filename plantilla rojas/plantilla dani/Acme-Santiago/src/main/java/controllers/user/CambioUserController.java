package controllers.user;

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

import services.CambioService;
import services.RouteService;
import services.UserService;
import controllers.AbstractController;
import domain.Cambio;
import domain.Route;
import domain.User;

@Controller
@RequestMapping("/cambio/user")
public class CambioUserController extends AbstractController {

	@Autowired
	private CambioService cambioService;
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private UserService userService;
	
	// Constructors ---------------------------------------------------------

	public CambioUserController() {
		super();
	}
	
	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0") final int routeId) {
		ModelAndView res;
		Collection<Cambio> cambios = new ArrayList<Cambio>();
		
		User user = this.userService.findByPrincipal();
		
		cambios = this.cambioService.findCambiosByRoute(routeId);
		
		res = new ModelAndView("cambio/user/list");
		res.addObject("cambios", cambios);
		res.addObject("user", user);
		res.addObject("requestURI", "cambio/user/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0") final int routeId) {
		ModelAndView res;
		Cambio cambio;
		Route route = this.routeService.findOne(routeId);

		cambio = this.cambioService.create(route);
		res = this.createEditModelAndView(cambio);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int cambioId) {
		ModelAndView result;
		Cambio cambio;

		if (cambioId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.cambioService.findOne(cambioId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {

			cambio = this.cambioService.findOne(cambioId);
			result = this.createEditModelAndView(cambio);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Cambio cambio, final BindingResult binding) {
		ModelAndView res;
		
		if (binding.hasErrors())
			res = this.createEditModelAndView(cambio, "cambio.params.error");
		else
			try {
				this.cambioService.save(cambio);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this
						.createEditModelAndView(cambio, "cambio.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Cambio cambio,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.cambioService.delete(cambio);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(cambio, "cambio.commit.error");
		}
		return res;
	}
	
	// Ancillary methods --------------------------------------------------

	private ModelAndView createEditModelAndView(final Cambio cambio) {
		ModelAndView result;

		result = this.createEditModelAndView(cambio, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Cambio cambio,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("cambio/user/edit");
		result.addObject("cambio", cambio);
		result.addObject("message", message);
		result.addObject("requestURI", "cambio/user/edit.do");

		return result;
	}
}
