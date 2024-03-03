package com.library.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.*;
import com.library.mapper.SearchMapper;
import com.library.service.SearchService;

@Service // 객체 생성
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchMapper searchMapper;

    @Override
    public int maxNum() throws Exception {
        return searchMapper.maxNum();
    }

    @Override
    public int getDataCount(String searchKey, String searchValue) throws Exception {
        return searchMapper.getDataCount(searchKey, searchValue);
    }

    @Override
    public List<SearchResultDto> getLists(SearchQuery query) throws Exception {
        return searchMapper.getLists(query);
    }

    public List<SearchRemoveDuplicateDto> removeDuplicates(List<SearchResultDto> list) {

        Map<String, SearchRemoveDuplicateDto> resultMap = new HashMap<>();

        for (SearchResultDto resultDto : list) {
            String title = resultDto.getTitle();

            SearchRemoveDuplicateDto dto = resultMap.computeIfAbsent(title, k -> {
                SearchRemoveDuplicateDto newDto = new SearchRemoveDuplicateDto();
                newDto.setTitle(resultDto.getTitle());
                newDto.setAuthor(resultDto.getAuthor());
                newDto.setPublisher(resultDto.getPublisher());
                newDto.setCategory(resultDto.getCategory());
                newDto.setRenterList(new ArrayList<>());
                newDto.setId(resultDto.getId());
                return newDto;
            });

            // Add renter to the renterList
            dto.getRenterList().add(resultDto.getRenter());
        }

        // Convert the values of resultMap to a List
        List<SearchRemoveDuplicateDto> resultList = new ArrayList<>(resultMap.values());

        // Set the bookStatus based on the values in the input list
        for (SearchRemoveDuplicateDto dto : resultList) {
            String bookStatus = determineBookStatus(list, dto.getTitle());
            dto.setBookStatus(bookStatus);
        }

        return resultList;
    }

    private static String determineBookStatus(List<SearchResultDto> list, String title) {
        for (SearchResultDto resultDto : list) {
            if (resultDto.getTitle().equals(title)) {
                if ("A".equals(resultDto.getBookStatus())) {
                    return "대여 가능";
                }
            }
        }
        return "대여 불가";
    }

    @Override
    public List<SearchResultDto> getBook(int id) {
        return searchMapper.getBook(id);
    }

    @Override
    public List<OverdueDto> getOverdue() {
        return searchMapper.getOverdue();
    }

}