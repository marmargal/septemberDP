package controllers.boss;

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

import services.CompanyService;
import services.EmployeeService;
import services.StandService;
import controllers.AbstractController;
import domain.Company;
import domain.Employee;
import domain.Stand;

@Controller
@RequestMapping("/stand/boss")
public class StandBossController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private StandService standService;
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private EmployeeService employeeService;
	
	// Constructors -----------------------------------------------------------

	public StandBossController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Stand stand;

		stand = this.standService.create();
		res = this.createEditModelAndView(stand);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int standId) {
		ModelAndView result;
		Stand stand;
		if (standId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.standService.findOne(standId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			stand = this.standService.findOne(standId);
			result = this.createEditModelAndView(stand);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Stand stand, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(stand, "stand.params.error");
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.standService.save(stand);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this
						.createEditModelAndView(stand, "stand.commit.error");
			}
		return res;
	}

	// Delete ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Stand stand, final BindingResult binding) {
		ModelAndView res;
		try {
			this.standService.delete(stand);
			res = new ModelAndView("redirect:/stand/list.do");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = new ModelAndView("redirect:../../");
		}

		return res;
	}
	
	// Affiliate ---------------------------------------------------------------
	
	@RequestMapping(value = "/affiliate")
	public ModelAndView affiliate(@RequestParam(defaultValue = "0") final int standId) {
		ModelAndView result;
		Stand stand = null;

		if (standId == 0) {
			result = new ModelAndView("redirect:../../");
		} else if (this.standService.findOne(standId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			stand = this.standService.findOne(standId);
			result = this.createEditModelAndViewAffiliate(stand);
		}

		return result;
	}

	@RequestMapping(value = "/affiliate", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAffiliate(@Valid Stand stand, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndViewAffiliate(stand, "stand.params.error");
		else
			try {
				standService.joinVoluntary(stand);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewAffiliate(stand, "stand.commit.error");
			}
		return res;
	}
	
	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Stand stand) {
		ModelAndView result;

		result = this.createEditModelAndView(stand, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Stand stand,
			final String message) {
		ModelAndView result;
		Collection<Boolean> invalidate = new ArrayList<>();
		invalidate.add(false);
		invalidate.add(true);
		Collection<Company> companies = new ArrayList<>();
		companies = this.companyService.findAll();
		
		result = new ModelAndView("stand/boss/edit");
		result.addObject("stand", stand);
		result.addObject("message", message);
		result.addObject("companies", companies);
		result.addObject("invalidate", invalidate);
		result.addObject("requestURI", "stand/boss/edit.do");
		return result;

	}
	
	protected ModelAndView createEditModelAndViewAffiliate(final Stand stand) {
		ModelAndView result;

		result = this.createEditModelAndViewAffiliate(stand, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewAffiliate(final Stand stand,
			final String message) {
		ModelAndView result;
		Collection<Employee> employees = new ArrayList<>();
		employees = this.employeeService.findAll();

		result = new ModelAndView("stand/boss/affiliate");
		result.addObject("stand", stand);
		result.addObject("message", message);
		result.addObject("employees", employees);
		result.addObject("requestURI", "stand/boss/affiliate.do");

		return result;
	}

}
