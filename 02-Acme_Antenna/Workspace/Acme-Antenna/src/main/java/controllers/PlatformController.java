package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PlatformService;
import services.SatelliteService;
import domain.Platform;
import domain.Satellite;

@Controller
@RequestMapping("/platform")
public class PlatformController extends AbstractController{

	// Services -------------------------------------------------------------

		@Autowired
		private PlatformService platformService;
		
		@Autowired
		private SatelliteService satelliteService;

		// Constructor

		public PlatformController() {
			super();
		}

		@RequestMapping(value = "/search", method = RequestMethod.GET)
		public ModelAndView searchList(@RequestParam String criteria) {
			ModelAndView res;
			Collection<Platform> platforms;

			platforms = this.platformService.searchPlatform(criteria);

			res = new ModelAndView("platform/list");
			res.addObject("platforms", platforms);
			res.addObject("requestURI", "platforms/list.do");
			return res;
		}
		
		@RequestMapping(value = "/list", method = RequestMethod.GET)
		public ModelAndView list() {
			ModelAndView result;
			Collection<Platform> platforms = new ArrayList<>();
			platforms = platformService.findAll();
			result = new ModelAndView("platform/list");
			result.addObject("requestURI", "platform/list.do");
			result.addObject("platforms", platforms);
			return result;
		}
		
		@RequestMapping(value = "/satellite/list", method = RequestMethod.GET)
		public ModelAndView list(@RequestParam int satelliteId) {
			ModelAndView result;
			Collection<Platform> platforms = new ArrayList<>();
			
			Satellite satellite = new Satellite();
			satellite = satelliteService.findOne(satelliteId);
			
			platforms = satellite.getPlatforms();
			result = new ModelAndView("platform/list");
			result.addObject("requestURI", "platform/satellite/list.do");
			result.addObject("platforms", platforms);
			return result;
		}
}
