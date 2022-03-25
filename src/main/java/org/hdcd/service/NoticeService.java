package org.hdcd.service;

import java.util.List;

import org.hdcd.domain.Notice;

public interface NoticeService {

	public void register(Notice notice) throws Exception;

	public Notice read(Long noticeNo) throws Exception;

	public void modify(Notice notice) throws Exception;

	public void remove(Long noticeNo) throws Exception;

	public List<Notice> list() throws Exception;
	
	public void updateView(Long noticeNo) throws Exception;
}
