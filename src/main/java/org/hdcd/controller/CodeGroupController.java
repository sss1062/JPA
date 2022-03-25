package org.hdcd.controller;

import org.hdcd.domain.CodeGroup;
import org.hdcd.service.CodeGroupService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/codegroup")
@PreAuthorize("hasRole('ADMIN')")
public class CodeGroupController {

	private final CodeGroupService service;

	@GetMapping("/register")
	public void registerForm(Model model) throws Exception {
		CodeGroup codeGroup = new CodeGroup();

		model.addAttribute(codeGroup);
	}

	@PostMapping("/register")
	public String register(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.register(codeGroup);

		rttr.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/codegroup/list";
	}

	@GetMapping("/list")
	public void list(Model model) throws Exception {
		model.addAttribute("list", service.list());
	}

	@GetMapping("/read")
	public void read(String groupCode, Model model) throws Exception {
		model.addAttribute(service.read(groupCode));
	}

	@PostMapping("/remove")
	public String remove(String groupCode, RedirectAttributes rttr) throws Exception {

		service.remove(groupCode);

		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/codegroup/list";
	}

	@GetMapping("/modify")
	public void modifyForm(String groupCode, Model model) throws Exception {
		model.addAttribute(service.read(groupCode));
	}

	@PostMapping("/modify")
	public String modify(CodeGroup codeGroup, RedirectAttributes rttr) throws Exception {
		service.modify(codeGroup);
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/codegroup/list";
	}
	
}
