package org.hdcd.service;

import org.hdcd.domain.Gallery;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;

public interface GalleryService {

	public void register(Gallery gallery) throws Exception;

	public Gallery read(Long galleryNo) throws Exception;
	
	public void modify(Gallery gallery) throws Exception;
	
	public void remove(Long galleryNo) throws Exception;
	
	public Page<Gallery> list(PageRequestVO pageRequestVO) throws Exception;
	
	public void updateView(Long galleryNo) throws Exception;
}
