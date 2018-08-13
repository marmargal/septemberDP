package controllers.handyworker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AntennaService;
import controllers.AbstractController;
import domain.Antenna;

@Controller
@RequestMapping("/antenna/handyworker")
public class AntennaHandyworkerController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private AntennaService antennaService;
	
	
	// Constructors ---------------------------------------------------------

	public AntennaHandyworkerController() {
		super();
	}

	// Display
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam int antennaId){
		ModelAndView res;
		Antenna antenna = new Antenna();
		
		antenna = this.antennaService.findOne(antennaId);
	
		res = new ModelAndView("antenna/display");
		res.addObject("antenna", antenna);

		return res;
	}
	
	
}
