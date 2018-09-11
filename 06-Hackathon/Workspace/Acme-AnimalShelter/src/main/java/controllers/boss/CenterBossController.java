/*
 * AdministratorController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.boss;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BossService;
import services.CenterService;
import services.WarehouseService;
import controllers.AbstractController;
import domain.Center;
import domain.Warehouse;

@Controller
@RequestMapping("/center/boss")
public class CenterBossController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private CenterService centerService;

	@Autowired
	private BossService bossService;

	@Autowired
	private WarehouseService warehouseService;

	// Constructors -----------------------------------------------------------

	public CenterBossController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Center center;

		center = this.centerService.create();
		Warehouse warehouse = warehouseService.create();
		center.setWarehouse(warehouse);
		res = this.createEditModelAndView(center);

		return res;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(
			@RequestParam(defaultValue = "0") final int centerId) {
		ModelAndView result;
		Center center;
		if (centerId == 0) {
			result = new ModelAndView("redirect:../../");

		} else if (this.centerService.findOne(centerId) == null) {
			result = new ModelAndView("redirect:../../");
		} else {
			center = this.centerService.findOne(centerId);
			result = this.createEditModelAndView(center);
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Center center, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors()) {
			res = this.createEditModelAndView(center, "center.params.error");
		} else
			try {
				Warehouse warehouse = this.warehouseService.save(center
						.getWarehouse());
				center.setBoss(bossService.findByPrincipal());
				center.setWarehouse(warehouseService.findOne(warehouse.getId()));
				this.centerService.save(center);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this
						.createEditModelAndView(center, "center.commit.error");
			}
		return res;
	}

	// Delete ---------------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Center center, final BindingResult binding) {
		ModelAndView res;
		try {
			this.centerService.delete(center);
			res = new ModelAndView("redirect:/center/list.do");

		} catch (Exception e) {
			res = this.createEditModelAndView(center, "center.commit.error");
		}

		return res;
	}
	

	protected ModelAndView createEditModelAndView(final Center center) {
		ModelAndView result;

		result = this.createEditModelAndView(center, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Center center,
			final String message) {
		ModelAndView result;
		result = new ModelAndView("center/boss/edit");
		result.addObject("center", center);

		result.addObject("message", message);
		result.addObject("requestURI", "center/boss/edit.do");
		return result;

	}

}
