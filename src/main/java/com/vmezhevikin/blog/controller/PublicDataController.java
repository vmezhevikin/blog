package com.vmezhevikin.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmezhevikin.blog.service.FindDataService;
import com.vmezhevikin.blog.service.StaticDataService;

@Controller
public class PublicDataController {
	
	@Autowired
	private StaticDataService staticDataService;
	
	@Autowired
	private FindDataService findDataService;
	
	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String getNews(Model model) {
		model.addAttribute("categories", staticDataService.findCategoryNames());
		model.addAttribute("count", findDataService.findCategoryStatistic());
		model.addAttribute("authors", findDataService.findAllAuthors());
		model.addAttribute("articles", findDataService.findAllArticles());
		return "news";
	}
}