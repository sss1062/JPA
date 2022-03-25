package org.hdcd.repository;

import org.hdcd.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery, Long>, CustomBoardRepository {
	
}
