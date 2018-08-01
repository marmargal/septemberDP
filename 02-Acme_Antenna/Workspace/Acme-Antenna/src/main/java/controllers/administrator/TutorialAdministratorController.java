package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TutorialService;
import controllers.AbstractController;
import domain.Tutorial;

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
	
	
	// Delete ------------------------------
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public ModelAndView delete(@RequestParam int tutorialId){
		Tutorial tutorial = tutorialService.findOne(tutorialId);
		tutorialService.delete(tutorial);
		return new ModelAndView("redirect:/tutorial/list.do");
	}

}
