package org.hdcd.service;

import org.hdcd.domain.Gallery;
import org.hdcd.repository.GalleryRepository;
import org.hdcd.vo.PageRequestVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GalleryServiceImpl implements GalleryService {

	private final GalleryRepository repository;

	@Override
	public void register(Gallery gallery) throws Exception {
		repository.save(gallery);
	}

	@Override
	public Gallery read(Long galleryNo) throws Exception {
		return repository.getOne(galleryNo);
	}

	@Override
	public void modify(Gallery gallery) throws Exception {
		Gallery galleryEntity = repository.getOne(gallery.getGalleryNo());

		galleryEntity.setTitle(gallery.getTitle());
		galleryEntity.setContent(gallery.getContent());
		
		repository.save(galleryEntity);
	}

	@Override
	public void remove(Long galleryNo) throws Exception {
		repository.deleteById(galleryNo);
	}

	@Override
	public Page<Gallery> list(PageRequestVO pageRequestVO) throws Exception {
		String searchType = pageRequestVO.getSearchType();
		String keyword = pageRequestVO.getKeyword();
		
		int pageNumber = pageRequestVO.getPage() - 1;
		int sizePerPage = pageRequestVO.getSizePerPage();
		
		Pageable pageRequest = PageRequest.of(pageNumber, sizePerPage, Sort.Direction.DESC, "galleryNo");
		
		return repository.getSearchPageG(searchType, keyword, pageRequest);
	}

	@Override
	public void updateView(Long galleryNo) throws Exception
	{
		Gallery galleryEntity = repository.getOne(galleryNo);
		
		galleryEntity.setView(galleryEntity.getView() + 1);
				
		repository.save(galleryEntity);
	}
	
}
