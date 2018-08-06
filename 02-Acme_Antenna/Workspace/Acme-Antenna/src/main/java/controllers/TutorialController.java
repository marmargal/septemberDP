package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.TutorialService;
import services.UserService;
import domain.Actor;
import domain.Comment;
import domain.Tutorial;

@Controller
@RequestMapping("/tutorial")
public class TutorialController extends AbstractController{

	// Services ---------------------------------
	@Autowired
	private TutorialService tutorialService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private UserService userService;
	
	// Constructors ------------------------------
	public TutorialController(){
		super();
	}
	
	// Listing -----------------------------------
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		Collection<Tutorial> tutorials = this.tutorialService.findAll();
		
		Actor actor = this.actorService.findByPrincipal();
		
		res = new ModelAndView("tutorial/list");
		res.addObject("tutorial",tutorials);
		res.addObject("actor",actor);
		res.addObject("requestURI","tutorial/list.do");
		
		return res;
	}
	
	// Display -----------------------------------
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam final int tutorialId){
		ModelAndView res;
		Tutorial tutorial;
		
		tutorial = this.tutorialService.findOne(tutorialId);
		Collection<Comment> comments = new ArrayList<Comment>();
		
		
		res = new ModelAndView("tutorial/display");
		res.addObject("tutorial",tutorial);
		try {
			userService.checkAuthority();
			comments = tutorial.getComments();
			res.addObject("comments", comments);
		} catch (Exception e) {
			
			res.addObject("comments", comments);
		}
		
		res.addObject("requestURI","tutorial/display.do");
		
		return res;
	}
}
