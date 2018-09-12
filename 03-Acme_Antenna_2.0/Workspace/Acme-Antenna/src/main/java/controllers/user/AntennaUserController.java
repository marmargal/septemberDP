package controllers.user;

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

import services.AntennaService;
import services.SatelliteService;
import services.UserService;
import controllers.AbstractController;
import domain.Antenna;
import domain.Satellite;
import domain.User;
import forms.AntennaForm;

@Controller
@RequestMapping("/antenna/user")
public class AntennaUserController extends AbstractController {
	
	// Services -------------------------------------------------------------

	@Autowired
	private AntennaService antennaService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SatelliteService satelliteService;

	
	// Constructors ---------------------------------------------------------

	public AntennaUserController() {
		super();
	}

	// Display
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public ModelAndView display(@RequestParam int antennaId){
		this.userService.checkAuthority();
		this.antennaService.checkAntennaIsOfUserLogged(antennaId);
		ModelAndView res;
		Antenna antenna = new Antenna();
		
		antenna = this.antennaService.findOne(antennaId);
	
		res = new ModelAndView("antenna/display");
		res.addObject("antenna", antenna);

		return res;
	}
	
	// Listing
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView list(){
		this.userService.checkAuthority();
		ModelAndView res;
		Collection<Antenna> antennas = new ArrayList<Antenna>();
		User user = this.userService.findByPrincipal();
		
		antennas = this.antennaService.findAntennasByUser(user.getId());
	
		res = new ModelAndView("antenna/list");
		res.addObject("antenna", antennas);
		res.addObject("requestURI","antenna/user/list.do");

		return res;
	}
	
	// Create --------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		this.userService.checkAuthority();
		ModelAndView res;
		
		AntennaForm antennaForm = new AntennaForm();
		
		res = this.createEditModelAndView(antennaForm);
		
		return res;
	}
	
	// Editing --------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int antennaId){
		this.userService.checkAuthority();
		
		ModelAndView res;
		
		Antenna antenna = antennaService.findOne(antennaId);
		AntennaForm antennaForm = antennaService.construct(antenna);
		
		res = createEditModelAndView(antennaForm);
		res.addObject("antennaForm", antennaForm);
		
		return res;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AntennaForm antennaForm,
			final BindingResult binding){
		this.userService.checkAuthority();
		ModelAndView res;
		
		if(binding.hasErrors()){
			res = this.createEditModelAndView(antennaForm, "antenna.params.error");
		}else
			try{
				
				Antenna antenna = this.antennaService.reconstruct(antennaForm, binding);
				this.antennaService.save(antenna);

				res = new ModelAndView("redirect:/antenna/user/list.do");
			}catch (final Throwable oops) {
				System.out.println(oops);
				res = this.createEditModelAndView(antennaForm, "antenna.commit.error");
			}
		
		return res;
	}
	
	// Delete --------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(AntennaForm antennaForm, BindingResult binding){
		this.userService.checkAuthority();
		
		ModelAndView res;
		
		try{
			Antenna antenna = this.antennaService.findOne(antennaForm.getId());
			antennaService.delete(antenna);
			res = new ModelAndView("redirect:/antenna/user/list.do");
		}catch (Throwable oops) {
			res = createEditModelAndView(antennaForm, "antenna.commit.error");
		}
		
		return res;
	}
	
	protected ModelAndView createEditModelAndView(final AntennaForm antennaForm) {
		ModelAndView res;
		
		res = this.createEditModelAndView(antennaForm,null);
		
		return res;
	}

	protected ModelAndView createEditModelAndView(final AntennaForm antennaForm, final String message) {
		ModelAndView res;
		Collection<Satellite> satellites = new ArrayList<Satellite>();
		
		satellites = this.satelliteService.findAll();
		res = new ModelAndView("antenna/edit");
		
		res.addObject("antennaForm", antennaForm);
		res.addObject("message",message);
		res.addObject("satellites",satellites);
		res.addObject("requestURI","antenna/user/edit.do");
		
		return res;
	}
	
	
}
