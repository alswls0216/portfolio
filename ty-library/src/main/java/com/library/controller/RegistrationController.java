package com.library.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.library.dto.BookDTO;
import com.library.dto.BookDeleteDto;
import com.library.dto.DeleteDto;
import com.library.dto.SearchQuery;
import com.library.dto.SearchRemoveDuplicateDto;
import com.library.dto.SearchResultDto;
import com.library.mapper.LibMapper;

@Controller
@RequestMapping("tylibrary/admin")
public class RegistrationController {

	private int id;
	@Value("${library.url}")
	private String url;

	BookDTO Books = new BookDTO(); // *** ArrayList 형태로 바꿔도 무방 ***

	@Autowired
	LibMapper libMapper;

	@RequestMapping("/register-book/done")
	public String test() {
		return "register-check";
	}

	@RequestMapping("/register-book")
	public String insertMain() {

		return "register";
	}

	// 책 등록

	@RequestMapping("/selectBookInfo")

	public String register(
			@RequestParam("isbn") String isbn, HttpSession session) {

		BookDTO book = libMapper.selectBookInfo(isbn);

		// pdf파일에서 받아오기 위함(register-check)
		/*
		 * session.setAttribute("isbn", book.getIsbn());
		 * session.setAttribute("title", book.getTitle());
		 * session.setAttribute("author", book.getAuthor());
		 * session.setAttribute("publisher", book.getPublisher());
		 */

		if (book == null) {

			return "forward:/tylibrary/admin/insertBookInfo";

		} else {
			return "forward:/tylibrary/admin/insertBooks";
		}

	}

	@RequestMapping("/insertBookInfo")
	public String insertBookInfo(HttpServletRequest httpServletRequest, Model model) {

		String isbn = httpServletRequest.getParameter("isbn");
		String title = httpServletRequest.getParameter("title");
		String author = httpServletRequest.getParameter("author");
		String publisher = httpServletRequest.getParameter("publisher");
		String category = httpServletRequest.getParameter("category");

		model.addAttribute("isbn", isbn);
		model.addAttribute("title", title);
		model.addAttribute("author", author);
		model.addAttribute("publisher", publisher);
		model.addAttribute("category", category);

		libMapper.insertBookInfo(new BookDTO(isbn, title, author, publisher, category));
		libMapper.insertBooks(new BookDTO(isbn));

		this.id = libMapper.getLastInsertId();

		this.url = httpServletRequest.getParameter("qrcode") + "/tylibrary/books/" + id;

		return "register-check";
	}

	@RequestMapping("/insertBooks")
	public String insertBooks(HttpServletRequest httpServletRequest, Model model) {

		String isbn = httpServletRequest.getParameter("isbn");
		model.addAttribute("isbn", isbn);
		libMapper.insertBooks(new BookDTO(isbn));

		this.id = libMapper.getLastInsertId();

		this.url = httpServletRequest.getParameter("qrcode") + "/tylibrary/books/" + id;

		return "register-check";
	}

	// ============ &&& isbn 받아오기 (JSON 버전) &&& ============
	@RequestMapping(value = "/searchIsbn")
	@ResponseBody
	public BookDTO searchIsbn_json(@RequestParam("name") String isbn) throws IOException {

		BookDTO book = new BookDTO();

		StringBuilder urlBuilder = new StringBuilder(
				"https://www.nl.go.kr/seoji/SearchApi.do?cert_key=461ef3112bc97ed275fbed101ea88fbabffae5fa94df34ee5a54dff44beb7c99"
						+ "&result_style=json&page_no=1&page_size=1&isbn=" + isbn); /* URL */
		URL url = new URL(urlBuilder.toString());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-type", "application/json");
		BufferedReader rd;

		// 서비스코드 정상이면 200~300사이의 숫자 나옴
		if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} else {
			rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
		}
		StringBuilder sb = new StringBuilder();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		rd.close();
		conn.disconnect();

		try {
			JSONObject result = (JSONObject) new JSONParser().parse(sb.toString());
			JSONArray bookdata = (JSONArray) result.get("docs");

			for (int i = 0; i < bookdata.size(); i++) {
				result = (JSONObject) bookdata.get(i);

				String title = (String) result.get("TITLE");
				String publisher = (String) result.get("PUBLISHER");
				String author = (String) result.get("AUTHOR");

				book = new BookDTO(isbn, title, author, publisher, "cat");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return book;
	}

	// =========== QR 생성 ============

	@GetMapping("/produceBookQR")
	public ResponseEntity<byte[]> produceBookQR() throws WriterException, IOException {
		// QR 정보

		// QR Code - Image 생성
		try {

			int width = 200;
			int height = 200;
			// books 테이블에 넣을 정보

			// String url =
			// "https://609b-118-32-180-157.ngrok-free.app/https://www.aladin.co.kr/home/welcome.aspx";

			// QR Code - BitMatrix: qr code 정보 생성
			BitMatrix encode = new MultiFormatWriter()
					.encode(url, BarcodeFormat.QR_CODE, width, height);

			// output Stream
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			// Bitmatrix, file.format, outputStream
			MatrixToImageWriter.writeToStream(encode, "PNG", out);

			return ResponseEntity.ok()
					.contentType(MediaType.IMAGE_PNG)
					.body(out.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@RequestMapping("/delete")
	public String delete() {

		return "delete";
	}

	@RequestMapping("/delete-check")
	public String deleteCheck(@RequestParam String id, String title, Model model) {
		model.addAttribute("id", id);
		model.addAttribute("title", title);

		return "deleteCheck";
	}

	/*
	 * @PostMapping("/bringBooksInfo")
	 * 
	 * @ResponseBody
	 * public List<BookDeleteDto> bringBooksInfo(
	 * 
	 * @RequestParam("isbn") String isbn, Model model) {
	 * 
	 * // LibMapper를 통해 책 정보를 가져오기
	 * List<BookDeleteDto> bookInfo = libMapper.bringBooksInfo(isbn);
	 * 
	 * // 모델에 책 정보 추가
	 * 
	 * return bookInfo;
	 * }
	 */
	@PostMapping("/deleteBook")
	public String deleteBook(
			@RequestParam("id") String id) {
		// 도서 삭제 로직을 수행

		libMapper.deleteBook(id);

		return "delete";
	}

	@PostMapping("/filteredToDelete")
	public String getSearchFiltered(@ModelAttribute SearchQuery query, Model m) throws Exception {

		List<DeleteDto> informations = libMapper.bringBooksInfo(query);
		m.addAttribute("informations", informations);
		m.addAttribute("query", query);

		return "delete-result";
	}

}
