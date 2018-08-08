package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.UserService;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class RegisterUserController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private UserService userService;


	// Constructors ---------------------------------------------------------

	public RegisterUserController() {
		super();
	}
	
	// Registering ----------------------------------------------------------
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView res;
		User user;
		user = this.userService.create();
		
		UserForm userForm;
		userForm = new UserForm(user);
		
		res = new ModelAndView("user/register");
		res.addObject("userForm", userForm);
		
		return res;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@ModelAttribute("userForm") UserForm userForm,
			final BindingResult binding) {
		ModelAndView res;
		userForm = this.userService.reconstruct(userForm, binding);
		
		System.out.println(binding.getFieldError());
		System.out.println(binding.getClass());
		
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(userForm, "actor.params.error");
		} else {
			try {
				if ((userForm.getUser().getId() == 0)) {
					Assert.isTrue(userForm.getUser().getUserAccount().getPassword().equals(userForm.getConfirmPassword()), "password does not match");
				}
				this.userService.save(userForm.getUser());
				res = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				if (oops.getMessage().equals("password does not match"))
					res = this.createEditModelAndView(userForm, "actor.password.check");
				else if (oops.getMessage().equals("could not execute statement; SQL [n/a]; constraint [null]" + "; nested exception is org.hibernate.exception.ConstraintViolationException: could not execute statement"))
					res = this.createEditModelAndView(userForm, "actor.commit.duplicate");
				else
					res = this.createEditModelAndView(userForm, "actor.commit.error");
			}
		}
		return res;
	}
	
	// Ancillary methods --------------------------------------------------
	
		protected ModelAndView createEditModelAndView(final UserForm userForm) {
			ModelAndView result;

			result = this.createEditModelAndView(userForm, null);

			return result;
		}
		
		protected ModelAndView createEditModelAndView(final UserForm userForm,
				final String message) {
			ModelAndView result;

			result = new ModelAndView("user/register");
			result.addObject("user", userForm);
			result.addObject("message", message);
			
			return result;
		}
}