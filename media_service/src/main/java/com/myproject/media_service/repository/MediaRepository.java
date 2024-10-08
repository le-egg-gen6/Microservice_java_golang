package com.myproject.media_service.repository;

import com.myproject.media_service.entity.Media;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 */
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

	Optional<Media> findByFileName(String fileName);

}
