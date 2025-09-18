package vn.inphic.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import vn.inphic.entity.Category;
import vn.inphic.service.CategoryService;

import jakarta.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/admin/categories")
public class CategoryAdminController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(defaultValue = "") String keyword,
                       Model model) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<Category> categories;
        if (!keyword.isEmpty()) {
            categories = service.search(keyword, pageable);
        } else {
            categories = service.findAll(pageable);
        }
        model.addAttribute("categories", categories);
        model.addAttribute("keyword", keyword);
        return "admin/category/list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/form";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Category> category = service.findById(id);
        model.addAttribute("category", category.orElse(new Category()));
        return "admin/category/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute Category category, BindingResult result) {
        if (result.hasErrors()) {
            return "admin/category/form";
        }
        service.save(category);
        return "redirect:/admin/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/admin/categories";
    }
}