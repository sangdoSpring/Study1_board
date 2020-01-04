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
		
		//리퀘스트파람은 input으로 받은 값들 하나하나 개별적으로 받을때 사용

		//@RequestBody는 에이작스의 data안에 담겨온 모든 리퀘스트 값을 받을때 쓴다.
		//ResponseBody는 바디안에 다시 값을 넘겨주는것
		
		System.out.println("진입");
		
		System.out.println(objName);
		
		return objName;
	}

}
