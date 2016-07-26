package com.vmezhevikin.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vmezhevikin.blog.entity.Comment;
import com.vmezhevikin.blog.form.CommentForm;
import com.vmezhevikin.blog.model.JsonResponse;
import com.vmezhevikin.blog.service.EditDataService;

@Controller
public class EditDataController {

	// @Autowired
	// private FindDataService findDataService;

	@Autowired
	private EditDataService editDataService;

	@RequestMapping(value = "/user/comment/add", method = RequestMethod.POST)
	public String postAddComment(@RequestBody CommentForm form, Model model) {
		Comment comment = editDataService.createNewComment(form);
		model.addAttribute("comment", comment);
		return "comment/add";
	}

	@RequestMapping(value = "/user/comment/del", method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse postDelComment(@RequestParam("commentId") Long commentId) {
		editDataService.removeComment(commentId);
		return new JsonResponse("OK");
	}
}