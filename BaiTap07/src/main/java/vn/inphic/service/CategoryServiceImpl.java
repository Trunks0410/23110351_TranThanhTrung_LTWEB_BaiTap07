package vn.inphic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.inphic.entity.Category;
import vn.inphic.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository repository;

	@Override
	public List<Category> findAll() {
		return repository.findAll();
	}

	@Override
	public Page<Category> findAll(Pageable pageable) {
		return repository.findAll(pageable);
	}

	@Override
	public Page<Category> search(String keyword, Pageable pageable) {
		return repository.searchByName(keyword, pageable);
	}

	@Override
	public Optional<Category> findById(Long id) {
		return repository.findById(id);
	}

	@Override
	public Category save(Category category) {
		return repository.save(category);
	}

	@Override
	public void deleteById(Long id) {
		repository.deleteById(id);
	}
}