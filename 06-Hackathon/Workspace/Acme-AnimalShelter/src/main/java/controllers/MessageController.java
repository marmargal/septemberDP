package controllers;

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

import services.ActorService;
import services.MessageService;
import domain.Actor;
import domain.Folder;
import domain.Message;
import forms.MessageForm;

@Controller
@RequestMapping("/message/actor")
public class MessageController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private MessageService messageService;

	// Constructors ---------------------------------------------------------

	public MessageController() {
		super();
	}
	
	// List ---------------------------------------------------------------		

	@RequestMapping("/listInBox")
	public ModelAndView listPending() {
		ModelAndView result;

		Actor actor = this.actorService.findByPrincipal();
		Collection<Message> allMessages = new ArrayList<Message>();
		Collection<Message> messages = new ArrayList<Message>();
		Collection<Folder> folders = new ArrayList<Folder>();
		
		allMessages = this.messageService.findAll();
		for(Message m: allMessages){
			folders = m.getFoldersRecipient();
			for(Folder folder: folders){
				if(folder.getName().equals("In Box") && folder.getActor() == actor){
					messages.add(m);
				}
			}
		}
		
		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/listInBox.do");
		result.addObject("viewForDeleteToTrash" , true);

		return result;
	}
	
	@RequestMapping("/listOutBox")
	public ModelAndView listOutBox() {
		ModelAndView result;

		Actor actor = this.actorService.findByPrincipal();
		Collection<Message> messages = new ArrayList<Message>();
		Collection<Folder> folders = new ArrayList<Folder>();
		
		folders = actor.getFolders();
		for(Folder folder: folders){
			if(folder.getName().equals("Out Box")){
				messages = folder.getMessages();
			}
		}
		
		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/listOutBox.do");

		return result;
	}
	
	@RequestMapping("/listTrashBox")
	public ModelAndView listTrashBox() {
		ModelAndView result;

		Actor actor = this.actorService.findByPrincipal();
		Collection<Message> allMessages = new ArrayList<Message>();
		Collection<Message> messages = new ArrayList<Message>();
		Collection<Folder> folders = new ArrayList<Folder>();
		
		allMessages = this.messageService.findAll();
		for(Message m: allMessages){
			folders = m.getFoldersRecipient();
			for(Folder folder: folders){
				if(folder.getName().equals("Trash Box") && folder.getActor() == actor){
					messages.add(m);
				}
			}
		}
		
		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/listTrashBox.do");
		result.addObject("viewForDelete" , true);

		return result;
	}
	
	// Delete -----------------------------------------------------------------------------
	
	@RequestMapping(value="/deleteToTrash",method=RequestMethod.POST, params = "deleteToTrash")
	public ModelAndView deleteToTrash(@RequestParam(defaultValue = "0") int messageId){
		ModelAndView res;
		try{
			Message message = this.messageService.findOne(messageId);
			this.messageService.moveToTrash(message);
			res = new ModelAndView("redirect:/message/actor/listInBox.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}
	
	@RequestMapping(value="/deleteOfTrash",method=RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam(defaultValue = "0") int messageId){
		ModelAndView res;
		try{
			Message message = this.messageService.findOne(messageId);
			this.messageService.deleteOfTrash(message);
			res = new ModelAndView("redirect:/message/actor/listTrashBox.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}
	
	// Creating ----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		
		ModelAndView res;
		MessageForm messageForm = new MessageForm();
		
		res = createEditModelAndView(messageForm);
		res.addObject("messageForm", messageForm);
		
		return res;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final MessageForm messageForm, final BindingResult binding) {
		ModelAndView res;
		Message message = new Message();

		if (binding.hasErrors())
			res = this.createEditModelAndView(messageForm, "message.params.error");
		else
			try {
				message = this.messageService.reconstruct(messageForm, binding);
				this.messageService.save(message);
				this.messageService.loadFolders(message);
				res = new ModelAndView("redirect:/message/actor/listOutBox.do");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(messageForm, "message.commit.error");
			}

		return res;
	}
	

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final MessageForm messageForm) {
		ModelAndView result;

		result = this.createEditModelAndView(messageForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MessageForm messageForm,
			final String message) {
		ModelAndView result;

		Collection<Actor> actors = new ArrayList<Actor>();
		actors = this.actorService.findAll();
		
		result = new ModelAndView("message/edit");
		result.addObject("messageForm", messageForm);
		result.addObject("actors", actors);
		result.addObject("message", message);
		result.addObject("requestURI","message/actor/create.do");

		return result;
	}
}
