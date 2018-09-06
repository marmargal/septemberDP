package controllers.employee;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.NoticeService;
import controllers.AbstractController;
import domain.Notice;

@Controller
@RequestMapping("/notice/employee")
public class NoticeEmployeeController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private NoticeService noticeService;
	
	// Constructors -----------------------------------------------------------

	public NoticeEmployeeController() {
		super();
	}

	// List ---------------------------------------------------------------		

	@RequestMapping("/list")
	public ModelAndView listNonDescarted() {
		ModelAndView result;

		Collection<Notice> notices = new ArrayList<Notice>(); 
		notices = this.noticeService.findNonDiscarded();
					
		result = new ModelAndView("notice/list");
		result.addObject("notices", notices);

		return result;
	}
	
	@RequestMapping(value="/discardNotice", method=RequestMethod.POST)
	public ModelAndView discard(@RequestParam(defaultValue = "0") int noticeId){
		ModelAndView res;
		
		try{
			Notice notice = this.noticeService.discardedTrue(noticeId);
			this.noticeService.save(notice);
			res = new ModelAndView("redirect:/notice/employee/list.do");
		}catch (Exception e) {
			res = new ModelAndView("redirect:../../");
		}
		return res;
	}
	
	@RequestMapping("/display")
	public ModelAndView display(@RequestParam(defaultValue = "0") int noticeId) {
		ModelAndView result;

		Notice notice = new Notice(); 
		notice = this.noticeService.findOne(noticeId);
		
		result = new ModelAndView("notice/display");
		result.addObject("notice", notice);

		return result;
	}

}
