package org.hdcd.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.hdcd.common.security.domain.CustomUser;
import org.hdcd.domain.Board;
import org.hdcd.domain.Comment;
import org.hdcd.domain.Member;
import org.hdcd.dto.CodeLabelValue;
import org.hdcd.dto.PaginationDTO;
import org.hdcd.service.BoardService;
import org.hdcd.service.CommentService;
import org.hdcd.service.NoticeService;
import org.hdcd.vo.PageRequestVO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {

	private final BoardService service;
	private final NoticeService noticeService;
	private final CommentService commentService;

	@GetMapping("/register")
	@PreAuthorize("hasRole('MEMBER')")
	public void registerForm(Model model, Authentication authentication) throws Exception {
		CustomUser customUser = (CustomUser) authentication.getPrincipal();
		Member member = customUser.getMember();

		Board board = new Board();

		board.setWriter(member.getUserId());

		model.addAttribute(board);
	}

	@PostMapping("/register")
	@PreAuthorize("hasRole('MEMBER')")
	public String register(@Validated Board board, BindingResult result, RedirectAttributes rttr) throws Exception {
		if(result.hasErrors()) {
			return "board/register";
		}	
		
		service.register(board);

		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return "redirect:/board/list";
	}
	
	@PostMapping(value="/uploadSummernoteImageFile", produces = "application/json")
	@ResponseBody
	public JsonObject uploadSummernoteImageFile(@RequestParam("file") MultipartFile multipartFile) {
		
		JsonObject jsonObject = new JsonObject();
		
		String fileRoot = "C:\\summernote_image\\";	//저장될 외부 파일 경로
		String originalFileName = multipartFile.getOriginalFilename();	//오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
				
		String savedFileName = UUID.randomUUID() + extension;	//저장될 파일 명
		
		File targetFile = new File(fileRoot + savedFileName);	
		
		try {
			InputStream fileStream = multipartFile.getInputStream();
			FileUtils.copyInputStreamToFile(fileStream, targetFile);	//파일 저장
			jsonObject.addProperty("url", "/summernoteImage/"+savedFileName);
			jsonObject.addProperty("responseCode", "success");
				
		} catch (IOException e) {
			FileUtils.deleteQuietly(targetFile);	//저장된 파일 삭제
			jsonObject.addProperty("responseCode", "error");
			e.printStackTrace();
		}
		
		return jsonObject;
	}

	@GetMapping("/list")
	public void list(@ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model) throws Exception {
		Page<Board> page = service.list(pageRequestVO);
	
		model.addAttribute("pgntn", new PaginationDTO<>(page));
		
		List<CodeLabelValue> searchTypeCodeValueList = new ArrayList<CodeLabelValue>();
		searchTypeCodeValueList.add(new CodeLabelValue("n", "---"));
		searchTypeCodeValueList.add(new CodeLabelValue("t", "제목"));
		searchTypeCodeValueList.add(new CodeLabelValue("c", "내용"));
		searchTypeCodeValueList.add(new CodeLabelValue("w", "작성자"));
		searchTypeCodeValueList.add(new CodeLabelValue("tc", "제목+내용"));
		searchTypeCodeValueList.add(new CodeLabelValue("cw", "내용+작성자"));
		searchTypeCodeValueList.add(new CodeLabelValue("tcw", "제목+내용+작성자"));

		model.addAttribute("searchTypeCodeValueList", searchTypeCodeValueList);
		model.addAttribute("noticeList", noticeService.list());
	}

	@GetMapping("/read")
	public void read(Long boardNo, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model, Authentication authentication) throws Exception {
		service.updateView(boardNo);
		try
		{
			if(!authentication.getPrincipal().equals(null))
			{
				CustomUser customUser = (CustomUser) authentication.getPrincipal();
				Member member = customUser.getMember();
				Comment comment = new Comment();
				comment.setWriter(member.getUserId());
				model.addAttribute(comment);
			}
		}
		catch(Exception e)
		{
			
		}
		
		model.addAttribute(service.read(boardNo));
	}

	@PostMapping("/remove")
	@PreAuthorize("(hasRole('MEMBER') and principal.username == #writer) or hasRole('ADMIN')")
	public String remove(Long boardNo, PageRequestVO pageRequestVO, RedirectAttributes rttr, String writer) throws Exception {
		service.remove(boardNo);

		rttr.addAttribute("page", pageRequestVO.getPage());
		rttr.addAttribute("sizePerPage", pageRequestVO.getSizePerPage());
		rttr.addAttribute("searchType", pageRequestVO.getSearchType());
		rttr.addAttribute("keyword", pageRequestVO.getKeyword());
	   
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}

	@GetMapping("/modify")
	@PreAuthorize("hasRole('MEMBER')")
	public void modifyForm(Long boardNo, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, Model model) throws Exception {
		model.addAttribute(service.read(boardNo));
	}

	@PostMapping("/modify")
	@PreAuthorize("hasRole('MEMBER') and principal.username == #board.writer")
	public String modify(@Validated Board board, BindingResult result, @ModelAttribute("pgrq") PageRequestVO pageRequestVO, RedirectAttributes rttr) throws Exception {
		if(result.hasErrors()) {
			return "board/modify";
		}		
		
		service.modify(board);

		rttr.addAttribute("page", pageRequestVO.getPage());
		rttr.addAttribute("sizePerPage", pageRequestVO.getSizePerPage());
		rttr.addAttribute("searchType", pageRequestVO.getSearchType());
		rttr.addAttribute("keyword", pageRequestVO.getKeyword());
	    
		rttr.addFlashAttribute("msg", "SUCCESS");

		return "redirect:/board/list";
	}	
	
	
}
