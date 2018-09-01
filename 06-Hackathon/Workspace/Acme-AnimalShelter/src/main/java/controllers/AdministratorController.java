/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.ClientService;
import services.EmployeeService;
import services.VeterinaryService;
import services.VoluntaryService;
import domain.Client;
import domain.Employee;
import domain.Veterinary;
import domain.Voluntary;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}
	
	// Supporting services
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private VoluntaryService voluntaryService;
	
	@Autowired
	private VeterinaryService veterinaryService;

	// List actors ---------------------------------------------------------------		

	@RequestMapping("/listEmployees")
	public ModelAndView listEmployees() {
		ModelAndView result;
		Collection<Employee> employees = new ArrayList<Employee>();
		
		employees = this.employeeService.findAll();
		
		result = new ModelAndView("actor/list");
		result.addObject("actors", employees);
		result.addObject("role", "employee");

		return result;
	}
	
	@RequestMapping("/listClients")
	public ModelAndView listClients() {
		ModelAndView result;
		Collection<Client> clients = new ArrayList<Client>();
		
		clients = this.clientService.findAll();
		
		result = new ModelAndView("actor/list");
		result.addObject("actors", clients);
		result.addObject("role", "client");

		return result;
	}
	
	@RequestMapping("/listVoluntaries")
	public ModelAndView listVoluntaries() {
		ModelAndView result;
		Collection<Voluntary> voluntaries = new ArrayList<Voluntary>();
		
		voluntaries = this.voluntaryService.findAll();
		
		result = new ModelAndView("actor/list");
		result.addObject("actors", voluntaries);
		result.addObject("role", "voluntary");

		return result;
	}
	
	@RequestMapping("/listVeterinaries")
	public ModelAndView listVeterinaries() {
		ModelAndView result;
		Collection<Veterinary> veterinaries = new ArrayList<Veterinary>();
		
		veterinaries = this.veterinaryService.findAll();
		
		result = new ModelAndView("actor/list");
		result.addObject("actors", veterinaries);
		result.addObject("role", "veterinary");

		return result;
	}
	
	// Ban ---------------------------------------------------------------
	@RequestMapping(value="/banEmployee",method=RequestMethod.POST, params = "banEmployee")
	public ModelAndView banEmployee(@RequestParam(defaultValue = "0") int employeeId){
		ModelAndView res;
		try{
			this.administratorService.banEmployee(employeeId);
			res = new ModelAndView("redirect:/administrator/listEmployees.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listEmployees.do");
		}
		
		return res;
	}
	
	@RequestMapping(value="/banClient",method=RequestMethod.POST, params = "banClient")
	public ModelAndView banClient(@RequestParam(defaultValue = "0") int clientId){
		ModelAndView res;
		try{
			this.administratorService.banClient(clientId);
			res = new ModelAndView("redirect:/administrator/listClients.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listClients.do");
		}
		
		return res;
	}
	
	@RequestMapping(value="/banVoluntary",method=RequestMethod.POST, params = "banVoluntary")
	public ModelAndView banVoluntary(@RequestParam(defaultValue = "0") int voluntaryId){
		ModelAndView res;
		try{
			this.administratorService.banVoluntary(voluntaryId);
			res = new ModelAndView("redirect:/administrator/listVoluntaries.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listVoluntaries.do");
		}
		
		return res;
	}
	
	@RequestMapping(value="/banVeterinary",method=RequestMethod.POST, params = "banVeterinary")
	public ModelAndView banVeterinary(@RequestParam(defaultValue = "0") int veterinaryId){
		ModelAndView res;
		try{
			this.administratorService.banVeterinary(veterinaryId);
			res = new ModelAndView("redirect:/administrator/listVeterinaries.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listVeterinaries.do");
		}
		
		return res;
	}
	
	
	// Deban ---------------------------------------------------------------
	@RequestMapping(value="/debanEmployee",method=RequestMethod.POST, params = "debanEmployee")
	public ModelAndView debanEmployee(@RequestParam(defaultValue = "0") int employeeId){
		ModelAndView res;
		try{
			this.administratorService.debanEmployee(employeeId);
			res = new ModelAndView("redirect:/administrator/listEmployees.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listEmployees.do");
		}
		
		return res;
	}
	
	@RequestMapping(value="/debanClient",method=RequestMethod.POST, params = "debanClient")
	public ModelAndView debanClient(@RequestParam(defaultValue = "0") int clientId){
		ModelAndView res;
		try{
			this.administratorService.debanClient(clientId);
			res = new ModelAndView("redirect:/administrator/listClients.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listClients.do");
		}
		
		return res;
	}
	
	@RequestMapping(value="/debanVoluntary",method=RequestMethod.POST, params = "debanVoluntary")
	public ModelAndView debanVoluntary(@RequestParam(defaultValue = "0") int voluntaryId){
		ModelAndView res;
		try{
			this.administratorService.debanVoluntary(voluntaryId);
			res = new ModelAndView("redirect:/administrator/listVoluntaries.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listVoluntaries.do");
		}
		
		return res;
	}
	
	@RequestMapping(value="/debanVeterinary",method=RequestMethod.POST, params = "debanVeterinary")
	public ModelAndView debanVeterinary(@RequestParam(defaultValue = "0") int veterinaryId){
		ModelAndView res;
		try{
			this.administratorService.debanVeterinary(veterinaryId);
			res = new ModelAndView("redirect:/administrator/listVeterinaries.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:/administrator/listVeterinaries.do");
		}
		
		return res;
	}

}
