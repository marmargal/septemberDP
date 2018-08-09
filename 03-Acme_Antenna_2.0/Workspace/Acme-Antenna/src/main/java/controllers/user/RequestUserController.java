package controllers.user;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RequestService;
import services.UserService;

import controllers.AbstractController;
import domain.Request;
import domain.User;
import forms.RequestForm;

@Controller
@RequestMapping("/request/user")
public class RequestUserController extends AbstractController{
	
	// Services ---------------------------------------------
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private UserService userService;
	
	
	// Constructor -------------------------------------------
	public RequestUserController(){
		super();
	}
	
	// Create ------------------------------------------------
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public ModelAndView create(){
		userService.checkAuthority();
		
		ModelAndView res;
		RequestForm requestForm = new RequestForm();
		
		res = this.createEditModelAndView(requestForm);
		
		return res;
	}
	
	// Save --------------------------------------------------
	@RequestMapping(value="/create", method=RequestMethod.POST, params ="save")
	public ModelAndView save(@Valid final RequestForm requestForm, final BindingResult binding){
		ModelAndView res;
		 
		if(binding.hasErrors())
			res = this.createEditModelAndView(requestForm, "request.params.error");
		else{
			try{
				Request request = this.requestService.reconstruct(requestForm, binding);
				this.requestService.save(request);
				
				res = new ModelAndView(
						"redirect:../../");
			}catch (final Throwable oops) {
				res = this.createEditModelAndView(requestForm, "request.commit.error");
			}
		}
		return res;
	}
	
	
	// Ancillary methods -----------------------------
		private ModelAndView createEditModelAndView(final RequestForm requestForm) {
			ModelAndView res;
			
			res = this.createEditModelAndView(requestForm,null);
			
			return res;
		}

		private ModelAndView createEditModelAndView(final RequestForm requestForm,
				final String message) {
			
			User user = this.userService.findByPrincipal();
			
			ModelAndView res = new ModelAndView("request/create");
			res.addObject("requestForm",requestForm);
			res.addObject("message",message);
			res.addObject("antennas", user.getAntennas());
			res.addObject("requestURI","request/user/create.do");
		
			return res;
		}
}
