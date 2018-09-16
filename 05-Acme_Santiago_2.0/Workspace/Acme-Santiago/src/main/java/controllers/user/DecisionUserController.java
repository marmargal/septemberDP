package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DecisionService;
import controllers.AbstractController;
import domain.Decision;

@Controller
@RequestMapping("/decision/user")
public class DecisionUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private DecisionService decisionService;
	
	// Constructors ---------------------------------------------------------

	public DecisionUserController() {
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

}
