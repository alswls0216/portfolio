package com.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import com.library.dto.BooksDTO;
import com.library.dto.EmployeeDTO;
import com.library.dto.SearchRentRecordDto;
import com.library.dto.StockBookDTO;

@Mapper
public interface adminStockMapper {

	//update=================================================
	public void updateInitialNStock(); //재고 조사 시작시 모든 stock 상태 N으로 초기화.
	public int updateYStockByBId(int id); //입력 id에 맞는 stock 상태 Y으로 초기화
	public int updateNStatusByBid(int id); //입력 id에 맞는 Status 상태 N으로 초기화
	public int updateReturn(int c_id, String date);
	public int updatePassword(String id);
	//select=================================================
	public StockBookDTO selectBooksByBId(int id);
	public int isReturnalbe(int id);
	public int selectBooksByRstaus(int id);
	public List<BooksDTO> selectBooksByYState();
	public List<BooksDTO> selectBooksByNStateAndNStock();
	public List<BooksDTO> selectBooksByNStateAndYStock();
	public List<String> getIds();
	public List<StockBookDTO> selectBooksByNStockNBook();
	public List<StockBookDTO> selectBooksByYStockNBook();
	public List<SearchRentRecordDto> selectRentRecordsByDateRange(String startDate,String endDate);
	public String selectEid(String ENum);
	public List<EmployeeDTO> selectByEId(@Param ("searchKey")String searchKey);
	public List<EmployeeDTO> selectByEName(@Param ("searchKey")String searchKey);
	//insert==================================================
	public int insertEmployee(@Param("ENum") String ENum,@Param ("EName") String EName);
}
