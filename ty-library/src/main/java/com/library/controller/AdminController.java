package com.library.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BooksDTO;
import com.library.dto.EmployeeDTO;
import com.library.dto.SearchRentRecordDto;
import com.library.dto.StockBookDTO;
import com.library.service.StockService;

@Controller
@RequestMapping("tylibrary")
public class AdminController {

	String admin[] = { "admin", "librarian" };

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	int stockState = 1; // 스톡 상태 : 0 잘못된 접근 1 기본상태 2 조사버튼 클릭 3 카메라확인 (여기서 디비 초기화) 1 종료 후 기본상태로

	@Autowired
	private StockService stockService;

	@RequestMapping("")
	public String userLogin(HttpSession session) {
		if (session.getAttribute("librarian") != null) {// 도서관 기기는 도서관 페이지로 납치
			return session.getAttribute("librarian").equals(admin[1]) ? "redirect:/tylibrary/admin" : "redirect:";
		} else if (session.getAttribute("employee") != null) {// 일반 사원은 사원 페이지로
			return "redirect:/tylibrary/home";
		}
		return "/admin/user-login";
	}

	// 사원 로그인
	@RequestMapping("/home")
	public String login(HttpSession session) {
		if (session.getAttribute("employee") != null) { // 이미 로그인 상태면 홈으로
			return "/admin/user-home";
		}
		return "redirect:"; // 아니라면 로그인 페이지로
	}

	// 사원 비밀번호 리셋
	@RequestMapping("passwordReset")
	public String reset(HttpSession session) {

		return "admin/user-passreset";
	}

	// admin login ====================
	@RequestMapping("/admin")
	public String index2(HttpSession session, @RequestParam(required = false, defaultValue = "login") String adminId,
			Model model) {
		String sessionInfo = stockService.checkSession(session, adminId, admin); // 세션에 담긴 정보를 확인합니다.
		if (sessionInfo.equals(admin[0])) { // 정보가 어드민이면 어드민 화면으로
			return "admin/admin-home";
		} else if (sessionInfo.equals(admin[1])) { // 정보가 사서면 사서 화면으로
			return "admin/librarian-home";
		}
		return "admin/admin-login"; // 의미 없는 정보면 다시 로그인 화면으로
	}

	@RequestMapping("Elogout")
	public String useLlogout(HttpSession session) {
		session.setAttribute("employee", null);
		return "redirect:/tylibrary";
	}

	@RequestMapping("admin/logout")
	public String logOut(HttpSession session) {
		session.setAttribute(admin[0], null);
		session.setAttribute(admin[1], null);
		return "redirect:/tylibrary/admin";
	}

	// 사원 등록 컨트롤러
	@RequestMapping("admin/user")
	public String user(HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/tylibrary/admin";
		}
		return "admin/user";
	}

	// 반납과 대여 컨트롤러
	@RequestMapping("librarian")
	public String notyet(String state, Model model, HttpSession session) {
		if (session.getAttribute("librarian") == null) {
			return "redirect:/tylibrary/admin";
		}
		model.addAttribute("state", state);
		return "admin/librarian-scan";
	}

	@RequestMapping("employee")
	public String foryou(String state, Model model, HttpSession session) {

		model.addAttribute("state", state);
		return "admin/user-scan";
	}

	// 의미 없는 공간
	@RequestMapping("isbn")
	public String isbn() {
		return "isbn";
	}

	@RequestMapping("qrgen")
	public String qrgen() {
		return "qrgen";
	}

	// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

	@RequestMapping("admin/stock-count")
	public String stockCount(@RequestParam(required = false) String stock, Model model, String state,
			HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/tylibrary/admin";
		}

		if (stock == null) {
			// 체크안됨
			model.addAttribute("book_one", stockService.selectBooksByNStockNBook());
			// 체크됨
			model.addAttribute("book_two", stockService.selectBooksByYStockNBook());
			// 대여 책 표시
			// model.addAttribute("book_three",stockService.selectBooksByYState());
			// 조사완료 버튼
			model.addAttribute("state", state == null ? 0 : stockState);
			return "admin/stock-count-list";
		} else if (stock.equals("start")) {
			if (stockState == 1)
				stockState = 2;
			model.addAttribute("state", stockState);
			return "admin/stock-count-scan";
		} else if (stock.equals("finish")) {
			model.addAttribute("book_one", stockService.selectBooksByNStockNBook());
			// 체크됨
			model.addAttribute("book_two", stockService.selectBooksByYStockNBook());
			// 대여 책 표시
			// model.addAttribute("book_three",stockService.selectBooksByYState());
			// 조사완료 버튼
			stockState = 1;
			model.addAttribute("state", stockState);
			return "admin/stock-count-list";
		}
		return "";
	}

	@RequestMapping("admin/rent-record")
	public String getRentRecord(HttpSession session) {
		if (session.getAttribute("admin") == null) {
			return "redirect:/tylibrary/admin";
		}
		return "rent-record";
	}

	@PostMapping("select-rent-record-by-date-range")
	@ResponseBody
	public List<SearchRentRecordDto> getRentRecords(@RequestParam("startDate") String startDate,
			@RequestParam("endDate") String endDate) {
		List<SearchRentRecordDto> result = stockService.selectRentRecordsByDateRange(startDate, endDate);

		return result;
	}

	// ajax 모음-------------------------------
	// 사원 조회
	@RequestMapping("admin/search-employees")
	@ResponseBody
	public List<EmployeeDTO> infoUser(String category, String searchKey) {
		return stockService.searchEmployee(category, searchKey);
	}

	// 사원 비밀번호 초기화
	@RequestMapping("admin/reset-password")
	@ResponseBody
	public int passwordReset(String id, HttpSession session) {
		if (session.getAttribute("admin") != null)
			return stockService.updatePassword(id);
		return 0;
	}

	// 사원 비밀번호 변경
	@RequestMapping("passwordChange")
	@ResponseBody
	public int passwordChange(String password, HttpSession session) {
		return 0;
	}

	// 사원등록이다
	@RequestMapping("admin/user-sign-up")
	@ResponseBody
	public List<EmployeeDTO> isSingUP(@RequestParam(required = false) String ENum,
			@RequestParam(required = false) String EName, @RequestParam(required = false) MultipartFile EFile,
			HttpSession session) {
		if (session.getAttribute("admin") != null)
			return stockService.goSingup(ENum, EName, EFile);
		return null;
	}

	// 카메라 확인 후 n초기화
	@RequestMapping("/stock-camera-ok")
	@ResponseBody
	public int isCamera(@RequestParam String cameraState, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			stockState = Integer.parseInt(cameraState);
			stockService.updateInitialNStock();
			return 3;
		}
		return 1;
	}

	// 스캔 완료시 y로 변경 후 리턴
	@RequestMapping("/stock-is-exist")
	@ResponseBody
	public StockBookDTO isExist(@RequestParam int id, HttpSession session) {
		if (session.getAttribute("admin") != null) {
			if (1 == stockService.updateYStockByBId(id)) {
				return stockService.selectBooksByBId(id);
			}
		}
		return null;
	}

	// 스캔하기 클릭시 스테이트 체크용
	@RequestMapping("/check-state")
	@ResponseBody
	public int determin() {
		return stockState;
	}

	// 반납 대여 관련 ajax----------------------------------
	@RequestMapping("/check-bookInfo")
	@ResponseBody
	public StockBookDTO doSometing(@RequestParam int id, @RequestParam String state) {
		if (state.equals("return")) {
			if (stockService.selectBooksByRstaus(id) == 1) { // 추가 checkout 테이블 변경
				return stockService.returnMethod(id);
			}
		} else if (state.equals("rent")) {
			return stockService.selectBooksByBId(id); // 여기는 jsp에서 연결하는 로직 추가
		}
		return null;
	}

	// 테스트용
	@RequestMapping("/admin/testx")
	public String asd() {
		return "/admin/testxcz";
	}

	@RequestMapping("/get-ids")
	@ResponseBody
	public List<String> getId() {
		return stockService.getIds();
	}

}
