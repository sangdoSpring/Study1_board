package com.sts.example.cmmn;

public class CmmnVO {
	
	// ����¡�� �ϱ� ���� �ּ����� �ʵ� 7��
	   private long rows      = 10; // �� �������� ���ϲ�
	   private long page      = 1; // ���� ��ġ�� ������
	   private long totalPage = 1; // �� ������
	   private long startPage = 1; // ���� ������ ���� ������
	   private long endPage   = 1; // ���� ������ �� ������
	   private long pageScale = 5; // ���� ������ ����
	   
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
