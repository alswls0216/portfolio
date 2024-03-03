package com.library.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.library.dto.*;
import com.library.service.SearchService;

/**
 * 도서 검색 화면을 위한 controller
 * 
 * search : 최초 접근 화면
 * search/filtered : 검색 결과 화면
 * searchbook : 대여 가능/불가 클릭 후 나오는 상세정보 화면
 * 
 * @author 김태형
 * 
 */
@Controller
@RequestMapping("tylibrary")
public class SearchController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private SearchService searchService;

	@RequestMapping("/search")
	public String getSearch() {
		return "search";
	}

	@PostMapping("/search/filtered")
	public String getSearchFiltered(@ModelAttribute SearchQuery query, Model m) throws Exception {

		List<SearchRemoveDuplicateDto> informations = searchService.removeDuplicates(searchService.getLists(query));
		m.addAttribute("informations", informations);
		m.addAttribute("query", query);

		return "search-result";
	}

	@GetMapping("/searchbook/{id}")
	public String getSearchBook(@PathVariable int id, Model m) {

		List<SearchRemoveDuplicateDto> information = searchService.removeDuplicates(searchService.getBook(id));

		m.addAttribute("information", information);

		return "search-book";
	}

	@GetMapping("/admin/overdue")
	public String getOverdue(Model m) {

		List<OverdueDto> informations = searchService.getOverdue();
		m.addAttribute("informations", informations);

		return "overdue";
	}

}
