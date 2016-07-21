package com.vmezhevikin.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmezhevikin.blog.Constants;
import com.vmezhevikin.blog.entity.Article;
import com.vmezhevikin.blog.service.FindDataService;
import com.vmezhevikin.blog.service.StaticDataService;

@Controller
public class PublicDataController {

	@Autowired
	@SuppressWarnings(value = "unused")
	private StaticDataService staticDataService;

	@Autowired
	private FindDataService findDataService;

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String getNews(Model model,
			@PageableDefault(size = Constants.MAX_ARTICLES_PER_PAGE) @SortDefault(direction = Direction.DESC, sort = "date") Pageable pageable) {
		return viewArticles(model, null, "/news", pageable);
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String getCategory(Model model, @PathVariable("id") Short id,
			@PageableDefault(size = Constants.MAX_ARTICLES_PER_PAGE) @SortDefault(direction = Direction.DESC, sort = "date") Pageable pageable) {
		return viewArticles(model, id, "/category/" + id, pageable);
	}

	private String viewArticles(Model model, Short id, String url, Pageable pageable) {
		Page<Article> articles = null;
		if (id == null) {
			articles = findDataService.findAllArticles(pageable);
		} else {
			articles = findDataService.findAllArticlesByCategoryId(id, pageable);
			model.addAttribute("currentCategory", findDataService.findCategoryById(id));
		}
		model.addAttribute("articles", articles.getContent());
		model.addAttribute("page", articles);
		model.addAttribute("categories", findDataService.findAllCategoriesWithStatistic());
		model.addAttribute("url", url);
		return "category";
	}

	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public String getArticle(Model model, @PathVariable("id") Long id) {
		model.addAttribute("categories", findDataService.findAllCategoriesWithStatistic());
		model.addAttribute("article", findDataService.findArticleById(id));
		model.addAttribute("comments", findDataService.findAllCommentsByArticleId(id));
		return "article";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String getErroe() {
		return "error";
	}

	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String getAbout() {
		return "about";
	}
}