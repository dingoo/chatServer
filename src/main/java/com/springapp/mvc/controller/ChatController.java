package com.springapp.mvc.controller;

import com.springapp.mvc.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ChatController {

	@Autowired
	ChatService chatService;
	@ResponseBody
	@RequestMapping(value = "/register" ,method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String register(@RequestBody String body) {
		String responseBody=chatService.register(body);
		return responseBody;
	}

	@ResponseBody
	@RequestMapping(value = "/getUserInfo" ,method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getUserInfo(@RequestBody String body) {
		String responseBody=chatService.getUserInfo(body);
		return responseBody;
	}

	@ResponseBody
	@RequestMapping(value = "/getFriends" ,method = RequestMethod.POST,produces = "text/json;charset=UTF-8")
	public String getFriends(@RequestBody String body) {
		String responseBody=chatService.getFriends(body);
		return responseBody;
	}

}