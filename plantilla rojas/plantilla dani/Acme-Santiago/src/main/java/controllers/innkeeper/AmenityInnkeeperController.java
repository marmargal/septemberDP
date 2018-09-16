package controllers.innkeeper;

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

import services.AmenityService;
import services.InnkeeperService;
import controllers.AbstractController;
import domain.Amenity;
import domain.Inn;
import domain.Innkeeper;
import forms.AmenityForm;

@Controller
@RequestMapping("/amenity/innkeeper")
public class AmenityInnkeeperController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AmenityService amenityService;
	
	@Autowired
	private InnkeeperService innkeeperService;

	// Constructors ---------------------------------------------------------

	public AmenityInnkeeperController() {
		super();
	}

	// Create --------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		AmenityForm amenityForm = new AmenityForm();
		
		res = this.createEditModelAndView(amenityForm);
		
		return res;
	}
	
	// Editing --------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int amenityId) {
		ModelAndView res;
		Amenity amenity;
		AmenityForm amenityForm;
		
		amenity = amenityService.findOne(amenityId);
		amenityForm = amenityService.construct(amenity);
		res = createEditModelAndView(amenityForm);

		return res;
	}

	// Saving --------------
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AmenityForm amenityForm,
			final BindingResult binding){
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(amenityForm, "amenity.params.error");
		}else
			try{
				Amenity amenity = this.amenityService.reconstruct(amenityForm, binding);
				this.amenityService.save(amenity);

				int innId = amenityForm.getInn().getId();
				String url = "redirect:/amenity/list.do?innId=" + innId;
				res = new ModelAndView(url);
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(amenityForm, "amenity.commit.error");
			}
		
		return res;
	}
	
	// Deleting -------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(AmenityForm amenityForm, BindingResult binding) {
		ModelAndView res;
		Amenity amenity = new Amenity();
		try {
			int innId = amenityForm.getInn().getId();
			String url = "redirect:../../amenity/list.do?innId=" + innId;
			
			amenity = this.amenityService.reconstruct(amenityForm, binding);
			
			this.amenityService.delete(amenity);
			res = new ModelAndView(url);
		} catch (Throwable oops) {
			res = createEditModelAndView(amenityForm, "amenity.commit.error");
		}

		return res;
	}

	protected ModelAndView createEditModelAndView(final AmenityForm amenityForm) {
		ModelAndView result;

		result = this.createEditModelAndView(amenityForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final AmenityForm amenityForm,
			final String message) {
		ModelAndView result;
		Innkeeper innkeeper = new Innkeeper();
		Collection<Inn> inns = new ArrayList<Inn>();
		
		innkeeper = this.innkeeperService.findByPrincipal();
		inns = innkeeper.getInns();
		
		result = new ModelAndView("amenity/edit");
		result.addObject("amenityForm", amenityForm);
		result.addObject("inns", inns);
		result.addObject("message", message);
		result.addObject("requestURI", "amenity/innkeeper/edit.do");

		return result;
	}

}
