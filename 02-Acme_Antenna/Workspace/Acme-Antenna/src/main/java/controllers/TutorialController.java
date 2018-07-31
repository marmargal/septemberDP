package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Tutorial;

import services.TutorialService;

@Controller
@RequestMapping("/tutorial")
public class TutorialController extends AbstractController{

	// Services ---------------------------------
	@Autowired
	private TutorialService tutorialService;
	
	// Constructors ------------------------------
	public TutorialController(){
		super();
	}
	
	// Listing -----------------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Tutorial> tutorials = this.tutorialService.findAll();
		
		res = new ModelAndView("tutorial/list");
		res.addObject("tutorial",tutorials);
		res.addObject("requestURI","tutorial/list.do");
		
		return res;
	}
	
	// Display -----------------------------------
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam final int tutorialId){
		ModelAndView res;
		Tutorial tutorial;
		
		tutorial = this.tutorialService.findOne(tutorialId);
		
		res = new ModelAndView("tutorial/display");
		res.addObject("tutorial",tutorial);
		res.addObject("requestURI","tutorial/display.do");
		
		return res;
	}
}
