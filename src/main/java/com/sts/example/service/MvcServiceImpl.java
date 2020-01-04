package com.sts.example.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sts.example.cmmn.SearchCmmnVO;
import com.sts.example.dao.MvcDAO;
import com.sts.example.domain.BoardVO;
import com.sts.example.domain.PagingVO;

@Service
public class MvcServiceImpl implements MvcService {

	@Autowired
	MvcDAO dao;
	
	@Override
	public List<BoardVO> boardSelect() throws Exception {

		return dao.boardSelect();
	}

	@Override
	public void boardInsert(BoardVO boardVO) throws Exception {
		
		dao.boardInsert(boardVO);
	}

	@Override
	public BoardVO boardDetail(String b_idx) throws Exception {
		// TODO Auto-generated method stub
		return dao.boardDetail(b_idx);
	}

	@Override
	public void boardUpdate(BoardVO boardVO) throws Exception {
		
		dao.boardUpdate(boardVO);
	}

	@Override
	public void boardDelete(String b_idx) throws Exception {
		
		dao.boardDelete(b_idx);
		
	}

	@Override
	public void boardDeleteList(ArrayList<String> boardChk) throws Exception {
		// TODO Auto-generated method stub
		
		dao.boardDeleteList(boardChk);
		
	}

	@Override
	public List<Map> selectPagingList(PagingVO pagingVO) throws Exception {
		
		return dao.selectPagingList(pagingVO);
	}

	@Override
	public HashMap<String, Object> selectPagingListCnt(PagingVO pagingVO) throws Exception {
		
		return dao.selectPagingListCnt(pagingVO);
	}

	@Override
	public List<Map> boardSearchList(SearchCmmnVO seachCmmnVO) throws Exception {
		
		return dao.boardSearchList(seachCmmnVO);
	}

	@Override
	public HashMap<String, Object> selectSearchPagingListCnt(SearchCmmnVO seachCmmnVO) throws Exception {
		
		return dao.boardSearchListCnt(seachCmmnVO);
	}

}
