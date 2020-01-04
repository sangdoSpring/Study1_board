package com.sts.example.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sts.example.cmmn.SearchCmmnVO;
import com.sts.example.domain.BoardVO;
import com.sts.example.domain.PagingVO;

@Repository
public class MvcDAOImpl implements MvcDAO {

	@Autowired
	SqlSession sqlSession;
	
	private static final String namespace = "com.sts.example.BoardMapper";

	@Override
	public List<BoardVO> boardSelect() throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace+".boardSelect");
	}

	@Override
	public void boardInsert(BoardVO boardVO) throws Exception {

		sqlSession.insert(namespace+".boardInsert",boardVO);
	}

	@Override
	public BoardVO boardDetail(String b_idx) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".boardDetail",b_idx);
	}

	@Override
	public void boardUpdate(BoardVO boardVO) throws Exception {
		
		sqlSession.update(namespace+".boardUpdate",boardVO);
	}

	@Override
	public void boardDelete(String b_idx) throws Exception {
		
		sqlSession.delete(namespace+".boardDelete",b_idx);
	}

	@Override
	public void boardDeleteList(ArrayList<String> boardChk) throws Exception {
		// TODO Auto-generated method stub
		
		sqlSession.delete(namespace+".boardDeleteList",boardChk);
		
	}

	@Override
	public List<Map> selectPagingList(PagingVO pagingVO) throws Exception {
		
		return sqlSession.selectList(namespace+".selectPagingList",pagingVO);
	}

	@Override
	public HashMap<String, Object> selectPagingListCnt(PagingVO pagingVO) throws Exception {
		
		return  sqlSession.selectOne(namespace+".selectPagingListCnt",pagingVO);
	}

	@Override
	public List<Map> boardSearchList(SearchCmmnVO seachCmmnVO) throws Exception {
		
		return sqlSession.selectList(namespace+".boardSearchList",seachCmmnVO);
	}

	@Override
	public HashMap<String, Object> boardSearchListCnt(SearchCmmnVO seachCmmnVO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace+".selectSearchPagingListCnt",seachCmmnVO);
	}

	
	
	
}
