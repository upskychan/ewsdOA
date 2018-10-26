package app.home.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import app.common.base.ExecuteResult;
import app.home.service.UserService;
import app.home.vo.LoginVo;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	private UserService userService;
	private ExecuteResult executeResult = new ExecuteResult();
	
	public UserService getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
		
	@RequestMapping("/loginsubmit")
	public ResponseEntity<String> submit(HttpSession session,LoginVo loginVo){
		String status="0";
		HttpHeaders headers = new HttpHeaders(); 
		//MediaType mediaType = new MediaType("text", "html", Charset.forName("UTF-8"));
		MediaType mediaType = new MediaType("text", "json", Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		
		String code = (String) session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		//判断验证码	
		if(!code.equals(loginVo.getVcode())){
			status="1";
			return new ResponseEntity<String>(status, headers, HttpStatus.OK);
		}
		
		try {
			LoginVo user = this.userService.findLoginUser(loginVo);
			if(null == user) {
				status="2";
				return new ResponseEntity<String>(status, headers, HttpStatus.OK);
			}
			
			session.setAttribute("USER", user);
			session.setAttribute("USERID", user.getUid());
		} catch (Exception e) {
			e.printStackTrace();
			status="5";
			return new ResponseEntity<String>(status, headers, HttpStatus.OK);
		}
		return new ResponseEntity<String>(status, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value="/logout") 
	public String logout(HttpServletRequest request){
		HttpHeaders headers = new HttpHeaders(); 
		MediaType mediaType = new MediaType("text", "html", Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
		request.getSession().removeAttribute("USER");
		return "/login";
	}

}
