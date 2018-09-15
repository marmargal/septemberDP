package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LawService;
import services.RequirementService;
import controllers.AbstractController;
import domain.Law;
import domain.Requirement;
import forms.RequirementForm;

@Controller
@RequestMapping("/requirement/administrator")
public class RequirementAdministratorController extends AbstractController{

	// Services -------------------------------------------------------------

	@Autowired
	private RequirementService requirementService;

	@Autowired
	private LawService lawService;

	
	// Constructors ---------------------------------------------------------

	public RequirementAdministratorController() {
		super();
	}
	
	// Listing
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView res;
		
		Collection<Requirement> requirements = this.requirementService.findAll();
		
		res = new ModelAndView("requirement/list");
		res.addObject("requirement",requirements);
		res.addObject("requestURI", "requirement/administrator/list.do");
		
		return res;
	}
	
	// Create --------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		RequirementForm requirementForm = new RequirementForm();
		
		res = this.createEditModelAndView(requirementForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requirementId){
		
		ModelAndView res;
		
		Requirement requirement = requirementService.findOne(requirementId);
		RequirementForm requirementForm = requirementService.construct(requirement);
		
		res = createEditModelAndView(requirementForm);
		res.addObject("requirementForm", requirementForm);
		
		return res;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RequirementForm requirementForm,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(requirementForm, "requirement.params.error");
		}else
			try{
				Requirement requirement = this.requirementService.reconstruct(requirementForm, binding);
				this.requirementService.save(requirement);

				res = new ModelAndView("redirect:/requirement/administrator/list.do");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(requirementForm, "requirement.commit.error");
			}
		
		return res;
	}
	
	
	// Delete --------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(RequirementForm requirementForm, BindingResult binding){
		
		ModelAndView res;
		
		try{
			Requirement requirement = this.requirementService.reconstruct(requirementForm, binding);
			requirementService.delete(requirement);
			res = new ModelAndView("redirect:/requirement/administrator/list.do");
		}catch (Throwable oops) {
			res = createEditModelAndView(requirementForm, "requirement.commit.error");
		}
		
		return res;
	}
	
	
	protected ModelAndView createEditModelAndView(final RequirementForm requirementForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(requirementForm,null);
		
		return res;
	}

	protected ModelAndView createEditModelAndView(final RequirementForm requirementForm, final String message) {
		ModelAndView res;
		
		Collection<Law> laws = this.lawService.findAll(); 
		
		res = new ModelAndView("requirement/edit");
		
		res.addObject("requirementForm", requirementForm);
		res.addObject("laws",laws);
		res.addObject("message",message);
		res.addObject("requestURI","requirement/administrator/edit.do");
		
		return res;
	}

}
