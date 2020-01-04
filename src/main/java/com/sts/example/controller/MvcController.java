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
		//@ModelAttribute BoardVO boardVO �տ� ������̼� �ٿ��ָ�, form���� name���� VO���� �ʵ� ���� ��ġ�ϴ� ��� �˾Ƽ� ���������� ������ ���� vo�ȿ� ���εǾ� ��
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+boardVO.getB_userId());
		
		service.boardInsert(boardVO);
		
		//return ���� ���� jsp���� ��θ� �����ֳ�, redirect�� ��� �ٽ� ȣ���ϰ����ϴ� ��Ʈ�ѷ��� ������Ʈ ���� ���� ���������.
		return "redirect:/notice";
	}
	
	//insert �������� �� �� �ø���
	@RequestMapping(value = "/boardInsert2", method = RequestMethod.GET)
	public String boardInsert2(Model model, @ModelAttribute BoardVO boardVO) throws Exception{
		
		for(int i = 1; i < 101; i++) {
		
		boardVO.setB_title("��������"+i);
		boardVO.setB_content("��������" + i +"�� �����Դϴ�.");
		boardVO.setB_userId("admin");
		boardVO.setB_boardType("notice");
		
		service.boardInsert(boardVO);
		}
		
		return "redirect:/notice";
	}
	
	@RequestMapping(value = "/boardDetail", method = RequestMethod.GET)
	public String boardDetail(@RequestParam String b_idx,@RequestParam long page, Model model) throws Exception{
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+b_idx + "�� �� �Խñۻ�");
		
		BoardVO boardVO = service.boardDetail(b_idx);
		model.addAttribute(boardVO);
		model.addAttribute("page", page);

		return "/board/boardDetail";
	}
	
	@RequestMapping(value = "/boardUpdateInit", method = RequestMethod.GET)
	public String boardUpdateInit(@RequestParam String b_idx, Model model) throws Exception{
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+b_idx + "�� �� �Խñ� ���� �Է�ȭ��");
		
		BoardVO boardVO = service.boardDetail(b_idx);
		model.addAttribute(boardVO);
		
		return "/board/boardUpdate";
	}
	
	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public String boardUpdate(@ModelAttribute BoardVO boardVO, Model model) throws Exception{
		
		logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+boardVO.getB_idx() + "�� �� �Խñ� ��������");
		
		service.boardUpdate(boardVO);  
		
		return "redirect:/boardDetail?b_idx="+boardVO.getB_idx();
	}
	
	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public String boardDelete(@RequestParam(required=false) String b_idx
			,@RequestParam(required=false) ArrayList<String> boardChk, Model model) throws Exception{
		
		logger.info(boardChk+">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>�Խñۻ��� ��Ʈ�ѷ� ����");
		
		if (b_idx != null) {
			
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+b_idx + "�� �� �Խñ� ����");
			
			service.boardDelete(b_idx);
			
		} else if(boardChk != null) {
			
			logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> üũ�ڽ� �Խñ� ����");
			
			service.boardDeleteList(boardChk);
			
		}
		
		  
		
		return "redirect:/notice";
	}
	
	@RequestMapping(value="/boardSearch", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	//produces = �κп� �Ӽ��� �����ϸ� ��Ʈ�ѷ����� ���̽����� ������ �Ѱ��ٶ� �ѱ۱��� �����ذ� ������ 3.1������ ���� "text/plain;charset=UTF-8" >> �̰� text���� �ѱ涧 ���!
	@ResponseBody //�̰� �����ָ� view ������ ������ �ƴ϶� �����͸� ����(��, http body �κи� ����)
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
	      
	      // �������׷�(������ ������ �׷����� ����)
	      int pageGroup = (int) Math.ceil((double) pagingVO.getPage() / pagingVO.getPageScale());
	      
	      // ���������� ���ϱ�
	      long startPage = (pageGroup - 1) * pagingVO.getPageScale() + 1;
	      pagingVO.setStartPage(startPage);
	      resMap.put("startPage", pagingVO.getStartPage());
	      
	      // �������� ���ϱ�
	      long endPage = startPage + pagingVO.getPageScale() - 1;
	      pagingVO.setEndPage(endPage);
	      resMap.put("endPage", pagingVO.getEndPage());
	      
	      // ����������, ����������
	      long prePage  = (pageGroup - 2) * pagingVO.getPageScale() + 1;
	      long nextPage = pageGroup * pagingVO.getPageScale() + 1;
	      
	      resMap.put("pageGroup", pageGroup);
	      resMap.put("prePage", prePage);
	      resMap.put("nextPage", nextPage);
		
	}
	
}
