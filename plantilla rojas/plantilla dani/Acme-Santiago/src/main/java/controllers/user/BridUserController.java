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

import services.BridService;
import services.RouteService;
import services.UserService;
import controllers.AbstractController;
import domain.Brid;
import domain.Route;
import domain.User;

@Controller
@RequestMapping("/brid/user")
public class BridUserController extends AbstractController {

	@Autowired
	private BridService bridService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private UserService userService;

	// Constructors ---------------------------------------------------------

	public BridUserController() {
		super();
	}

	// Listing ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0") final int routeId) {
		ModelAndView res;
		Collection<Brid> brids = new ArrayList<Brid>();

		User user = this.userService.findByPrincipal();

		brids = this.bridService.findBridsByRoute(routeId);

		res = new ModelAndView("brid/user/list");
		res.addObject("brids", brids);
		res.addObject("user", user);
		res.addObject("requestURI", "brid/user/list.do");

		return res;
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Brid> brids = new ArrayList<Brid>();

		User user = this.userService.findByPrincipal();

		brids = this.bridService.findBridsByUser(user.getId());

		res = new ModelAndView("brid/user/listAll");
		res.addObject("brids", brids);
		res.addObject("user", user);
		res.addObject("requestURI", "brid/user/list.do");

		return res;
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Brid brid;

		brid = this.bridService.create();
		res = this.createEditModelAndView(brid);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int bridId) {
		ModelAndView result;
		Brid brid;
		User user = this.userService.findByPrincipal();
		brid = this.bridService.findOne(bridId);

		if (bridId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.bridService.findOne(bridId) == null) {
			result = new ModelAndView("redirect:../../");
		} else if (user.getBrids().contains(brid)) {
			result = this.createEditModelAndView(brid);
		} else {
			result = new ModelAndView("redirect:../../");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Brid brid, final BindingResult binding) {
		ModelAndView res;
		System.out.println(brid.getRoute());
		if (binding.hasErrors())
			res = this.createEditModelAndView(brid, "brid.params.error");
		else
			try {
				this.bridService.save(brid);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(brid, "brid.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Brid brid,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.bridService.delete(brid);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(brid, "brid.commit.error");
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	private ModelAndView createEditModelAndView(final Brid brid) {
		ModelAndView result;

		result = this.createEditModelAndView(brid, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Brid brid,
			final String message) {
		ModelAndView result;
		Collection<Route> routes = new ArrayList<Route>();
		routes = routeService.findByUser(this.userService.findByPrincipal());

		result = new ModelAndView("brid/user/edit");
		result.addObject("brid", brid);
		result.addObject("message", message);
		result.addObject("routes", routes);
		result.addObject("requestURI", "brid/user/edit.do");

		return result;
	}
}
