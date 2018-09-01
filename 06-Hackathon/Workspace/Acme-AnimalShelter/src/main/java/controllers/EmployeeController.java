package controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.EmployeeService;
import domain.Employee;
import forms.ActorForm;

@Controller
@RequestMapping("/employee")
public class EmployeeController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private EmployeeService employeeService;

	// Constructors ---------------------------------------------------------

	public EmployeeController() {
		super();
	}

	// Editing ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(){
		
		ModelAndView res;
		
		Employee employee = this.employeeService.findByPrincipal();
		ActorForm employeeForm = this.employeeService.construct(employee);
		
		res = createEditModelAndViewEdit(employeeForm);
		res.addObject("actrForm", employeeForm);
		
		return res;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView saveEdit(@Valid final ActorForm employeeForm, final BindingResult binding) {
		ModelAndView res;
		Employee employee;

		if (binding.hasErrors())
			res = this.createEditModelAndViewEdit(employeeForm, "actor.params.error");
		else if (!employeeForm.getRepeatPassword().equals(employeeForm.getPassword()))
			res = this.createEditModelAndViewEdit(employeeForm, "actor.commit.errorPassword");
		else
			try {
				employee = employeeService.reconstruct(employeeForm, binding);
				this.employeeService.save(employee);
				res = new ModelAndView("redirect:/j_spring_security_logout");
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewEdit(employeeForm, "actor.commit.error");
			}

		return res;
	}
	

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndViewEdit(final ActorForm actorForm) {
		ModelAndView result;

		result = this.createEditModelAndViewEdit(actorForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewEdit(final ActorForm actorForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", actorForm);
		result.addObject("message", message);
		result.addObject("requestURI","employee/edit.do");

		return result;
	}
}
