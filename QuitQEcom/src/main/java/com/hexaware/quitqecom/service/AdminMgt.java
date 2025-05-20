package com.hexaware.quitqecom.service;

import java.util.List;

import com.hexaware.quitqecom.component.Category;
import com.hexaware.quitqecom.component.User;

public interface AdminMgt {
	List<User> getUsers();
	List<User> getAllSeller();
	void deleteUser(int userId);
	void deleteSeller(int sellerId);
	List<Category> getAllCategories();
	Category addCategory(Category category);
	Category updateCategory(int categoryId, Category category);
	void deleteCategory(int categoryId);
}
