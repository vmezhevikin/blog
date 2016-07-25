package com.vmezhevikin.blog.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmezhevikin.blog.entity.Author;
import com.vmezhevikin.blog.form.SignUpForm;
import com.vmezhevikin.blog.model.CurrentUser;
import com.vmezhevikin.blog.service.EditDataService;
import com.vmezhevikin.blog.service.FindDataService;
import com.vmezhevikin.blog.util.SecurityUtil;

@Controller
public class SignInSignOutController {

	@Autowired
	private FindDataService findDataService;
	
	@Autowired
	private EditDataService editDataService;

	@RequestMapping(value = "/sign-up", method = RequestMethod.GET)
	public String getSignUp(Model model) {
		model.addAttribute("signUpForm", new SignUpForm());
		return "sign-up";
	}

	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String postSignUp(@ModelAttribute("signUpForm") SignUpForm form, Model model) {
		Author author = editDataService.createNewAuthor(form);
		SecurityUtil.authentificate(author);
		return "redirect:/sign-up-success";
	}

	@RequestMapping(value = "/sign-up-success", method = RequestMethod.GET)
	public String getSignUpSuccess(Model model) {
		Author author = findDataService.findAuthorById(SecurityUtil.getCurrentUserId());
		if (author == null) {
			return "error";
		}
		model.addAttribute("author", author);
		return "sign-up-success";
	}

	@RequestMapping(value = "/sign-in", method = RequestMethod.GET)
	public String getSignIn() {
		CurrentUser currentUser = SecurityUtil.getCurrentUser();
		if (currentUser != null) {
			return "redirect:/user/my-articles";
		} else {
			return "sign-in";
		}
	}

	@RequestMapping(value = "/sign-in-failed", method = RequestMethod.GET)
	public String getSignInFailed(HttpSession session) {
		if (session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") == null) {
			return "redirect:/sign-in";
		} else {
			return "sign-in";
		}
	}
}