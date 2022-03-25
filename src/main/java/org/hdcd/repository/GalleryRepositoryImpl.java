package org.hdcd.repository;

import java.util.List;

import org.hdcd.domain.Board;
import org.hdcd.domain.Gallery;
import org.hdcd.domain.QGallery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.JPQLQuery;

public class GalleryRepositoryImpl extends QuerydslRepositorySupport implements CustomBoardRepository {

	public GalleryRepositoryImpl() {
		super(Gallery.class);
	}
	
	@Override
	public Page<Gallery> getSearchPageG(String searchType, String keyword, Pageable pageable) {
		String title = keyword;
		String writer = keyword;
		String content = keyword;
		
		QGallery gallery = QGallery.gallery;
		
		JPQLQuery<Gallery> query = from(gallery);
		
		if(searchType != null && searchType.length() > 0) {
			if(searchType.equals("t")) {
				query.where(gallery.title.like("%" + title +"%"));
				query.orderBy(gallery.galleryNo.desc());
			}
			else if(searchType.equals("w")) {
				query.where(gallery.writer.like("%" + writer +"%"));
				query.orderBy(gallery.galleryNo.desc());
			}
			else if(searchType.equals("c")) {
				query.where(gallery.content.like("%" + content +"%"));
				query.orderBy(gallery.galleryNo.desc());
			}
			else if(searchType.equals("tc")) {
				query.where(gallery.title.like("%" + title +"%").or(gallery.content.like("%" + content +"%")));
				query.orderBy(gallery.galleryNo.desc());
			}
			else if(searchType.equals("cw")) {
				query.where(gallery.content.like("%" + content +"%").or(gallery.writer.like("%" + writer +"%")));
				query.orderBy(gallery.galleryNo.desc());
			}
			else if(searchType.equals("tcw")) {
				BooleanBuilder builder = new BooleanBuilder();
				builder.and(gallery.title.like("%" + title +"%"))
				.or(gallery.content.like("%" + content +"%"))
				.or(gallery.writer.like("%" + writer +"%"));
				
				query.where(builder);
				query.orderBy(gallery.galleryNo.desc());
			}			
			else {
				query.where(gallery.galleryNo.gt(0L));
				query.orderBy(gallery.galleryNo.desc());
			}
		}
		else {
			query.where(gallery.galleryNo.gt(0L));
			query.orderBy(gallery.galleryNo.desc());
		}
		
		query.offset(pageable.getOffset());
		query.limit(pageable.getPageSize());
		
		List<Gallery> resultList = query.fetch();
		
		long total = query.fetchCount();

		return new PageImpl<>(resultList, pageable, total);
	}

	@Override
	public Page<Board> getSearchPage(String type, String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
