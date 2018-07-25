package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import controllers.AbstractController;
import domain.Answer;

@Controller
@RequestMapping("/answer")
public class AnswerController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private AnswerService answerService;

	// Constructors ---------------------------------------------------------

	public AnswerController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int questionId) {
		ModelAndView result;
		
		Answer answer = this.answerService.findAnswerByQuestion(questionId);
		
		result = new ModelAndView("answer/list");
		result.addObject("answer", answer);
		result.addObject("requestURI", "answer/immigrant/list.do");
		
		return result;
	}

}
