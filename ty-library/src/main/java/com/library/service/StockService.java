package com.library.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BooksDTO;
import com.library.dto.EmployeeDTO;
import com.library.dto.SearchRentRecordDto;
import com.library.dto.StockBookDTO;

public interface StockService {
	//===쿼리문===============================================
	//update ================================
	public void updateInitialNStock();
	public int updateYStockByBId(int id);
	public int updateNStatusByBid(int id);
	public int updateReturn(int c_id, String date);
	public int updatePassword(String id);
	//select ================================
	public StockBookDTO selectBooksByBId(int id);
	public int isReturnalbe(int id);
	public int selectBooksByRstaus(int id);
	public List<BooksDTO> selectBooksByYState();
	public List<BooksDTO> selectBooksByNStateAndNStock();
	public List<BooksDTO> selectBooksByNStateAndYStock();
	public List<String> getIds();
	public List<StockBookDTO> selectBooksByNStockNBook();
	public List<StockBookDTO> selectBooksByYStockNBook();
	public String selectEid(String ENum);
	public List<EmployeeDTO> selectByEId(String searchKey);
	public List<EmployeeDTO> selectByEName(String searchKey);
	
	//insert =================================
	public int insertEmployee(String ENum,String EName);
	
	//===메소드================================================
	public List<EmployeeDTO> searchEmployee(String category,String searchKey);
	public List<EmployeeDTO> goSingup(String ENum, String EName, MultipartFile EFile);
	public StockBookDTO returnMethod(int id);
	public List<SearchRentRecordDto> selectRentRecordsByDateRange(String startDate,String endDate);
	public String checkSession(HttpSession session, String adminId, String[] admin);
}
