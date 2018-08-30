package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Message;


@Service
@Transactional
public class MessageService {

	// Managed repository

	@Autowired
	private MessageRepository messageRepository;

	// Suporting services

	// Constructors

	public MessageService() {
		super();
	}

	// Simple CRUD methods

	public Message create() {
		Message res = new Message();
		
		return res;

	}

	public Collection<Message> findAll() {
		Collection<Message> res;
		res = messageRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Message findOne(int messageId) {
		Assert.isTrue(messageId != 0);
		Message res;
		res = messageRepository.findOne(messageId);
		Assert.notNull(res);
		return res;
	}

	public Message save(Message message) {
		Message res;
		res = messageRepository.save(message);
		return res;
	}

	public void delete(Message message) {
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(messageRepository.exists(message.getId()));
		messageRepository.delete(message);
	}

	// Other business methods
	
	

}
