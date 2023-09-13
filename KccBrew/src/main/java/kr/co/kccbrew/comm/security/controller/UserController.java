package kr.co.kccbrew.comm.security.controller;



import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.kccbrew.comm.register.model.RegisterVo;
import kr.co.kccbrew.comm.security.model.UserVo;
import kr.co.kccbrew.comm.security.service.UserService;


@Controller
public class UserController {

	@Autowired
	private UserService userService;

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

	/*회원가입*/
	@GetMapping("/signup")
	public String showRegistrationForm(Model model) {
		return "security/register";
	}

	@PostMapping("/register")
	public String register(UserVo userVo,@Value("#{serverImgPath['localPath']}")String localPath,@Value("#{serverImgPath['userPath']}")String path,HttpServletRequest request) {

		/*User객체에 파라미터로 넣을 정보 가공*/
	/*	String username = userVo.getUserId();
		String password = userVo.getUserPwd();
		String selectedRole = null;
		
		switch (userVo.getUserTypeCd()) {
		case "01":
			selectedRole = "ROLE_ADMIN";
			break;
		case "02":
			selectedRole = "ROLE_MANAGER";
			break;
		case "03":
			selectedRole = "ROLE_MECHA";
			break;
		}

		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		authorities.add(new SimpleGrantedAuthority(selectedRole));
		
		User user = new User(username, password, authorities);*/
		
		/*데이터확인*/
		System.out.println("userVo: " + userVo);
		
		String folderPath=request.getServletContext().getRealPath("")+path;
		File folder = new File(folderPath);
        // 폴더가 존재하지 않으면 폴더를 생성합니다.
        if (!folder.exists()) {
            boolean success = folder.mkdirs(); // 폴더 생성 메소드
        }
        userVo.setStorageLocation(path);
        userVo.setServerSavePath(folderPath);
		
		//local 저장 위치 배포할땐 삭제
		File folder2 = new File(localPath+path);
		// 폴더가 존재하지 않으면 폴더를 생성합니다.
		if (!folder2.exists()) {
			boolean success = folder2.mkdirs(); // 폴더 생성 메소드
		}
		userVo.setLocalSavePath(localPath+path);
		
		userService.registerUser(userVo);
		
		return "redirect:/login";
	}

}
