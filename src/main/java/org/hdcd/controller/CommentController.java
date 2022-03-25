package org.hdcd.controller;

import org.hdcd.domain.Comment;
import org.hdcd.service.BoardService;
import org.hdcd.service.CommentService;
import org.hdcd.vo.PageRequestVO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/comment")
public class CommentController {
	
	private final CommentService commentService;

	@PostMapping("/{boardNo}/addComment")
	@PreAuthorize("hasRole('MEMBER')")
	@ResponseBody
	public String addComment(@PathVariable Long boardNo, @RequestBody Comment comment) throws Exception {
		commentService.register(comment, boardNo);
		
		return "redirect:/board/list";
	}
	
	@PostMapping("/remove/{id}")
	@PreAuthorize("(hasRole('MEMBER') or hasRole('ADMIN'))")
	public String removeComment(@PathVariable Long id) throws Exception {
		System.out.println(id);
		commentService.remove(id);

		return "redirect:/board/list";
	}
	
	@PostMapping("/modifyComment/{boardNo}")
	@PreAuthorize("hasRole('MEMBER')")
	@ResponseBody
	public String modComment(@PathVariable Long boardNo, @RequestBody Comment comment) throws Exception {
		commentService.modify(comment, boardNo);
		
		return "redirect:/board/list";
	}
	
}
