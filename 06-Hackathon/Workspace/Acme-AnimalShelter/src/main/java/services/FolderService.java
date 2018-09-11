package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;


@Service
@Transactional
public class FolderService {

	// Managed repository

	@Autowired
	private FolderRepository folderRepository;

	// Suporting services
	@Autowired
	private ActorService actorService;

	// Constructors

	public FolderService() {
		super();
	}

	// Simple CRUD methods

	public Folder create() {
		Folder res = new Folder();
		
		Collection<Message> messages = new ArrayList<Message>();
		res.setMessages(messages);
		res.setPredefined(true);
		
		
		return res;

	}

	public Collection<Folder> findAll() {
		Collection<Folder> res;
		res = folderRepository.findAll();
		Assert.notNull(res);
		return res;
	}

	public Folder findOne(int folderId) {
		Assert.isTrue(folderId != 0);
		Folder res;
		res = folderRepository.findOne(folderId);
		Assert.notNull(res);
		return res;
	}

	public Folder save(Folder folder) {
		Folder res;
		res = folderRepository.save(folder);
		return res;
	}

	public void delete(Folder folder) {
		Assert.notNull(folder);
		Assert.isTrue(folder.getId() != 0);
		Assert.isTrue(folderRepository.exists(folder.getId()));
		folderRepository.delete(folder);
	}

	// Other business methods
	
	

}
