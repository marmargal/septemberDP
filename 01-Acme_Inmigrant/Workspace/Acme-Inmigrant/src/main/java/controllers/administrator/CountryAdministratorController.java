package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CountryService;
import controllers.AbstractController;
import domain.Country;
import forms.CountryForm;

@Controller
@RequestMapping("/country/administrator")
public class CountryAdministratorController extends AbstractController{

	// Services -------------------------------------------------------------

	@Autowired
	private CountryService countryService;


	// Constructors ---------------------------------------------------------

	public CountryAdministratorController() {
		super();
	}
	
	// Create --------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		CountryForm countryForm = new CountryForm();
		
		res = this.createEditModelAndView(countryForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int countryId){
		
		ModelAndView res;
		
		Country country = countryService.findOne(countryId);
		CountryForm countryForm = countryService.construct(country);
		
		res = createEditModelAndView(countryForm);
		res.addObject("countryForm", countryForm);
		
		return res;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save( final CountryForm countryForm,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(countryForm, "country.params.error");
		}else
			try{
				
				Country country = this.countryService.reconstruct(countryForm, binding);
				this.countryService.save(country);

				res = new ModelAndView("redirect:/country/list.do");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(countryForm, "country.commit.error");
			}
		
		return res;
	}
	
	
	// Delete --------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(CountryForm countryForm, BindingResult binding){
		
		ModelAndView res;
		
		try{
			Country country = this.countryService.reconstruct(countryForm, binding);
			countryService.delete(country);
			res = new ModelAndView("redirect:/country/list.do");
		}catch (Throwable oops) {
			res = createEditModelAndView(countryForm, "country.commit.error");
		}
		
		return res;
	}
	
	
	protected ModelAndView createEditModelAndView(final CountryForm countryForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(countryForm,null);
		
		return res;
	}

	protected ModelAndView createEditModelAndView(final CountryForm countryForm, final String message) {
		ModelAndView res;
		
		res = new ModelAndView("country/edit");
		
		res.addObject("countryForm", countryForm);
		res.addObject("message",message);
		
		return res;
	}

}
