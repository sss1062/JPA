package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.domain.Notice;
import org.hdcd.repository.NoticeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

	private final NoticeRepository repository;

	@Override
	public void register(Notice notice) throws Exception {
		repository.save(notice);
	}

	@Override
	public Notice read(Long noticeNo) throws Exception {
		return repository.getOne(noticeNo);
	}

	@Override
	public void modify(Notice notice) throws Exception {
		Notice noticeEntity = repository.getOne(notice.getNoticeNo());

		noticeEntity.setTitle(notice.getTitle());
		
		repository.save(noticeEntity);
	}

	@Override
	public void remove(Long noticeNo) throws Exception {
		repository.deleteById(noticeNo);
	}

	@Override
	public List<Notice> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "noticeNo"));
	}

	@Override
	public void updateView(Long noticeNo) throws Exception
	{
		Notice noticeEntity = repository.getOne(noticeNo);
		
		noticeEntity.setView(noticeEntity.getView() + 1);
				
		repository.save(noticeEntity);
	}
	
}
