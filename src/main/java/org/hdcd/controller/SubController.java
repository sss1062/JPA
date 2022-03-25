package org.hdcd.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sub")
public class SubController {

	@GetMapping("/sub01")
	public void sub01(Model model, Authentication authentication) throws Exception {
		
	}

	
}
