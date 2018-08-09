package controllers.handyworker;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HandyworkerService;
import controllers.AbstractController;
import domain.Handyworker;
import domain.Request;

@Controller
@RequestMapping("/request/handyworker")
public class RequestHandyworkerController extends AbstractController {

	// Services -------------------------------------------------------------

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
		System.out.println(lista);
		System.out.println(requests);

		result = new ModelAndView("request/listWithoutServiced");
		result.addObject("requests", requests);
		result.addObject("requestURI",
				"request/handyworker/listWithoutServiced.do");
		return result;
	}
}
