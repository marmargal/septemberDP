package controllers.agent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdvertisementService;
import services.AgentService;
import services.HikeService;
import controllers.AbstractController;
import domain.Advertisement;
import domain.Agent;
import domain.Hike;

@Controller
@RequestMapping("/hike/agent")
public class HikeAgentController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private HikeService hikeService;

	@Autowired
	private AgentService agentService;
	
	@Autowired
	private AdvertisementService advertisementService;

	// Constructors ---------------------------------------------------------

	public HikeAgentController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Hike> hikes = new ArrayList<>();
		
		Agent agent = agentService.findByPrincipal();
		
		Collection<Advertisement> advertisements = new ArrayList<Advertisement>();
		advertisements.addAll(agent.getAdvertisements());
		
		for(Advertisement a: advertisements){
			hikes.addAll(this.hikeService.findHikeByAdvertisement(a.getId()));
		}
		
		res = new ModelAndView("hike/agent/list");
		res.addObject("requestURI", "hike/agent/list.do");
		res.addObject("hikes", hikes);

		return res;
	}
	
	@RequestMapping(value = "/listWithoutAd", method = RequestMethod.GET)
	public ModelAndView listWithoutAd() {
		ModelAndView res;
		Collection<Hike> hikes = new ArrayList<>();
		
		Agent agent = agentService.findByPrincipal();
		
		Collection<Advertisement> advertisements = new ArrayList<Advertisement>();
		advertisements.addAll(agent.getAdvertisements());
		
		for(Advertisement a: advertisements){
			hikes.addAll(this.hikeService.findHikeByAdvertisement(a.getId()));
		}
		
		Collection<Hike> hikesRes = new ArrayList<Hike>();
		hikesRes.addAll(hikeService.findAll());
		
		hikesRes.removeAll(hikes);
		
		res = new ModelAndView("hike/agent/listWithoutAd");
		res.addObject("requestURI", "hike/agent/listWithoutAd.do");
		res.addObject("hikes", hikesRes);

		return res;
	}
	
	// Displaying ----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView searchList(@RequestParam(defaultValue = "0") int hikeId) {
		ModelAndView res;
		Hike hike;
		Collection<Advertisement> advertisements = new ArrayList<Advertisement>();
		advertisements = this.advertisementService.findAdvertisementByHike(hikeId);
		Advertisement advertisement = ((ArrayList<Advertisement>) advertisements).get(new Random().nextInt(advertisements.size()));

		if (hikeId == 0) {
			res = new ModelAndView("redirect:../");

		} else if (this.hikeService.findOne(hikeId) == null) {
			res = new ModelAndView("redirect:../");
		} else {

			hike = this.hikeService.findOne(hikeId);

			res = new ModelAndView("hike/display");
			res.addObject("hike", hike);
			res.addObject("advertisement", advertisement);
			res.addObject("requestURI", "hike/agent/display.do");
		}
		return res;
	}
	
}
