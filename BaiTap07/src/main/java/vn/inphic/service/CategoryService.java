package vn.inphic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.inphic.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
	List<Category> findAll();

	Page<Category> findAll(Pageable pageable);

	Page<Category> search(String keyword, Pageable pageable);

	Optional<Category> findById(Long id);

	Category save(Category category);

	void deleteById(Long id);
}