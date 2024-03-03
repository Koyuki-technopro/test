package com.example.category;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.user.LoginUser;

@Controller
public class CategoryController {
	//	private final CategoryService categoryService;
	//
	//	@Autowired
	//	public CategoryController(CategoryService categoryService) {
	//		this.categoryService = categoryService;
	//	}

	@GetMapping("/category/list")
	public String index(@AuthenticationPrincipal LoginUser loginUser, Model model) {
		Integer userId = loginUser.getUser().getId();
		model.addAttribute("userId", userId);
		return "category/categoryList";
	}

	@PostMapping("/category/list")
	public String processForm(
			@RequestParam("selectedCategory") Integer selectedCategoryId,
			@AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = loginUser.getUser().getId();
		return "redirect:/question/list/" + userId + "/" + selectedCategoryId;
	}
}
