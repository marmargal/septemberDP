package controllers.administrator;

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

import services.AdministratorService;
import services.BridService;
import controllers.AbstractController;
import domain.Administrator;
import domain.Brid;

@Controller
@RequestMapping("/brid/administrator")
public class BridAdministratorController extends AbstractController {

	@Autowired
	private BridService bridService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors ---------------------------------------------------------

	public BridAdministratorController() {
		super();
	}

	// Listing ---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView res;
		Collection<Brid> brids = new ArrayList<Brid>();

		Administrator admin = this.administratorService.findByPrincipal();

		brids = this.bridService.findBridsWithoutDecision();

		res = new ModelAndView("brid/administrator/list");
		res.addObject("brids", brids);
		res.addObject("admin", admin);
		res.addObject("requestURI", "brid/administrator/list.do");

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam(defaultValue = "0") final int bridId) {
		ModelAndView result;
		Brid brid;

		if (bridId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.bridService.findOne(bridId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {

			brid = this.bridService.findOne(bridId);
			result = this.createEditModelAndView(brid);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Brid brid, final BindingResult binding) {
		ModelAndView res;
		Administrator admin;
		admin = administratorService.findByPrincipal();
		if (binding.hasErrors())
			res = this.createEditModelAndView(brid, "brid.params.error");
		else
			try {
				if (brid.getJustification() == null
						|| brid.getJustification().isEmpty()) {
					res = this.createEditModelAndView(brid, "brid.commit.error.description");
				} else {
					brid.setAdministrator(admin);
					this.bridService.save(brid);
					res = new ModelAndView("redirect:../../");
				}
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(brid, "brid.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	private ModelAndView createEditModelAndView(final Brid brid) {
		ModelAndView result;

		result = this.createEditModelAndView(brid, null);

		return result;
	}

	private ModelAndView createEditModelAndView(final Brid brid,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("brid/administrator/edit");
		result.addObject("brid", brid);
		result.addObject("message", message);
		result.addObject("requestURI", "brid/administrator/edit.do");

		return result;
	}
}
