package controllers.handyworker;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.HandyworkerService;
import services.RequestService;
import controllers.AbstractController;
import domain.Handyworker;
import domain.Request;

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
				res = new ModelAndView("redirect:/request/handyworker/list.do");
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
}
