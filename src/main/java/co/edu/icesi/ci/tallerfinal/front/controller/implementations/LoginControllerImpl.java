package co.edu.icesi.ci.tallerfinal.front.controller.implementations;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.icesi.ci.tallerfinal.front.controller.interfaces.LoginController;

@Controller
@RequestMapping("/front")
public class LoginControllerImpl implements LoginController{
	
	@Override
	@GetMapping("/login")
	public String login() {
		return "/customLogin";
	}

	@GetMapping("/")
	public String index(){
		return "/index";
	}
}
