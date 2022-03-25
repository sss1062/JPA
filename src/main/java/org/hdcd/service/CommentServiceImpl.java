package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.domain.Comment;
import org.hdcd.repository.BoardRepository;
import org.hdcd.repository.CommentRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

	private final CommentRepository repository;
	private final BoardRepository boardrepository;
	
	@Override
	public void register(Comment comment, Long boardNo) throws Exception {
		Board board = boardrepository.findById(boardNo).orElseThrow(() -> new IllegalArgumentException("해달 boardId가 없습니다. id=" + boardNo));
		comment.save(board);
		repository.save(comment);
	}

	@Override
	public void modify(Comment comment, Long boardNo) throws Exception {
		
	}

	@Override
	public void remove(Long id) throws Exception {
		repository.deleteById(id);
	}
	
	@Override
	public List<Comment> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "id"));
	}

}
