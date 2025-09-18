package vn.inphic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import vn.inphic.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	@Query("SELECT c FROM Category c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%'))")
	Page<Category> searchByName(@Param("keyword") String keyword, Pageable pageable);
}