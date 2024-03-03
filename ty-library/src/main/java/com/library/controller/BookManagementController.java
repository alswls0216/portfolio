package com.library.controller;

/** 
 * 도서 대여, 연장, 양도, 반납 기능 controller
 * 
 *  @author 김민진
 *  
 */

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.library.dto.BookInfoDTO;
import com.library.dto.BooksDTO;
import com.library.dto.EmployeeDTO;
import com.library.service.bookmanagementService;

@SessionAttributes
@Controller
public class BookManagementController {

	// ******************************************** 변수 ******************************************** //
	@Autowired
	private bookmanagementService libService;

	// 대여 상태
	String can_rent = "A";
	String cannot_rent = "R";

	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	// ******************************************** qr 이동 ******************************************** //
	@GetMapping("/tylibrary/books/{b_id}")
	public String moveToBookInfo(@PathVariable("b_id") int b_id, HttpSession session, HttpServletRequest request, Model model) {
		
		// 로그인이 되어 있는 경우 : 사원 정보 저장
		EmployeeDTO session_employee = (EmployeeDTO) session.getAttribute("employee");
		if(session_employee!=null) {
			model.addAttribute("employee", session_employee);
		}

		// 도서 아이디 비교해서 데이터 가져오기
		BookInfoDTO bookInfo = libService.selectBookInfo(b_id);
		BooksDTO books = libService.selectBooks(b_id);

		if (books.getB_status().equals(can_rent))
			model.addAttribute("rent_availability", can_rent);
		else if (books.getB_status().equals(cannot_rent))
			model.addAttribute("rent_availability", cannot_rent);

		// 대여 페이지로 보낼 정보 저장
		session = request.getSession(false);
		model.addAttribute("bookInfo", bookInfo);

		// 세션에 도서 아이디 저장
		session.setAttribute("b_id", b_id);

		return "bookmanagement/books";
	}
	
	// ******************************************** 로그인 ******************************************** //
	@RequestMapping(value = "/tylibrary/login")
	public String login(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("management_type", "login");
		return "bookmanagement/enter_empl_num";
	}
	
	@RequestMapping(value = "/tylibrary/loginProcess")
	@ResponseBody
	public String loginProcess(@RequestParam(value = "arr[]") String[] arr, HttpSession session, HttpServletRequest request, Model model) {

		// 사번 비교 및 사원 정보 가져오기
		EmployeeDTO employee = libService.checkEmplInfo(arr[0], arr[1]);
		if(employee==null) {
			return "wrongAccess";
		} else if (employee.getE_id().equals("login_error_password")) { 
			return "비밀번호를 다시 입력해 주세요.";
		} else if(employee.getE_id().equals("login_error_eid")) {
			return "아이디를 다시 입력해 주세요.";
		} else {
			// 세션에 사원 정보 저장
			session.setAttribute("employee", employee);
			int b_id = (int) session.getAttribute("b_id"); // 도서 번호
			return "books/"+b_id;
		}
	}
	
	// ******************************************** 로그아웃 ******************************************** //
	@RequestMapping("/tylibrary/logout")
	public String logout(HttpSession session, HttpServletRequest request, Model model) {
		
		// 세션에서 사원 정보 삭제
		session.removeAttribute("employee");
		
		int b_id = (int) session.getAttribute("b_id"); // 도서 번호
		return "redirect:/tylibrary/books/"+b_id;
	}
	
	
	// ******************************************** 로그인 페이지 이동 ******************************************** //
	// ============ &&& 대여 : 사번 입력 페이지 이동 &&& ============
	@RequestMapping(value = "/tylibrary/rent/enter_empl_num")
	public String rent_login(HttpSession session, HttpServletRequest request, Model model) {

		int b_id = (int) session.getAttribute("b_id"); // 도서 번호
		BooksDTO book = libService.selectBooks(b_id);
		EmployeeDTO session_employee = (EmployeeDTO) session.getAttribute("employee");
		
		if(session_employee==null) {
			model.addAttribute("management_type", "rent");
			return "bookmanagement/enter_empl_num";
		} else {
			if(book.getB_status().equals(cannot_rent)) {
				model.addAttribute("error_type", "cant_rent");
				return "bookmanagement/wrongAccess";
			} else {
				model.addAttribute("management_type", "rent");
				return "bookmanagement/selectDate_jquery";
			}
		}
	}

	// ============ &&& 연장 : 사번 입력 페이지 이동 &&& ============
	@GetMapping("/tylibrary/renew/enter_empl_num")
	public String renew_login(HttpSession session, HttpServletRequest request, Model model) {

		EmployeeDTO session_employee = (EmployeeDTO) session.getAttribute("employee");
		int b_id = (int) session.getAttribute("b_id"); // 도서 번호
		String recent_return_date = libService.getRecentReturnDate(b_id);	// 이전에 선택한 대여 기간
	
		if(session_employee==null) {
			model.addAttribute("management_type", "renew");
			return "bookmanagement/enter_empl_num";
		} else {
			EmployeeDTO employee = libService.checkEmplInfoByBid(b_id, session_employee.getE_id(), session_employee.getE_password(), "renew");
			if(employee==null) {
				model.addAttribute("error_type", "cant_renew");
				return "bookmanagement/wrongAccess";
			} else {
				model.addAttribute("management_type", "renew");
				model.addAttribute("recent_return_date", recent_return_date);
				return "bookmanagement/selectDate_jquery_renew";
			}	
		}
	}

	// ============ &&& 양도 : 사번 입력 페이지 이동 &&& ============
	@GetMapping("/tylibrary/assign/enter_empl_num")
	public String assign_login(HttpSession session, HttpServletRequest request, Model model) {

		int b_id = (int) session.getAttribute("b_id"); // 도서 번호
		EmployeeDTO session_employee = (EmployeeDTO) session.getAttribute("employee");
		
		if(session_employee==null) {
			model.addAttribute("management_type", "assign");
			return "bookmanagement/enter_empl_num";
		} else {
			EmployeeDTO employee = libService.checkEmplInfoByBid(b_id, session_employee.getE_id(), session_employee.getE_password(), "assign");
			if(employee==null) {
				model.addAttribute("error_type", "cant_assign");
				return "bookmanagement/wrongAccess";
			} else {
				model.addAttribute("management_type", "assign");
				return "bookmanagement/selectDate_jquery";
			}	
		}
	}

	// ******************************************** 대여 ********************************************* //
	// ============ &&& 대여 : 사번 입력 &&& ============
	@RequestMapping(value = "/tylibrary/rent/loginProcess")
	@ResponseBody
	public String rent_loginProcess(@RequestParam(value = "arr[]") String[] arr,
									HttpSession session, HttpServletRequest request, Model model) {

		// 사번 비교 및 사원 정보 가져오기
		EmployeeDTO employee = libService.checkEmplInfo(arr[0], arr[1]);
		if(employee==null) {
			return "wrongAccess";
		} else if (employee.getE_id().equals("login_error_password")) { 
			return "비밀번호를 다시 입력해 주세요.";
		} else if(employee.getE_id().equals("login_error_eid")) {
			return "아이디를 다시 입력해 주세요.";
		} else {
			// 세션에 사원 정보 저장
			session.setAttribute("employee", employee);
			return "selectDate";
		}
	}
	
	@RequestMapping(value = "/tylibrary/rent/selectDate")
	public String rent_selectDate(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("management_type", "rent");
		return "bookmanagement/selectDate_jquery";
	}

	// ============ &&& 대여 기간 선택 &&& ============
	@RequestMapping(value = "/tylibrary/rent/due")
	@ResponseBody
	public String rent_due(@RequestParam(value = "arr[]") String[] pickedDate, HttpSession session,
			HttpServletRequest request,
			Model model) {

		// 대여 정보 확인 페이지에 표시될 내용
		String rent_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//String due_date = pickedDate[0] + "-" + pickedDate[1] + "-" + pickedDate[2];
		String due_date = pickedDate[0].replace("/", "-");

		// 세션에 정보 저장
		session.setAttribute("rent_date", rent_date);
		session.setAttribute("due_date", due_date);
		return "bookmanagement/check";
	}

	// ============ &&& 대여 : 확인 페이지 &&& ============
	@RequestMapping("/tylibrary/rent/check")
	public String rent_check(HttpSession session, HttpServletRequest request, Model model) {
		
		BookInfoDTO bookInfo = libService.selectBookInfo((int) session.getAttribute("b_id"));
		model.addAttribute("bookInfo", bookInfo);
		model.addAttribute("management_type", "rent");
		
		return "bookmanagement/check";
	}

	// ============ &&& 대여 : 진행 &&& ============
	@RequestMapping("/tylibrary/rent")
	public String rent(HttpSession session, HttpServletRequest request, Model model) {

		String returnType = null;
		int b_id = (int) session.getAttribute("b_id");	// 도서 번호

		if (libService.selectBooks(b_id).getB_status().equals(can_rent)) {
			// 사원 정보
			EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");
			BookInfoDTO bookInfo = libService.selectBookInfo(b_id);
			
			libService.updateBooks(b_id, (String) session.getAttribute("due_date"));	// books, checkout DB에 정보 저장
			libService.insertCheckout(b_id, employee.getE_id());
			
			model.addAttribute("bookInfo", bookInfo);
			model.addAttribute("management_type", "rent");
			returnType = "bookmanagement/confirm";
		} else if (libService.selectBooks(b_id).getB_status().equals(cannot_rent)) {
			// 잘못 접근한 경우
			model.addAttribute("error_type", "cant_rent");
			returnType = "bookmanagement/wrongAccess";
		}
		return returnType;
	}

	// ******************************************** 연장 ******************************************** //
	// ============ &&& 연장 : 사번 입력 &&& ============
	@RequestMapping(value = "/tylibrary/renew/loginProcess")
	@ResponseBody
	public String renew_loginProcess(@RequestParam(value = "arr[]") String[] arr, HttpSession session,
			HttpServletRequest request, Model model) {

		int b_id = (int) session.getAttribute("b_id"); // 도서 번호
		String recent_return_date = libService.getRecentReturnDate(b_id);	// 이전에 선택한 대여 기간
		EmployeeDTO employee = libService.checkEmplInfoByBid(b_id, arr[0], arr[1], "renew"); // 연장하려는 도서의 대여자 정보 가져오기

		if(employee==null) {
			return "wrongAccess";
		} else if (employee.getE_id().equals("login_error_password")) {
			return "비밀번호를 다시 입력해 주세요.";
		} else if (employee.getE_id().equals("login_error_eid")) {
			return "아이디를 다시 입력해 주세요.";
		} else {
			// 세션에 사원 정보 저장
			session.setAttribute("employee", employee);
			model.addAttribute("recent_return_date", recent_return_date);
			return "selectDate";
		}
	}

	@RequestMapping(value = "/tylibrary/renew/selectDate")
	public String renew_selectDate(HttpSession session, HttpServletRequest request, Model model) {
		int b_id = (int) session.getAttribute("b_id"); // 도서 번호
		String recent_return_date = libService.getRecentReturnDate(b_id); // 이전에 선택한 대여 기간

		model.addAttribute("management_type", "renew");
		model.addAttribute("recent_return_date", recent_return_date);
		return "bookmanagement/selectDate_jquery_renew";
	}

	// ============ &&& 연장 : 기간 선택 &&& ============
	@RequestMapping(value = "/tylibrary/renew/due")
	@ResponseBody
	public String renew_due(@RequestParam(value = "arr[]") String[] pickedDate, HttpSession session,
			HttpServletRequest request, Model model) {

		// 연장 정보 확인 페이지에 표시될 내용
		String rent_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//String due_date = pickedDate[0] + "-" + pickedDate[1] + "-" + pickedDate[2];
		String due_date = pickedDate[0].replace("/", "-");

		// 세션에 정보 저장
		session.setAttribute("rent_date", rent_date);
		session.setAttribute("due_date", due_date);

		return "bookmanagement/check";
	}

	// ============ &&& 연장 : 확인 페이지 &&& ============
	@RequestMapping("/tylibrary/renew/check")
	public String renew_check(HttpSession session, Model model) {

		BookInfoDTO bookInfo = libService.selectBookInfo((int) session.getAttribute("b_id"));
		model.addAttribute("bookInfo", bookInfo);
		model.addAttribute("management_type", "renew");
		
		return "bookmanagement/check";
	}

	// ============ &&& 연장 : 진행 &&& ============
	@RequestMapping("/tylibrary/renew")
	public String renew(HttpSession session, HttpServletRequest request, Model model) {

		String returnType = null;
		if (session.getAttribute("due_date") == null) {
			model.addAttribute("error_type", "error_renew");
			model.addAttribute("b_id", (int) session.getAttribute("b_id"));
			return "bookmanagement/wrongAccess";
		} else {
			int b_id = (int) session.getAttribute("b_id");
			String due_date = (String) session.getAttribute("due_date");
			BookInfoDTO bookInfo = libService.selectBookInfo(b_id);
			
			model.addAttribute("bookInfo", bookInfo);
			model.addAttribute("management_type", "renew");
			model.addAttribute("due_date", due_date);
			model.addAttribute("rent_date", (String) session.getAttribute("rent_date"));

			libService.updateBooks(b_id, due_date); // books 테이블에 대여 정보 기록

			returnType = "bookmanagement/confirm";
			
			// 세션에서 대여 기간 정보 삭제
			session.removeAttribute("due_date");
			session.removeAttribute("rent_date");
		}
		return returnType;
	}

	// ******************************************** 양도 ******************************************** //
	// ============ &&& 양도 : 사번 입력 &&& ============
	@RequestMapping(value = "/tylibrary/assign/loginProcess")
	@ResponseBody
	public String assign_loginProces(@RequestParam(value = "arr[]") String[] arr, HttpSession session,
			HttpServletRequest request, Model model) {

		int b_id = (int) session.getAttribute("b_id"); // 도서 번호
		EmployeeDTO employee = libService.checkEmplInfoByBid(b_id, arr[0], arr[1], "assign"); // 연장하려는 도서의 대여자 정보 가져오기

		if(employee == null) {
			return "wrongAccess";
		} else if (employee.getE_id().equals("login_error_password")) {
			return "비밀번호를 다시 입력해 주세요.";
		} else if (employee.getE_id().equals("login_error_eid")) {
			return "아이디를 다시 입력해 주세요.";
		} else {
			// 세션에 사원 정보 저장
			session.setAttribute("employee", employee);
			return "selectDate";
		}
	}

	@RequestMapping(value = "/tylibrary/assign/selectDate")
	public String assign_selectDate(HttpSession session, HttpServletRequest request, Model model) {
		model.addAttribute("management_type", "assign");
		return "bookmanagement/selectDate_jquery";
	}
	

	// ============ &&& 양도 : 대여 기간 선택 &&& ============
	@RequestMapping(value = "/tylibrary/assign/due")
	@ResponseBody
	public String assign_due(@RequestParam(value = "arr[]") String[] pickedDate, HttpSession session,
			HttpServletRequest request, Model model) {

		// 대여 정보 확인 페이지에 표시될 내용
		String rent_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		//String due_date = pickedDate[0] + "-" + pickedDate[1] + "-" + pickedDate[2];
		String due_date = pickedDate[0].replace("/", "-");

		// 세션에 정보 저장
		session.setAttribute("rent_date", rent_date);
		session.setAttribute("due_date", due_date);

		return "bookmanagement/check";
	}

	// ============ &&& 양도 : 확인 페이지 &&& ============
	@RequestMapping("/tylibrary/assign/check")
	public String assign_check(HttpSession session, Model model) {

		BookInfoDTO bookInfo = libService.selectBookInfo((int) session.getAttribute("b_id"));
		model.addAttribute("bookInfo", bookInfo);
		model.addAttribute("management_type", "assign");
		return "bookmanagement/check";
	}

	// ============ &&& 양도 : 진행 &&& ============
	@RequestMapping("/tylibrary/assign")
	public String assign(HttpSession session, HttpServletRequest request, Model model) {

		String returnType = null;
		if (session.getAttribute("due_date") == null) {
			model.addAttribute("error_type", "error_assign");
			model.addAttribute("b_id", (int) session.getAttribute("b_id"));
			return "bookmanagement/wrongAccess";
		} else {
			int b_id = (int) session.getAttribute("b_id");
			EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");
			BookInfoDTO bookInfo = libService.selectBookInfo(b_id);
			
			model.addAttribute("bookInfo", bookInfo);
			model.addAttribute("management_type", "assign");
			model.addAttribute("due_date", (String) session.getAttribute("due_date"));
			model.addAttribute("rent_date", (String) session.getAttribute("rent_date"));

			libService.updateBooks(b_id, (String) session.getAttribute("due_date")); 	// books 테이블에 대여(양도) 정보 기록
			libService.updateCheckout(b_id, employee.getE_id()); 						// checkout 테이블에 기존 대여 정보 기록
			libService.insertCheckout(b_id, employee.getE_id()); 						// checkout 테이블에 도서 대여(양도) 현황 기록

			returnType = "bookmanagement/confirm";
			
			// 세션에서 대여 기간 정보 삭제
			session.removeAttribute("due_date");
			session.removeAttribute("rent_date");
		}
		return returnType;
	}

	// ******************************************** 반납 ******************************************** //

	@RequestMapping("/tylibrary/return/check")
	public String return_check(HttpSession session, HttpServletRequest request, Model model) {

		String rent_date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		BookInfoDTO bookInfo = libService.selectBookInfo((int) session.getAttribute("b_id"));

		if (session.getAttribute("adminId").equals("admin")) {
			model.addAttribute("rent_date", rent_date);
			model.addAttribute("bookInfo", bookInfo);
			model.addAttribute("management_type", "return");
			return "bookmanagement/check";
		} else if (session.getAttribute("adminId") == null) {
			return "bookmanagement/login";
		} else
			return "bookmanagement/wrongAccess";
	}

	@RequestMapping("/tylibrary/return")
	public String return_confirm(HttpSession session, HttpServletRequest request, Model model) {

		// 도서 번호
		int b_id = (int) session.getAttribute("b_id");
		// 사번
		String e_id = (String) session.getAttribute("e_id");

		libService.updateBooks(b_id, (String) session.getAttribute("rent_date")); // books 테이블에 대여 정보 기록
		libService.updateCheckout(b_id, e_id);

		model.addAttribute("management_type", "return");
		return "bookmanagement/confirm";
	}

	
	// ******************************************** 오류 페이지 ******************************************** //
	@RequestMapping("/tylibrary/assign/wrongAccess")
	public String assign_wrong_access(Model model) {
		model.addAttribute("error_type", "cant_assign");
		return "bookmanagement/wrongAccess";
	}
	
	@RequestMapping("/tylibrary/rent/wrongAccess")
	public String rent_wrong_access(Model model) {
		model.addAttribute("error_type", "cant_rent");
		return "bookmanagement/wrongAccess";
	}
	
	@RequestMapping("/tylibrary/renew/wrongAccess")
	public String renew_wrong_access(Model model) {
		model.addAttribute("error_type", "cant_renew");
		return "bookmanagement/wrongAccess";
	}

}
