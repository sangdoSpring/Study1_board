package com.sts.example.domain;

import java.time.LocalDateTime;

import org.apache.ibatis.type.Alias;

@Alias("BoardVO")
public class BoardVO {
	
	private int b_idx;
	private String b_userId, b_boardType, b_title, b_content;
	private LocalDateTime b_createDate, b_updateDate;
	
	
	public int getB_idx() {
		return b_idx;
	}
	public void setB_idx(int b_idx) {
		this.b_idx = b_idx;
	}
	public String getB_userId() {
		return b_userId;
	}
	public void setB_userId(String b_userId) {
		this.b_userId = b_userId;
	}
	public String getB_boardType() {
		return b_boardType;
	}
	public void setB_boardType(String b_boardType) {
		this.b_boardType = b_boardType;
	}
	public String getB_title() {
		return b_title;
	}
	public void setB_title(String b_title) {
		this.b_title = b_title;
	}
	public String getB_content() {
		return b_content;
	}
	public void setB_content(String b_content) {
		this.b_content = b_content;
	}
	public LocalDateTime getB_createDate() {
		return b_createDate;
	}
	public void setB_createDate(LocalDateTime b_createDate) {
		this.b_createDate = b_createDate;
	}
	public LocalDateTime getB_updateDate() {
		return b_updateDate;
	}
	public void setB_updateDate(LocalDateTime b_updateDate) {
		this.b_updateDate = b_updateDate;
	}
	
	
}
