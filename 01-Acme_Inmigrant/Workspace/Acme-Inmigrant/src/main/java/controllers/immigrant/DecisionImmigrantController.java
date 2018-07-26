package controllers.immigrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.DecisionService;
import services.ImmigrantService;
import domain.Decision;
import domain.Immigrant;

@Controller
@RequestMapping("/decision/immigrant")
public class DecisionImmigrantController extends AbstractController{

	// Services -----------------------------------
	@Autowired
	private DecisionService decisionService;
	
	
	@Autowired
	private ImmigrantService immigrantService;
	
	// Constructors --------------------------------
	public DecisionImmigrantController(){
		super();
	}
	
	
	
	// Display (immigrant) ------------------------------------
		@RequestMapping(value="/display",method=RequestMethod.GET)
		public ModelAndView displayImmigrant(@RequestParam final int decisionId){
			
			ModelAndView res;
			Decision decision;
			
			Immigrant immigrantPrincipal = this.immigrantService.findByPrincipal();
			
			decision = this.decisionService.findOne(decisionId);
			
			res = new ModelAndView("decision/display");
			if(immigrantPrincipal.equals(decision.getApplication().getImmigrant())){
				res.addObject("decision",decision);
				res.addObject("requestURI", "decision/immigrant/display.do");
			}else{
				try{
					res = new ModelAndView("redirect:../../");
				}catch(final Throwable oops) {
					res.addObject("decision",decision);
					res.addObject("message","decision.commit.error");
					res.addObject("requestURI", "decision/immigrant/display.do");
				}
			}
//			res.addObject("requestURI", "decision/display.do");
			
			return res;
		}
}
