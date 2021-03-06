package controllers.officer;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.OfficerService;
import services.QuestionService;
import controllers.AbstractController;
import domain.Application;
import domain.Officer;
import domain.Question;
import forms.QuestionForm;

@Controller
@RequestMapping("/question/officer")
public class QuestionOfficerController extends AbstractController{
	// Services
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private ApplicationService applicationService;
	
	// Supporting services
	
	@Autowired
	private OfficerService officerService;

	// Constructors
	
	public QuestionOfficerController(){
		super();
	}
	
	// Listing
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(@RequestParam int applicationId){
		ModelAndView res;
		Officer officer = this.officerService.findByPrincipal();
		
		Application application = new Application();
		Collection<Question> questions = new ArrayList<Question>();
		
		application = this.applicationService.findOne(applicationId);
		
		if(application != null){
			if(officer.equals(application.getOfficer())){
				questions = application.getQuestion();
			
				res = new ModelAndView("question/list");
				res.addObject("question", questions);
				res.addObject("requestURI", "question/officer/list.do");
				res.addObject("application", application);
				res.addObject("applicationId", application.getId());
			}else 
				res = new ModelAndView("redirect:../");
			
		}else{
			res = new ModelAndView("question/list");
			res.addObject("requestURI", "question/list.do");
			res.addObject("question", questions);
			res.addObject("application", null);
		}
		
			
		return res;
	}
	
	// Create --------------
	@RequestMapping(value="/edit", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam int applicationId){
		ModelAndView res;
		
		Question question = this.questionService.create(applicationId);
		QuestionForm questionForm = this.questionService.construct(question);
		
		res = this.createEditModelAndView(questionForm);
		
		return res;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final QuestionForm questionForm,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(questionForm, "question.params.error");
		}else
			try{
				
				Question question = this.questionService.reconstruct(questionForm, binding);
				this.questionService.save(question);

				res = new ModelAndView("redirect:/question/officer/list.do?applicationId=" + question.getApplication().getId());
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(questionForm, "question.commit.error");
			}
		
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final QuestionForm questionForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(questionForm,null);
		
		return res;
	}

	protected ModelAndView createEditModelAndView(final QuestionForm questionForm, final String message) {
		ModelAndView res;
		
		res = new ModelAndView("question/edit");
		
		res.addObject("questionForm", questionForm);
		res.addObject("message",message);
		res.addObject("requestURI","question/officer/edit.do");
		
		return res;
	}

}
