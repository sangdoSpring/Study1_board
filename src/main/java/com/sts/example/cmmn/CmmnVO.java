package com.sts.example.cmmn;

public class CmmnVO {
	
	// 페이징을 하기 위한 최소한의 필드 7개
	   private long rows      = 10; // 한 페이지에 보일꺼
	   private long page      = 1; // 내가 위치한 페이지
	   private long totalPage = 1; // 총 페이지
	   private long startPage = 1; // 현재 상태의 시작 페이지
	   private long endPage   = 1; // 현재 상태의 끝 페이지
	   private long pageScale = 5; // 보일 페이지 갯수
	   
	   public long getRows() {
	      return rows;
	   }
	   public void setRows(long rows) {
	      this.rows = rows;
	   }
	   public long getPage() {
	      return page;
	   }
	   public void setPage(long page) {
	      this.page = page;
	   }
	   public long getTotalPage() {
	      return totalPage;
	   }
	   public void setTotalPage(long totalPage) {
	      this.totalPage = totalPage;
	   }
	   public long getStartPage() {
	      return startPage;
	   }
	   public void setStartPage(long startPage) {
	      this.startPage = startPage;
	   }
	   public long getEndPage() {
	      return endPage;
	   }
	   public void setEndPage(long endPage) {
	      this.endPage = endPage;
	   }
	   public long getPageScale() {
	      return pageScale;
	   }
	   public void setPageScale(long pageScale) {
	      this.pageScale = pageScale;
	   }

}
