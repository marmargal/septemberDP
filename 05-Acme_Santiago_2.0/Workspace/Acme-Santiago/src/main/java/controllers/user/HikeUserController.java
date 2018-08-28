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

import services.HikeService;
import services.UserService;
import controllers.AbstractController;
import domain.Hike;
import domain.User;

@Controller
@RequestMapping("/hike/user")
public class HikeUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private HikeService hikeService;

	@Autowired
	private UserService userService;

	// Constructors ---------------------------------------------------------

	public HikeUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Hike> hikes = new ArrayList<>();
		hikes = this.hikeService.findAll();
		User user = this.userService.findByPrincipal();
		res = new ModelAndView("hike/list");
		res.addObject("requestURI", "hikes/user/list.do");
		res.addObject("hikes", hikes);
		res.addObject("user", user);
		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Hike hike;
		hike = this.hikeService.create();
		res = this.createEditModelAndView(hike);
		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int hikeId) {
		ModelAndView result;
		Hike hike;
		hike = this.hikeService.findOne(hikeId);
		User user = this.userService.findByPrincipal();

		if (hikeId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.hikeService.findOne(hikeId) == null || !user.equals(hike.getRoute().getUser())) {
			result = new ModelAndView("redirect:../../");
		} else {
			result = this.createEditModelAndView(hike);
		}
		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Hike hike, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(hike, "hike.params.error");
		else
			try {
				this.hikeService.save(hike);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(hike, "hike.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Hike hike,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.hikeService.delete(hike);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(hike, "hike.commit.error");
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Hike hike) {
		ModelAndView result;

		result = this.createEditModelAndView(hike, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Hike hike,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("hike/edit");
		result.addObject("hike", hike);
		result.addObject("message", message);
		result.addObject("requestURI", "hike/user/edit.do");

		return result;
	}

}