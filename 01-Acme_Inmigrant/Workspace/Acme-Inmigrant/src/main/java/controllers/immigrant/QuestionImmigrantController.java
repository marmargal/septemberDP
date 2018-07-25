package controllers.immigrant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.QuestionService;
import controllers.AbstractController;
import domain.Application;
import domain.Question;

@Controller
@RequestMapping("/question/immigrant")
public class QuestionImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private ApplicationService applicatioService;
	
	@Autowired
	private QuestionService questionService;

	// Constructors ---------------------------------------------------------

	public QuestionImmigrantController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int applicationId) {
		ModelAndView result;
		try{
			this.questionService.checkAplicationNotApply(applicationId);
			Application application = this.applicatioService.findOne(applicationId);
			Collection<Question> questions = application.getQuestion();
			
			result = new ModelAndView("question/list");
			result.addObject("question", questions);
			result.addObject("requestURI", "question/immigrant/list.do");
		}catch (Throwable oops) {
			result = new ModelAndView("redirect:../../");
		}
		
		
		return result;
	}

}
