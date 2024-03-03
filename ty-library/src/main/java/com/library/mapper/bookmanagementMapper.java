package com.library.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.library.dto.BookInfoDTO;
import com.library.dto.BooksDTO;
import com.library.dto.CheckoutDTO;
import com.library.dto.EmployeeDTO;

@Mapper
public interface bookmanagementMapper {

		// 전체 사원 조회
		List<EmployeeDTO> selectAllEmplInfo();
		// 대여자 정보 조회
		EmployeeDTO selectEmplInfoByBid(@Param("b_id") int b_id);
		// 양도: DB 저장 중복 검사 시
		String selectCidByBid(@Param("b_id") int b_id);
		
		// 대여 정보 업로드
		void updateBooks(BooksDTO books);
		// 책 정보 조회
		BookInfoDTO selectBookInfo(@Param("b_id") int b_id);
		// 대여 정보 조회
		BooksDTO selectBooks(@Param("b_id") int b_id);
		// 대여 기록 추가
		void insertCheckout(CheckoutDTO checkout);
		// 양도: 대여 기록 업데이트
		void updateCheckout(CheckoutDTO checkout);
		
}
