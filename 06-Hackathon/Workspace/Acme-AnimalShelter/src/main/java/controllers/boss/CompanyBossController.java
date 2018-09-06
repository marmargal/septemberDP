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
import services.EventService;
import controllers.AbstractController;
import domain.Company;
import domain.Event;

@Controller
@RequestMapping("/company/boss")
public class CompanyBossController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private EventService eventService;

	// Constructors -----------------------------------------------------------

	public CompanyBossController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Company company;

		company = this.companyService.create();
		res = this.createEditModelAndView(company);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int companyId) {
		ModelAndView result;
		Company company;
		if (companyId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.companyService.findOne(companyId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			company = this.companyService.findOne(companyId);
			result = this.createEditModelAndView(company);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Company company, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(company, "company.params.error");
			System.out.println(binding.getAllErrors());
		} else
			try {
				this.companyService.save(company);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this.createEditModelAndView(company,
						"company.commit.error");
			}
		return res;
	}

	// Delete ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Company company,
			final BindingResult binding) {
		ModelAndView res;
		try {
			this.companyService.delete(company);
			res = new ModelAndView("redirect:/company/list.do");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = new ModelAndView("redirect:../../");
		}

		return res;
	}

	// Affiliate ---------------------------------------------------------------

	@RequestMapping(value = "/affiliate")
	public ModelAndView affiliate(
			@RequestParam(defaultValue = "0") final int companyId) {
		ModelAndView result;
		Company company = null;

		if (companyId == 0) {
			result = new ModelAndView("redirect:../../");
		} else if (this.companyService.findOne(companyId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			company = this.companyService.findOne(companyId);
			result = this.createEditModelAndViewAffiliate(company);
		}

		return result;
	}

	@RequestMapping(value = "/affiliate", method = RequestMethod.POST, params = "save")
	public ModelAndView saveAffiliate(@Valid Company company,
			final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndViewAffiliate(company,
					"company.params.error");
		else
			try {
				companyService.save(company);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndViewAffiliate(company,
						"company.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Company company) {
		ModelAndView result;

		result = this.createEditModelAndView(company, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Company company,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("company/boss/edit");
		result.addObject("company", company);
		result.addObject("message", message);
		result.addObject("requestURI", "company/boss/edit.do");
		return result;
	}
	
	protected ModelAndView createEditModelAndViewAffiliate(final Company company) {
		ModelAndView result;

		result = this.createEditModelAndViewAffiliate(company, null);

		return result;
	}

	protected ModelAndView createEditModelAndViewAffiliate(final Company company,
			final String message) {
		ModelAndView result;
		Collection<Event> events = new ArrayList<>();
		events = this.eventService.findAll();

		result = new ModelAndView("company/boss/affiliate");
		result.addObject("company", company);
		result.addObject("message", message);
		result.addObject("events", events);
		result.addObject("requestURI", "company/boss/affiliate.do");

		return result;
	}

}
