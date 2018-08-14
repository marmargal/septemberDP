package controllers.handyworker;

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

import services.HandyworkerService;
import services.RequestService;
import controllers.AbstractController;
import domain.Handyworker;
import domain.Request;
import forms.RequestForm;

@Controller
@RequestMapping("/request/handyworker")
public class RequestHandyworkerController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private RequestService requestService;

	@Autowired
	private HandyworkerService handyworkerService;

	// Constructor

	public RequestHandyworkerController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> lista = new ArrayList<>();
		Collection<Request> requests = new ArrayList<>();

		Handyworker handyworker = handyworkerService.findByPrincipal();

		lista = handyworker.getRequests();
		for (Request r : lista) {
			if (r.getFinishMoment() != null) {
				requests.add(r);
			}
		}

		result = new ModelAndView("request/list");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/handyworker/list.do");
		return result;
	}

	@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "assign")
	public ModelAndView save(@RequestParam int requestId) {
		ModelAndView res;
		try {
			Request request = this.requestService.findOne(requestId);
			Handyworker handyworker = this.handyworkerService.findByPrincipal();
			if (request.equals(null) || request.getHandyworker() != null) {
				res = new ModelAndView("redirect:/request/handyworker/list.do");
			} else {
				request.setHandyworker(handyworker);
				handyworker.getRequests().add(request);
				requestService.save(request);
				handyworkerService.save(handyworker);
				res = new ModelAndView("redirect:/request/handyworker/listWithoutServiced.do");
			}

		} catch (Exception e) {
			res = new ModelAndView("redirect:/request/handyworker/list.do");

		}
		return res;
	}

	@RequestMapping(value = "/listWithoutServiced", method = RequestMethod.GET)
	public ModelAndView listWithoutServiced() {
		ModelAndView result;
		Collection<Request> lista = new ArrayList<>();
		Collection<Request> requests = new ArrayList<>();

		Handyworker handyworker = handyworkerService.findByPrincipal();

		lista = handyworker.getRequests();

		for (Request r : lista) {
			if (r.getFinishMoment() == null) {
				requests.add(r);
			}
		}

		result = new ModelAndView("request/listWithoutServiced");
		result.addObject("requests", requests);
		result.addObject("requestURI",
				"request/handyworker/listWithoutServiced.do");
		return result;
	}

	@RequestMapping(value = "/listUnassigned", method = RequestMethod.GET)
	public ModelAndView listUnassigned() {
		ModelAndView result;
		Collection<Request> requests = new ArrayList<>();

		requests = this.requestService.requestUnassigned();
		result = new ModelAndView("request/listUnassigned");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/handyworker/listUnassigned.do");
		return result;
	}

	// Edit ----------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int requestId) {

		ModelAndView res;
		Request request;
		RequestForm requestForm;

		Handyworker handyworkerPrincipal = this.handyworkerService
				.findByPrincipal();

		request = this.requestService.findOne(requestId);
		requestForm = this.requestService.construct(request);

		if (handyworkerPrincipal.equals(request.getHandyworker()))
			res = this.createEditModelAndView(requestForm);
		else
			res = new ModelAndView("redirect:/tutorial/list.do");

		return res;
	}

	// Save ------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final RequestForm requestForm,
			final BindingResult binding) {
		ModelAndView res;
		System.out.println(binding.getFieldError());
		if (binding.hasErrors())
			res = this.createEditModelAndView(requestForm,
					"request.params.error");
		else {
			try {
				Request request = this.requestService.reconstruct(
						requestForm, binding);
				this.requestService.save(request);

				res = new ModelAndView("redirect:/request/handyworker/list.do");
			} catch (final Throwable oops) {
				System.out.println(oops.getMessage());
				res = this.createEditModelAndView(requestForm,
						"request.commit.error");
			}
		}
		return res;
	}

	// Ancillary methods -----------------------------
	private ModelAndView createEditModelAndView(final RequestForm requestForm) {
		ModelAndView res;

		res = this.createEditModelAndView(requestForm, null);

		return res;
	}

	private ModelAndView createEditModelAndView(final RequestForm requestForm,
			final String message) {

		ModelAndView res = new ModelAndView("request/edit");
		res.addObject("requestForm", requestForm);
		res.addObject("message", message);
		res.addObject("requestURI", "request/handyworker/edit.do");

		return res;
	}
}
