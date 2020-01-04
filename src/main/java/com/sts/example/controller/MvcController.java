package com.sts.example.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sts.example.cmmn.SearchCmmnVO;
import com.sts.example.domain.BoardVO;
import com.sts.example.domain.PagingVO;
import com.sts.example.service.MvcService;

@Controller
public class MvcController {
	private static final Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@Autowired
	MvcService service;

	@RequestMapping(value = {"/notice","/"} ,method = RequestMethod.GET)
	public String noticeInit(PagingVO pagingVO,Model model) throws Exception{
		
		List<Map> pagingList = service.selectPagingList(pagingVO);
		HashMap<String, Object> pagingListCnt = service.selectPagingListCnt(pagingVO);
	      
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		
		createPaging(resMap,pagingList,pagingListCnt,pagingVO);
	      
	      model.addAttribute("pagingList", pagingList);
	      model.addAttribute("resMap",     resMap);
		
		return "/board/notice";
	}

	@RequestMapping(value = "/boardInsertInit", method = RequestMethod.GET)
	public String boardInsertInit() throws Exception{
		
		return "/board/boardInsert";
	}
	
	@RequestMapping(value = "/boardInsert", method = RequestMethod.POST)
	public String boardInsert(Model model, @ModelAttribute BoardVO boardVO) throws Exception{
		//@ModelAttribute BoardVO boardVO 앞에 어노테이션 붙여주면, form안의 name값과 VO안의 필드 명이 일치하는 경우 알아서 브라우저에서 가져온 값이 vo안에 매핑되어 들어감
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+boardVO.getB_userId());
		
		service.boardInsert(boardVO);
		
		//return 값이 보통 jsp파일 경로를 적어주나, redirect의 경우 다시 호출하고자하는 컨트롤러의 리퀘스트 매핑 값을 적어줘야함.
		return "redirect:/notice";
	}
	
	//insert 수동으로 글 수 늘리기
	@RequestMapping(value = "/boardInsert2", method = RequestMethod.GET)
	public String boardInsert2(Model model, @ModelAttribute BoardVO boardVO) throws Exception{
		
		for(int i = 1; i < 101; i++) {
		
		boardVO.setB_title("공지사항"+i);
		boardVO.setB_content("공지사항" + i +"의 내용입니다.");
		boardVO.setB_userId("admin");
		boardVO.setB_boardType("notice");
		
		service.boardInsert(boardVO);
		}
		
		return "redirect:/notice";
	}
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@RequestParam String b_idx,@RequestParam long page, Model model) throws Exception{
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+b_idx + "번 글 게시글상세");
		
		BoardVO boardVO = service.boardDetail(b_idx);
		model.addAttribute(boardVO);
		model.addAttribute("page", page);

		return "/board/boardDetail";
	}
	
	@RequestMapping(value = "/boardUpdateInit", method = RequestMethod.GET)
	public String boardUpdateInit(@RequestParam String b_idx, Model model) throws Exception{
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+b_idx + "번 글 게시글 수정 입력화면");
		
		BoardVO boardVO = service.boardDetail(b_idx);
		model.addAttribute(boardVO);
		
		return "/board/boardUpdate";
	}
	
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO boardVO, Model model) throws Exception{
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+boardVO.getB_idx() + "번 글 게시글 수정실행");
		
		service.boardUpdate(boardVO);  
		
		return "redirect:/boardDetail?b_idx="+boardVO.getB_idx();
	}
	
	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public String boardDelete(@RequestParam(required=false) String b_idx
			,@RequestParam(required=false) ArrayList<String> boardChk, Model model) throws Exception{
		
		logger.info(boardChk+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>게시글삭제 컨트롤러 진입");
		
		if (b_idx != null) {
			
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+b_idx + "번 글 게시글 삭제");
			
			service.boardDelete(b_idx);
			
		} else if(boardChk != null) {
			
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 체크박스 게시글 삭제");
			
			service.boardDeleteList(boardChk);
			
		}
		
		  
		
		return "redirect:/notice";
	}
	
	@RequestMapping(value="/boardSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//produces = 부분에 속성값 지정하면 컨트롤러에서 제이슨으로 데이터 넘겨줄때 한글깨짐 현상해결 스프링 3.1까지만 먹음 "text/plain;charset=UTF-8" >> 이건 text파일 넘길때 사용!
	@ResponseBody //이걸 적어주면 view 페이지 리턴이 아니라 데이터만 리턴(즉, http body 부분만 리턴)
	public String boardSearch(@RequestBody String searchObj) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		SearchCmmnVO seachCmmnVO = mapper.readValue(searchObj, SearchCmmnVO.class);
		
		logger.info(seachCmmnVO.getKeyword());
		
		List<Map> searchList = service.boardSearchList(seachCmmnVO);
		
		PagingVO pagingVO = new PagingVO();
		pagingVO.setPage(seachCmmnVO.getPage());
		
		HashMap<String, Object> searchPagingListCnt = service.selectSearchPagingListCnt(seachCmmnVO);
	      
		HashMap<String, Object> resMap = new HashMap<String, Object>();
		
		createPaging(resMap,searchList,searchPagingListCnt,pagingVO);
		
		resMap.put("searchList", searchList);
		
		String jsonObj = new Gson().toJson(resMap);
		
		return jsonObj;
	}
	
	
	
	
	private void createPaging(HashMap<String, Object> resMap, List<Map> pagingList,
			HashMap<String, Object> pagingListCnt,PagingVO pagingVO) {

		  resMap.put("page", pagingVO.getPage());
	      resMap.put("totalPage", pagingListCnt.get("TOTAL_PAGE"));
	      resMap.put("pageScale", pagingVO.getPageScale());
	      
	      // 페이지그룹(페이지 갯수당 그룹으로 묶음)
	      int pageGroup = (int) Math.ceil((double) pagingVO.getPage() / pagingVO.getPageScale());
	      
	      // 시작페이지 구하기
	      long startPage = (pageGroup - 1) * pagingVO.getPageScale() + 1;
	      pagingVO.setStartPage(startPage);
	      resMap.put("startPage", pagingVO.getStartPage());
	      
	      // 끝페이지 구하기
	      long endPage = startPage + pagingVO.getPageScale() - 1;
	      pagingVO.setEndPage(endPage);
	      resMap.put("endPage", pagingVO.getEndPage());
	      
	      // 이전페이지, 다음페이지
	      long prePage  = (pageGroup - 2) * pagingVO.getPageScale() + 1;
	      long nextPage = pageGroup * pagingVO.getPageScale() + 1;
	      
	      resMap.put("pageGroup", pageGroup);
	      resMap.put("prePage", prePage);
	      resMap.put("nextPage", nextPage);
		
	}
	
}
