package org.hdcd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginController {

	@RequestMapping("/login")
	public String loginForm(String error, String logout, Model model) {
		if (error != null) {
			model.addAttribute("error", "존재하지 않는 아이디나 패스워드 입니다.");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!!");
		}

		return "auth/loginForm";
	}
	
}
