package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.QuestionRepository;
import domain.Answer;
import domain.Question;

@Service
@Transactional
public class QuestionService {

	// Managed repository

	@Autowired
	private QuestionRepository questionRepository;

	// Suporting services
	@Autowired
	private AnswerService answerService;

	// Constructors

	public QuestionService() {
		super();
	}

	// Simple CRUD methods

	public Question create() {
		Question res = new Question();

		Answer answer = new Answer();

		Date moment = new Date(System.currentTimeMillis() - 1000);

		res.setMoment(moment);
		res.setAnswer(answer);

		return res;

	}

	public Collection<Question> findAll() {
		Collection<Question> res;
		res = questionRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Question findOne(int questionId) {
		Assert.isTrue(questionId != 0);
		Question res;
		res = questionRepository.findOne(questionId);
		Assert.notNull(res);
		return res;
	}

	public Question save(Question question) {
		Question res;
		res = questionRepository.save(question);
		return res;
	}

	public void delete(Question question) {
		Assert.notNull(question);
		Assert.isTrue(question.getId() != 0);
		Assert.isTrue(questionRepository.exists(question.getId()));
		questionRepository.delete(question);
	}

}
