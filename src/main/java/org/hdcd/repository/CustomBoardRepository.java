package org.hdcd.repository;

import org.hdcd.domain.Board;
import org.hdcd.domain.Gallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomBoardRepository {
	
	public Page<Board> getSearchPage(String type, String keyword, Pageable pageable);
	
	public Page<Gallery> getSearchPageG(String type, String keyword, Pageable pageable);

}
