package com.sts.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ajaxTestController {
	
	@RequestMapping(value="/ajaxTestInit")
	public String ajaxTsetInit() {
		
		return "ajax/ajaxJson";
	}
	
	@RequestMapping(value = "/ajaxTest", method = RequestMethod.POST)
	@ResponseBody
	public String ajaxTset(@RequestBody String objName) {
		
		//������Ʈ�Ķ��� input���� ���� ���� �ϳ��ϳ� ���������� ������ ���

		//@RequestBody�� �����۽��� data�ȿ� ��ܿ� ��� ������Ʈ ���� ������ ����.
		//ResponseBody�� �ٵ�ȿ� �ٽ� ���� �Ѱ��ִ°�
		
		System.out.println("����");
		
		System.out.println(objName);
		
		return objName;
	}

}
