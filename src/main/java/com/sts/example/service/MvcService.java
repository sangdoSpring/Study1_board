package com.sts.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sts.example.cmmn.SearchCmmnVO;
import com.sts.example.domain.BoardVO;
import com.sts.example.domain.PagingVO;

public interface MvcService {

	List<BoardVO> boardSelect() throws Exception;

	void boardInsert(BoardVO boardVO) throws Exception;

	BoardVO boardDetail(String b_idx)throws Exception;

	void boardUpdate(BoardVO boardVO)throws Exception;

	void boardDelete(String b_idx) throws Exception;

	void boardDeleteList(ArrayList<String> boardChk) throws Exception;

	List<Map> selectPagingList(PagingVO pagingVO)throws Exception;

	HashMap<String, Object> selectPagingListCnt(PagingVO pagingVO) throws Exception;

	List<Map> boardSearchList(SearchCmmnVO seachCmmnVO) throws Exception;

	HashMap<String, Object> selectSearchPagingListCnt(SearchCmmnVO seachCmmnVO) throws Exception;
	

}
