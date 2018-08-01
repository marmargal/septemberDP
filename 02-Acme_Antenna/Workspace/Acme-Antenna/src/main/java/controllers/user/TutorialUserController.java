package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.TutorialService;
import services.UserService;
import controllers.AbstractController;
import domain.Actor;
import domain.Tutorial;
import forms.TutorialForm;

@Controller
@RequestMapping("/tutorial/user")
public class TutorialUserController extends AbstractController {
	
	// Services ------------------------------
	@Autowired
	private TutorialService tutorialService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActorService actorService;
	
	// Constructors --------------------------
	public TutorialUserController(){
		super();
	}
	
	// Create --------------------------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		userService.checkAuthority();
		
		ModelAndView res;
		Tutorial tutorial;
		TutorialForm tutorialForm;
		
		tutorial = this.tutorialService.create();
		tutorialForm = this.tutorialService.construct(tutorial);
		
		res = this.createEditModelAndView(tutorialForm);
		
		return res;
	}
	// Edit ----------------------------------
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId){
		
		ModelAndView res;
		Tutorial tutorial;
		TutorialForm tutorialForm;
		
		Actor actorPrincipal = this.actorService.findByPrincipal();
		
		tutorial = this.tutorialService.findOne(tutorialId);
		tutorialForm = this.tutorialService.construct(tutorial);
		
		if(actorPrincipal.equals(tutorial.getActor()))
			res = this.createEditModelAndView(tutorialForm);
		else 
			res = new ModelAndView("redirect:/tutorial/list.do");
		
		return res;
	}
	
	// Save ------------------------------------------
	@RequestMapping(value="/edit", method=RequestMethod.POST, params ="save")
	public ModelAndView save(@Valid final TutorialForm tutorialForm, final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors())
			res = this.createEditModelAndView(tutorialForm, "tutorial.params.error");
		else{
			try{
				Tutorial tutorial = this.tutorialService.reconstruct(tutorialForm, binding);
				this.tutorialService.save(tutorial);
				
				res = new ModelAndView(
						"redirect:/tutorial/list.do");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(tutorialForm, "tutorial.commit.error");
				System.out.println(oops);
			}
		}
		return res;
	}


	// Ancillary methods -----------------------------
	private ModelAndView createEditModelAndView(final TutorialForm tutorialForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(tutorialForm,null);
		
		return res;
	}

	private ModelAndView createEditModelAndView(final TutorialForm tutorialForm,
			final String message) {
		ModelAndView res = new ModelAndView("tutorial/edit");
		
		res.addObject("tutorialForm",tutorialForm);
		res.addObject("message",message);
		res.addObject("requestURI","tutorial/user/edit.do");
	
		return res;
	}
	
}
