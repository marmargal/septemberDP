package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AntennaService;
import services.UserService;
import controllers.AbstractController;
import domain.Antenna;
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

	
	// Constructors ---------------------------------------------------------

	public AntennaUserController() {
		super();
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
	public ModelAndView save( final AntennaForm antennaForm,
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
				res = this.createEditModelAndView(antennaForm, "antenna.commit.error");
			}
		
		return res;
	}
	
	// Delete --------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(AntennaForm anntenaForm, BindingResult binding){
		this.userService.checkAuthority();
		
		ModelAndView res;
		
		try{
			Antenna antenna = this.antennaService.reconstruct(anntenaForm, binding);
			antennaService.delete(antenna);
			res = new ModelAndView("redirect:/antenna/user/list.do");
		}catch (Throwable oops) {
			res = createEditModelAndView(anntenaForm, "antenna.commit.error");
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
		
		res = new ModelAndView("question/edit");
		
		res.addObject("antennaForm", antennaForm);
		res.addObject("message",message);
		res.addObject("requestURI","antenna/user/edit.do");
		
		return res;
	}
	
	
}
