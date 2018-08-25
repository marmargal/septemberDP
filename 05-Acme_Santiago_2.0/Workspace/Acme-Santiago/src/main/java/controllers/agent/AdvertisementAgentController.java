package controllers.agent;

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

import services.AdvertisementService;
import services.HikeService;
import controllers.AbstractController;
import domain.Advertisement;
import domain.Hike;

@Controller
@RequestMapping("/advertisement/agent")
public class AdvertisementAgentController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AdvertisementService advertisementService;
	
	@Autowired
	private HikeService hikeService;

	// Constructors ---------------------------------------------------------

	public AdvertisementAgentController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(defaultValue = "0") final int hikeId) {
		ModelAndView res;
		
		Collection<Advertisement> advertisements = new ArrayList<>();
		advertisements.addAll(this.advertisementService.findAdvertisementByHike(hikeId));
		
		res = new ModelAndView("advertisement/list");
		res.addObject("requestURI", "advertisements/agent/list.do");
		res.addObject("advertisements", advertisements);

		return res;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam(defaultValue = "0")  final int hikeId) {
		ModelAndView res;
		Advertisement advertisement;
		Hike hike;
		
		if (hikeId == 0) {
			res = new ModelAndView("redirect:../../");

		} else if (this.hikeService.findOne(hikeId) == null) {
			res = new ModelAndView("redirect:../../");
		} else {
			advertisement = this.advertisementService.create();
			hike = hikeService.findOne(hikeId);
			advertisement.setHike(hike);
			res = this.createEditModelAndView(advertisement);
		}
		return res;
	}

	// Editing ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int advertisementId) {
		ModelAndView result;
		Advertisement advertisement;

		if (advertisementId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.advertisementService.findOne(advertisementId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			advertisement = this.advertisementService.findOne(advertisementId);
			result = this.createEditModelAndView(advertisement);
		}
		return result;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Advertisement advertisement, final BindingResult binding) {
		ModelAndView res;

		if (binding.hasErrors())
			res = this.createEditModelAndView(advertisement, "advertisement.params.error");
		else
			try {
				this.advertisementService.save(advertisement);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(advertisement, "advertisement.commit.error");
			}
		return res;
	}

	// Deleting --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Advertisement advertisement,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.advertisementService.delete(advertisement);
			res = new ModelAndView("redirect:../../");
		} catch (final Throwable oops) {
			res = this.createEditModelAndView(advertisement, "advertisement.commit.error");
		}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Advertisement advertisement) {
		ModelAndView result;

		result = this.createEditModelAndView(advertisement, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Advertisement advertisement,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("advertisement/edit");
		result.addObject("advertisement", advertisement);
		result.addObject("message", message);
		result.addObject("requestURI", "advertisement/agent/edit.do");

		return result;
	}

}