package kr.co.kccbrew;

import java.text.DateFormat; 
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.co.kccbrew.asMng.service.AsMngService;
import kr.co.kccbrew.comm.security.service.UserService;
import lombok.RequiredArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequiredArgsConstructor
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model,HttpServletRequest httpServletRequest, Authentication authentication) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		/*model.addAttribute("serverTime", formattedDate );*/

		/*권한에 따른 메인페이지 이동*/
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		GrantedAuthority role = null;

		for ( GrantedAuthority authority : userDetails.getAuthorities()) {
			role = authority;
		}

		if(role.equals(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
			return "redirect:/admin/main";
		} else if (role.equals(new SimpleGrantedAuthority("ROLE_MANAGER"))) {
			return "redirect:/store-mng/main";
		} else if(role.equals(new SimpleGrantedAuthority("ROLE_MECHA")))  {
			return "redirect:/mechanic/main";
		} else {
			return "redirect:/logout";
		}
	}
}

