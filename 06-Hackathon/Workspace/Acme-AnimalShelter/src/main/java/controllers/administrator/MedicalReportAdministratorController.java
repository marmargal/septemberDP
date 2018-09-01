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

import services.MedicalReportService;
import controllers.AbstractController;
import domain.MedicalReport;

@Controller
@RequestMapping("/medicalReport/administrator")
public class MedicalReportAdministratorController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private MedicalReportService medicalReportService;
	
	// Constructors -----------------------------------------------------------

	public MedicalReportAdministratorController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView result;

		Collection<MedicalReport> medicalReports = new ArrayList<MedicalReport>(); 
		medicalReports = this.medicalReportService.findMedicalReportsVeterinaryBanned();
		
		result = new ModelAndView("medicalReport/list");
		result.addObject("medicalReports", medicalReports);
		result.addObject("viewForDelete" , true);

		return result;
	}
	
	
	// Delete ---------------------------------------------------------------
	@RequestMapping(value="/delete",method=RequestMethod.POST, params = "delete")
	public ModelAndView delete(@RequestParam(defaultValue = "0") int medicalReportId){
		ModelAndView res;
		try{
			MedicalReport medicalReport = this.medicalReportService.findOne(medicalReportId);
			this.medicalReportService.delete(medicalReport);
			res = new ModelAndView("redirect:/medicalReport/administrator/list.do");

		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		
		return res;
	}


}
