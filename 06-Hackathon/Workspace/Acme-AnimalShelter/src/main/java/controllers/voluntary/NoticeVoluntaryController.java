package controllers.voluntary;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.NoticeService;
import controllers.AbstractController;
import domain.Notice;

@Controller
@RequestMapping("/notice/voluntary")
public class NoticeVoluntaryController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private NoticeService noticeService;

	// Constructors -----------------------------------------------------------

	public NoticeVoluntaryController() {
		super();
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		Notice notice;

		notice = this.noticeService.create();
		res = this.createEditModelAndView(notice);

		return res;
	}

	// Saving --------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Notice notice, final BindingResult binding) {
		ModelAndView res;
		if (binding.hasErrors())
			res = this.createEditModelAndView(notice, "notice.params.error");
		else
			try {
				this.noticeService.save(notice);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(notice, "notice.commit.error");
			}
		return res;
	}

	// Ancillary methods --------------------------------------------------

	protected ModelAndView createEditModelAndView(final Notice notice) {
		ModelAndView result;

		result = this.createEditModelAndView(notice, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Notice notice,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("notice/voluntary/edit");
		result.addObject("notice", notice);
		result.addObject("message", message);
		result.addObject("requestURI", "notice/voluntary/edit.do");

		return result;
	}

}
