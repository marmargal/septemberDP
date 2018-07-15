package services;

import java.sql.Date;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;
import domain.Immigrant;

@Service
@Transactional
public class AnswerService {

	// Managed repository

	@Autowired
	private AnswerRepository answerRepository;

	// Suporting services
	@Autowired
	private ImmigrantService immmigrantService;

	// Constructors

	public AnswerService() {
		super();
	}

	// Simple CRUD methods

	public Answer create() {
		Answer res = new Answer();

		String reply = "reply";
		Date moment = new Date(System.currentTimeMillis() - 1000);

		Immigrant immigrant = immmigrantService.findByPrincipal();

		res.setReply(reply);
		res.setMoment(moment);

		res.setImmigrant(immigrant);

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
		Answer res;
		res = answerRepository.save(answer);
		return res;
	}

	public void delete(Answer answer) {
		Assert.notNull(answer);
		Assert.isTrue(answer.getId() != 0);
		Assert.isTrue(answerRepository.exists(answer.getId()));
		answerRepository.delete(answer);
	}

}
