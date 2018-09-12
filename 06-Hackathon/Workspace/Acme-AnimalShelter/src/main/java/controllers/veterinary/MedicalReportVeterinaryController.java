package controllers.veterinary;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MedicalReportService;
import services.VeterinaryService;
import controllers.AbstractController;
import domain.MedicalReport;
import domain.Veterinary;

@Controller
@RequestMapping("/medicalReport/veterinary")
public class MedicalReportVeterinaryController extends AbstractController{

	// Services --------------------------------------------
	
	@Autowired
	private MedicalReportService medicalReportService;
	
	@Autowired
	private VeterinaryService veterinaryService;
	
	// Constructors ----------------------------------------
	
	public MedicalReportVeterinaryController(){
		super();
	}
	
	
	// Create ----------------------------------------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(@RequestParam int petId){
		ModelAndView res;
		
		MedicalReport medicalReport = this.medicalReportService.create(petId);
		
		res = this.createEditModelAndView(medicalReport);
		return res;
	}


	// Edit ------------------------------------------------
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public ModelAndView edit(@RequestParam int medicalReportId){
		ModelAndView res;
		
		MedicalReport medicalReport = this.medicalReportService.findOne(medicalReportId);;
		Veterinary veterinary = this.veterinaryService.findByPrincipal();
		
		if(veterinary.equals(medicalReport.getVeterinary()))
			res = this.createEditModelAndView(medicalReport);
		else
			res = new ModelAndView("redirect:../../");
		
		return res;
	}
	
	
	
	// Save ------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid MedicalReport medicalReport, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(medicalReport, "medicalReport.params.error");
		else
			try {
				this.medicalReportService.save(medicalReport);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(medicalReport, "medicalReport.commit.error");
			}
		return res;
	}
	
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(defaultValue = "0") int medicalReportId) {
		ModelAndView result;

		MedicalReport medicalReport = new MedicalReport(); 
		medicalReport = this.medicalReportService.findOne(medicalReportId);
		
		result = new ModelAndView("medicalReport/display");
		result.addObject("medicalReport", medicalReport);

		return result;
	}
	
	
	// Ancillary methods
	
	private ModelAndView createEditModelAndView(final MedicalReport medicalReport) {
		ModelAndView result;

		result = this.createEditModelAndView(medicalReport, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final MedicalReport medicalReport,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("medicalReport/edit");
		result.addObject("medicalReport", medicalReport);
		result.addObject("message", message);
		result.addObject("requestUri", "medicalReport/veterinary/edit.do");

		return result;
	}
}
