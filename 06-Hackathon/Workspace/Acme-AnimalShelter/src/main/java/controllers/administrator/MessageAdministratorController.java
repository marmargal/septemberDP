/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.administrator;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import controllers.AbstractController;
import domain.Message;

@Controller
@RequestMapping("/message/administrator")
public class MessageAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private MessageService messageService;
	
	// Constructors -----------------------------------------------------------

	public MessageAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------		
	
	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<Message> messages = new ArrayList<Message>(); 
		messages = this.messageService.findMessagesNotDeleted();
		
		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("viewForTrueDelete" , true);
		result.addObject("requestURI", "message/administrator/list.do");

		return result;
	}
	
	@RequestMapping("/listAllInTrash")
	public ModelAndView listAllInInTrash() {
		ModelAndView result;

		Collection<Message> messages = new ArrayList<Message>(); 
		messages = this.messageService.findMessagesDeleted();
		
		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("viewForTrueDelete" , true);
		result.addObject("requestURI", "message/administrator/listAllInTrash.do");

		return result;
	}

	// Delete -----------------------------------------------------------------------------
	
	@RequestMapping(value="/delete",method=RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam(defaultValue = "0") int messageId){
		ModelAndView res;
		try{
			Message message = this.messageService.findOne(messageId);
			this.messageService.delete(message);
			res = new ModelAndView("redirect:/message/administrator/listAllInTrash.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}


}
