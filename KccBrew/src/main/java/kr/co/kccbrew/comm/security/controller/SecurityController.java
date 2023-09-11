package kr.co.kccbrew.comm.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

	@GetMapping("/login")
	public String requestMethod(Model model) {		   	  
		return "security/loginform";
	}

	@GetMapping("/admin")
	public String requestMethod2(Model model) {		  	  
		return  "security/webpage08_04";
	}	

	@GetMapping("/logout")
	public String logout(Model model) { 
		return "security/loginform";  
	}
}
