package org.hdcd.service;

import org.hdcd.domain.Board;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;

public interface BoardService {

	public void register(Board board) throws Exception;

	public Board read(Long boardNo) throws Exception;
	
	public void modify(Board board) throws Exception;
	
	public void remove(Long boardNo) throws Exception;
	
	public Page<Board> list(PageRequestVO pageRequestVO) throws Exception;
	
	public void updateView(Long boardNo) throws Exception;
}
