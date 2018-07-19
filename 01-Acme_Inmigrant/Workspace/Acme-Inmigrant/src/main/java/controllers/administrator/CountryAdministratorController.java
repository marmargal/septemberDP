package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
		Country country;
		CountryForm countryForm;
		
		country = this.countryService.create();
		
		countryForm = this.countryService.construct(country);
		
		res = this.createEditModelAndView(countryForm);
		
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
				System.out.println(oops);
				System.out.println(binding);
				res = this.createEditModelAndView(countryForm, "country.commit.error");
			}
		
		return res;
	}
	
	
	// Delete --------------
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(int countryId){
		
		Country country = countryService.findOne(countryId);
		
		countryService.delete(country);
		
		return new ModelAndView("redirect:list.do");
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
