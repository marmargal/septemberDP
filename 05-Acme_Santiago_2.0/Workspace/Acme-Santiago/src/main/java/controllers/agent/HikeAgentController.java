package controllers.agent;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
}
