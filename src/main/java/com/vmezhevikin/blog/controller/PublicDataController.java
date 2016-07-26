package com.vmezhevikin.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmezhevikin.blog.Constants;
import com.vmezhevikin.blog.entity.Article;
import com.vmezhevikin.blog.exception.CantCompleteClientRequestException;
import com.vmezhevikin.blog.service.EditDataService;
import com.vmezhevikin.blog.service.FindDataService;

@Controller
public class PublicDataController {

	@Autowired
	private FindDataService findDataService;
	
	@Autowired
	private EditDataService editDataService;

	@RequestMapping(value = "/news", method = RequestMethod.GET)
	public String getNews(Model model,
			@PageableDefault(size = Constants.MAX_ARTICLES_PER_PAGE) @SortDefault(direction = Direction.DESC, sort = "date") Pageable pageable) {
		
		Page<Article> articles = findDataService.findAllArticles(pageable);
		addCommonArticlesAttrToModel(model, articles, "/news");
		return "category";
	}

	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public String getCategory(Model model, @PathVariable("id") Short id,
			@PageableDefault(size = Constants.MAX_ARTICLES_PER_PAGE) @SortDefault(direction = Direction.DESC, sort = "date") Pageable pageable) {
		
		Page<Article> articles = findDataService.findArticlesForCategory(id, pageable);
		addCommonArticlesAttrToModel(model, articles, "/category/" + id);
		model.addAttribute("currentCategory", findDataService.findCategoryById(id));
		return "category";
	}

	private void addCommonArticlesAttrToModel(Model model, Page<Article> articles, String categoryUrl) {
		model.addAttribute("articles", articles.getContent());
		model.addAttribute("page", articles);
		model.addAttribute("categories", findDataService.findAllCategoriesWithStatistic());
		model.addAttribute("categoryUrl", categoryUrl);
	}

	@RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
	public String getArticle(Model model, @PathVariable("id") Long id) {
		if (findDataService.countArticlesById(id) == 0) {
			throw new CantCompleteClientRequestException("Can't find article with id " + id);
		}
		editDataService.incrementViewsForArticle(id);
		model.addAttribute("comments", findDataService.findFirstNCommentsForArticle(id, Constants.FIRST_COMMENTS_PER_PAGE));
		model.addAttribute("firstComments", Constants.FIRST_COMMENTS_PER_PAGE);
		model.addAttribute("totalComments", findDataService.countAllCommentsForArticle(id));
		model.addAttribute("categories", findDataService.findAllCategoriesWithStatistic());
		model.addAttribute("article", findDataService.findArticleById(id));
		return "article";
	}
	
	@RequestMapping(value = "/comment/all", method = RequestMethod.GET)
	public String getMoreComments(Model model, @ModelAttribute("articleId") Long id) {
		model.addAttribute("comments", findDataService.findAllCommentsForArticle(id));
		return "comment/all";
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