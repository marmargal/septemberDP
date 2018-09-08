package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;


@Service
@Transactional
public class MessageService {

	// Managed repository

	@Autowired
	private MessageRepository messageRepository;

	// Suporting services

	@Autowired
	private AdministratorService administratorService;
	
	// Constructors

	public MessageService() {
		super();
	}

	// Simple CRUD methods

	public Message create() {
		Message res = new Message();
		res.setIsDelete(false);
		
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
		this.administratorService.checkAuthority();
		Assert.notNull(message);
		Assert.isTrue(message.getId() != 0);
		Assert.isTrue(messageRepository.exists(message.getId()));
		messageRepository.delete(message);
	}

	// Other business methods
	
	public Collection<Message> findMessagesNotDeleted() {
		Collection<Message> messages = new ArrayList<Message>();
		messages = this.messageRepository.findMessagesNotDeleted();
		return messages;
	}
	
	public Collection<Message> findMessagesDeleted() {
		Collection<Message> messages = new ArrayList<Message>();
		messages = this.messageRepository.findMessagesDeleted();
		return messages;
	}
	
	public void moveToTrash(Message message) {
		this.administratorService.checkAuthority();
		List<Folder> folders = (List<Folder>) message.getFoldersRecipient(); 
		for(int i=0; i<folders.size(); i++){
			Folder folder = folders.get(i);
			
			//Eliminamos el mensaje de las carpeta In Box
			Collection<Message> messages = folder.getMessages();
			messages.remove(message);
			folder.setMessages(messages);
			
			//Añadimos el mensaje a la carpeta Trash
			Actor actor = folder.getActor();
			Collection<Folder> foldersByActor = actor.getFolders();
			for(Folder f: foldersByActor){
				if(f.getName().equals("Trash Box")){
					Collection<Message> messagesInTrash = f.getMessages();
					messagesInTrash.add(message);
					f.setMessages(messagesInTrash);
					
					//Actualizamos el recipient del mensaje (InBox por Trash)
					Collection<Folder> foldersRecipient = new ArrayList<Folder>();
					foldersRecipient = message.getFoldersRecipient();
					foldersRecipient.remove(folder);
					foldersRecipient.add(f);
					message.setFoldersRecipient(foldersRecipient);
				}
			}
			message.setIsDelete(true);
			messageRepository.save(message);
		}
		
	}
	

}
