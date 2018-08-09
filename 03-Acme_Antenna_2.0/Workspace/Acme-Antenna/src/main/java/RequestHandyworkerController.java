import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.HandyworkerService;
import services.RequestService;
import controllers.AbstractController;
import domain.Handyworker;
import domain.Request;

@Controller
@RequestMapping("/request/handyworker")
public class RequestHandyworkerController extends AbstractController{

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
}
