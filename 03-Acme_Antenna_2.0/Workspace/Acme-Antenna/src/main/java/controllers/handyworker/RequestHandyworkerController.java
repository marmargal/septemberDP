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
		Collection<Request> requests = new ArrayList<>();

		Handyworker handyworker = handyworkerService.findByPrincipal();

		requests = handyworker.getRequests();
		result = new ModelAndView("request/list");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/handyworker/list.do");
		return result;
	}

	@RequestMapping(value = "/listUnassigned", method = RequestMethod.GET)
	public ModelAndView listUnassigned() {
		ModelAndView result;
		Collection<Request> requests = new ArrayList<>();

		

		requests = this.requestService.requestUnassigned();
		result = new ModelAndView("request/list");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/handyworker/list.do");
		return result;
	}
	@RequestMapping(value = "/assign", method = RequestMethod.POST, params = "assign")
	public ModelAndView save(@RequestParam int requestId) {
		ModelAndView res;
		try {
			System.out.println("llega");
			Request request = this.requestService.findOne(requestId);
			Handyworker handyworker = this.handyworkerService.findByPrincipal();
			if (request.equals(null) || request.getHandyworker() != null) {
				System.out.println("falla");
				res = new ModelAndView("redirect:/request/handyworker/list.do");
			} else {
				System.out.println("entra");
				request.setHandyworker(handyworker);
				handyworker.getRequests().add(request);
				requestService.save(request);
				handyworkerService.save(handyworker);
				res = new ModelAndView("redirect:/request/handyworker/list.do");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			res = new ModelAndView("redirect:/request/handyworker/list.do");

		}
		return res;
	}

}
