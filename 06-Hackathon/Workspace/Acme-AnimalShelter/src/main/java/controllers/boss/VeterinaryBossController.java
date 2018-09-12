/*
 * VeterinaryController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.boss;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import services.VeterinaryService;
import controllers.AbstractController;
import domain.Folder;
import domain.Veterinary;
import forms.ActorForm;

@Controller
@RequestMapping("/veterinary/boss")
public class VeterinaryBossController extends AbstractController {

	// Services -------------------------------------------------------------
	
	@Autowired
	private VeterinaryService veterinaryService;
	
	@Autowired
	private ActorService actorService;
	

	@Autowired
	private FolderService folderService;
	
	
	// Constructors -----------------------------------------------------------

	public VeterinaryBossController() {
		super();
	}

	// Registering ----------------------------------------------------------

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView res;
		
		ActorForm clientForm = new ActorForm();
		
		res = this.createEditModelAndView(clientForm);

		return res;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm veterinaryForm, final BindingResult binding) {
		ModelAndView res;
		Veterinary veterinary;
		boolean validPhone = this.actorService.validPhoneNumber(veterinaryForm.getPhoneNumber());

		if (binding.hasErrors())
			res = this.createEditModelAndView(veterinaryForm, "actor.params.error");
		else if (!veterinaryForm.getRepeatPassword().equals(veterinaryForm.getPassword()))
			res = this.createEditModelAndView(veterinaryForm, "actor.commit.errorPassword");
		else if (veterinaryForm.getTermsAndConditions() == false) 
			res = this.createEditModelAndView(veterinaryForm, "actor.params.errorTerms");
		else if (!validPhone && (veterinaryForm.getAceptPhoneNumberConditions() == null || veterinaryForm.getAceptPhoneNumberConditions() == false)) {
			veterinaryForm.setAceptPhoneNumberConditions(false);
			res = this.createEditModelAndView(veterinaryForm, "actor.params.mustAcceptPhoneNumber");
		} else
			try {
				veterinary = veterinaryService.reconstruct(veterinaryForm, binding);
				this.veterinaryService.save(veterinary);
				
				
				Collection<Folder> folders = new ArrayList<Folder>();
				Folder inBox = this.folderService.create();
				Folder outBox = this.folderService.create();
				Folder trash = this.folderService.create();
				inBox.setName("In Box");
				outBox.setName("Out Box");
				trash.setName("Trash Box");
				inBox.setActor(veterinary);
				outBox.setActor(veterinary);
				trash.setActor(veterinary);
				
				folders.add(inBox);
				folders.add(outBox);
				folders.add(trash);
				veterinary.setFolders(folders);
				res = new ModelAndView("redirect:../../");
			} catch (final Throwable oops) {
				res = this.createEditModelAndView(veterinaryForm, "actor.commit.error");
			}

		return res;
	}
	
	protected ModelAndView createEditModelAndView(final ActorForm veterinaryForm) {
		ModelAndView result;

		result = this.createEditModelAndView(veterinaryForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm veterinaryForm,
			final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/register");
		result.addObject("actorForm", veterinaryForm);
		result.addObject("message", message);
		result.addObject("requestURI","veterinary/boss/register.do");

		return result;
	}

}
