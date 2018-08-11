package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TutorialService;
import controllers.AbstractController;
import domain.Tutorial;
import forms.TutorialForm;

@Controller
@RequestMapping("/tutorial/administrator")
public class TutorialAdministratorController extends AbstractController{

	// Services ----------------------------
	
	@Autowired 
	private TutorialService tutorialService;
	
	// Constructors ------------------------
	
	public TutorialAdministratorController(){
		super();
	}
	
	// Edit ----------------------------------
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tutorialId){
		
		ModelAndView res;
		Tutorial tutorial;
		TutorialForm tutorialForm;
		
		tutorial = this.tutorialService.findOne(tutorialId);
		tutorialForm = this.tutorialService.construct(tutorial);
		
		res = this.createEditModelAndView(tutorialForm);
		
		return res;
	}
	
	
	// Delete ------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final TutorialForm tutorialForm, final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(tutorialForm, "tutorial.params.error");
		} else {
			try {
				Tutorial tutorial = this.tutorialService.reconstruct(tutorialForm, binding);
				this.tutorialService.delete(tutorial);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this.createEditModelAndView(tutorialForm, "comment.commit.error");
			}
		}
		
		return res;
	}
	
	@RequestMapping(value="/tutorialsTabooList", method=RequestMethod.GET)
	public ModelAndView tutorialsTabooList(){
		ModelAndView res;
		Collection<Tutorial> tutorials = this.tutorialService.tutorialsTaboo();
		
		res = new ModelAndView("tutorial/list");
		res.addObject("tutorial",tutorials);
		res.addObject("requestURI","tutorial/administrator/list.do");
		
		return res;
	}
	
	private ModelAndView createEditModelAndView(final TutorialForm tutorialForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(tutorialForm,null);
		
		return res;
	}

	private ModelAndView createEditModelAndView(final TutorialForm tutorialForm,
			final String message) {
		
		ModelAndView res = new ModelAndView("tutorial/administrator/edit");
		res.addObject("tutorialForm",tutorialForm);
		res.addObject("message",message);
		res.addObject("requestUri","tutorial/administrator/edit.do");
	
		return res;
	}

}
