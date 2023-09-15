package kr.co.kccbrew.comm.security.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Example01Controller {

	@GetMapping("/exam01")
    public String requestMethod(Model model) {
        return "security/webpage08_01";
    }
    
    @GetMapping("/admin/main")
    public String requestMethod2(Model model, @AuthenticationPrincipal User currentUser) {
        System.out.println("currentUser: " + currentUser);
        model.addAttribute("data", "/webpage01/adminPage.jsp");
        return "security/webpage01/adminPage";
    }
    
    @GetMapping("/manager/main")
    public String requestMethod3(Model model) {
        model.addAttribute("data", "/webpage01/managerPage.jsp");
        return "security/webpage01/managerPage";
    }
    
    @GetMapping("/member/main")
    public String requestMethod4(Model model) {
        model.addAttribute("data", "/webpage01/memberPage.jsp");
        return "security/webpage01/memberPage";
    }
    
    @GetMapping("/home/main")
    public String requestMethod5(Model model) {
        model.addAttribute("data", "/webpage01/homePage.jsp");
        return "security/webpage01/homePage";
    }
	
	
}
