package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;
import forms.MessageForm;


@Service
@Transactional
public class MessageService {

	// Managed repository

	@Autowired
	private MessageRepository messageRepository;

	// Suporting services

	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private Validator		validator;
	
	// Constructors

	public MessageService() {
		super();
	}

	// Simple CRUD methods

	public Message create() {
		Message res = new Message();
		
		Collection<Folder> foldersRecipient = new ArrayList<Folder>();
		
		res.setFoldersRecipient(foldersRecipient);
		res.setIsDelete(false);
		res.setMoment(new Date(System.currentTimeMillis() - 100));
		
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
	
	public void flush() {
		this.messageRepository.flush();
	}

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
		Collection<Message> messages = new ArrayList<Message>();
		Actor actor = this.actorService.findByPrincipal();
		List<Folder> folders = (List<Folder>) message.getFoldersRecipient();

		for(int i=0; i<folders.size(); i++){
			Folder folder = folders.get(i);
			
			//Eliminamos el mensaje de la carpeta In Box
			if(folder.getActor() == actor){
				messages = folder.getMessages();
				messages.remove(message);
				folder.setMessages(messages);
			}
			
			//Añadimos el mensaje a la carpeta Trash
			Collection<Folder> foldersByActor = actor.getFolders();
			for(Folder f: foldersByActor){
				if(f.getName().equals("Trash Box")){
					Collection<Message> messagesInTrash = new ArrayList<Message>();
					messagesInTrash = f.getMessages();
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
	
	public Message reconstruct(MessageForm messageForm, BindingResult binding){
		Assert.notNull(messageForm);
		
		Message res = new Message();

		if (messageForm.getId() != 0)
			res = this.findOne(messageForm.getId());
		else
			res = this.create();
		
		Collection<Folder> foldersRecipient = new ArrayList<Folder>();
		
		res.setSubject(messageForm.getSubject());
		res.setBody(messageForm.getBody());
		res.setPriority(messageForm.getPriority());
		
		for(Actor actor: messageForm.getRecipients()){
			Collection<Folder> folders = actor.getFolders();
			for(Folder folder: folders){
				if(folder.getName().equals("In Box")){
					foldersRecipient.add(folder);
				}
			}
		}
		
		Actor actor = this.actorService.findByPrincipal();
		for(Folder folder: actor.getFolders()){
			if(folder.getName().equals("Out Box")){
				res.setFolder(folder);
			}
		}
		
		res.setFoldersRecipient(foldersRecipient);
		
		this.validator.validate(res, binding);

		return res;
	}
	
	public void loadFolders(Message message){
		for(Folder folder: message.getFoldersRecipient()){
			Collection<Message> messagesInFolder = new ArrayList<Message>();
			
			messagesInFolder = folder.getMessages();
			messagesInFolder.add(message);
			folder.setMessages(messagesInFolder);
			this.folderService.save(folder);
		}
	}

}
