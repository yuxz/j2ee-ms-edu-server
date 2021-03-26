package com.yxz.edu.teacher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//@RequestMapping("tms")
public class ScheduleController {
	
	@RequestMapping("/index.html")
	public String index() {
		
		return "index";
	}
	
	@RequestMapping("/schedule.html")
	public String getschedule() {
		
		return "schedule";
	}
	
	@RequestMapping("/messagelist.html")
	public String getmessageList() {
		
		return "messagelist";
	}
	
	@RequestMapping("/message.html")
	public String getmessage() {
		
		return "message";
	}
	
	
	@RequestMapping("/class.html")
	public String getclass() {
		
		return "class";
	}

}
