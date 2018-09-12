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

import services.ApplicationService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping("/application/administrator")
public class ApplicationAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private ApplicationService applicationService;
	
	// Constructors -----------------------------------------------------------

	public ApplicationAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/listPending")
	public ModelAndView listPending() {
		ModelAndView result;

		Collection<Application> applications = new ArrayList<Application>(); 
		applications = this.applicationService.findApplicationsPending();
		
		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("method", "listPending");
		result.addObject("viewForDelete" , true);
		result.addObject("requestURI", "application/administrator/listPending.do");

		return result;
	}
	
	@RequestMapping("/listClientBan")
	public ModelAndView listClientBan() {
		ModelAndView result;

		Collection<Application> applications = new ArrayList<Application>(); 
		applications = this.applicationService.findApplicationsClientBan();
		
		result = new ModelAndView("application/list");
		result.addObject("applications", applications);
		result.addObject("method", "listClientBan");
		result.addObject("viewForDelete" , true);
		result.addObject("requestURI", "application/administrator/listClientBan.do");

		return result;
	}
	
	// Delete ---------------------------------------------------------------
	@RequestMapping(value="/delete",method=RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam(defaultValue = "0") int applicationId, @RequestParam String method){
		ModelAndView res;
		try{
			Application application = this.applicationService.findOne(applicationId);
			this.applicationService.delete(application);
			res = new ModelAndView("redirect:/application/administrator/" + method + ".do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}


}
