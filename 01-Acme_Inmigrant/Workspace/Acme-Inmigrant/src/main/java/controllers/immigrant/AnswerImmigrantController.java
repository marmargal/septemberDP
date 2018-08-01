package controllers.immigrant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import controllers.AbstractController;
import domain.Answer;
import forms.AnswerForm;

@Controller
@RequestMapping("/answer/immigrant")
public class AnswerImmigrantController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AnswerService answerService;
	
	// Constructors ---------------------------------------------------------

	public AnswerImmigrantController() {
		super();
	}

	// Create --------------
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam int questionId){
		ModelAndView res;
		AnswerForm answerForm = new AnswerForm();
		
		answerForm.setQuestionId(questionId);
		
		res = this.createEditModelAndView(answerForm);
		
		return res;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save( final AnswerForm answerForm,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(answerForm, "answer.params.error");
		}else
			try{
				Answer answer = this.answerService.reconstruct(answerForm, binding);
				this.answerService.save(answer);

				res = new ModelAndView("redirect:/answer/list.do?questionId=" + answerForm.getQuestionId());
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(answerForm, "answer.commit.error");
			}
		
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final AnswerForm answerForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(answerForm,null);
		
		return res;
	}

	protected ModelAndView createEditModelAndView(final AnswerForm answerForm, final String message) {
		ModelAndView res;
		
		res = new ModelAndView("answer/edit");
		
		res.addObject("answerForm", answerForm);
		res.addObject("message",message);
		res.addObject("requestURI", "answer/immigrant/edit.do");
		
		return res;
	}

	

}
