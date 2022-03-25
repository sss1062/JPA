package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Comment;

public interface CommentService {
	
	public void register(Comment comment, Long boardNo) throws Exception;

	public void modify(Comment comment, Long boardNo) throws Exception;
	
	public void remove(Long id) throws Exception;

	public List<Comment> list() throws Exception;

}
