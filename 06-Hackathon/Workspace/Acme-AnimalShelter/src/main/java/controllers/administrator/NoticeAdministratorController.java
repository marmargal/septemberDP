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

import services.NoticeService;
import controllers.AbstractController;
import domain.Notice;

@Controller
@RequestMapping("/notice/administrator")
public class NoticeAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private NoticeService noticeService;
	
	// Constructors -----------------------------------------------------------

	public NoticeAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView listClientBan() {
		ModelAndView result;

		Collection<Notice> notices = new ArrayList<Notice>(); 
		notices = this.noticeService.findAll();
		
		result = new ModelAndView("notice/list");
		result.addObject("notices", notices);

		return result;
	}
	
	// Display ---------------------------------------------------------------		

	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(defaultValue = "0") int noticeId) {
		ModelAndView result;

		Notice notice = new Notice(); 
		notice = this.noticeService.findOne(noticeId);
		
		result = new ModelAndView("notice/display");
		result.addObject("notice", notice);

		return result;
	}
	
	// Delete ---------------------------------------------------------------
	@RequestMapping(value="/delete",method=RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam(defaultValue = "0") int noticeId){
		ModelAndView res;
		try{
			Notice notice = this.noticeService.findOne(noticeId);
			this.noticeService.delete(notice);
			res = new ModelAndView("redirect:/notice/administrator/list.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}


}
