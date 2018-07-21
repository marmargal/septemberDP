package services;

import java.sql.Date;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.AnswerRepository;
import domain.Answer;
import domain.Immigrant;
import domain.Question;
import forms.AnswerForm;

@Service
@Transactional
public class AnswerService {

	// Managed repository

	@Autowired
	private AnswerRepository answerRepository;

	// Suporting services
	@Autowired
	private ImmigrantService immmigrantService;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private Validator		validator;

	// Constructors

	public AnswerService() {
		super();
	}

	// Simple CRUD methods

	public Answer create(int questionId) {
		Answer res = new Answer();
		
		Question question = this.questionService.findOne(questionId);
		Date moment = new Date(System.currentTimeMillis() - 1000);

		Immigrant immigrant = immmigrantService.findByPrincipal();

		res.setMoment(moment);
		res.setImmigrant(immigrant);
		res.setQuestion(question);

		return res;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> res;
		res = answerRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Answer findOne(int answerId) {
		Assert.isTrue(answerId != 0);
		Answer res;
		res = answerRepository.findOne(answerId);
		Assert.notNull(res);
		return res;
	}

	public Answer save(Answer answer) {
		this.immmigrantService.checkAuthority();
		this.questionService.checkAplicationNotApply(answer.getQuestion().getApplication().getId());
		Answer res;
		Question question = answer.getQuestion();
		
		question.setStatement(true);
		
		res = answerRepository.save(answer);
		return res;
	}

	public void delete(Answer answer) {
		Assert.notNull(answer);
		Assert.isTrue(answer.getId() != 0);
		Assert.isTrue(answerRepository.exists(answer.getId()));
		answerRepository.delete(answer);
	}
	
	public Answer findAnswerByQuestion(int questionId){
		Answer answer = this.answerRepository.findAnswerByQuestion(questionId);

		return answer;
	}
	
	public AnswerForm construct(Answer answer){
		AnswerForm res = new AnswerForm();
		
		res.setId(answer.getId());
		res.setQuestionId(answer.getQuestion().getId());
		res.setReply(answer.getReply());
		
		return res;
	}
	
	public Answer reconstruct(AnswerForm answerForm, BindingResult binding){
		Assert.notNull(answerForm);
		
		Answer res = new Answer();

		if (answerForm.getId() != 0)
			res = this.findOne(answerForm.getId());
		else 
			res = this.create(answerForm.getQuestionId());
		
		res.setReply(answerForm.getReply());

		this.validator.validate(res, binding);

		return res;
	}

}
