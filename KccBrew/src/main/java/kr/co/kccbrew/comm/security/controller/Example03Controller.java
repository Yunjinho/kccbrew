package kr.co.kccbrew.comm.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Example03Controller {

	@GetMapping("/exam03")
	public String requestMethod(Model model) {		   	  
	   	return "security/webpage08_03";
	}
	
	@GetMapping("/admin/tag")
	public String requestMethod2(Model model) {		  	  
	   	return  "security/webpage08_03";
	}	
	 
}
