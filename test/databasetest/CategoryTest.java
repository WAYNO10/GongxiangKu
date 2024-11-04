/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasetest;

import entity.Category;
import java.util.List;
import service.CategoryService;
import service.UserService;

/**
 *
 * @author 王陈宇科
 */
public class CategoryTest {
    public static void main(String[] args) {
        System.out.println(UserService.login("keke", "12345"));
//        Category category = new Category();
//
//        category.setName("地理");
//        category.setDescription("帮助我们更好地理解地球的多样性和复杂的自然及人文特征");
//        
//        category.setName("人文");
//        category.setDescription("更好地认识世界上的不同文化、经济和社会现象");
//        
//        category.setName("心理");
//        category.setDescription("探讨心理健康的重要性以及如何维护良好的心理状态");
//        int categoryId = CategoryService.createCategory(category);
//        System.out.println("新建分类"+categoryId);
        List<Category> allCategory = CategoryService.getAllCategory();
        System.out.println(allCategory);
        
        System.out.println(CategoryService.getCategoryById(3));
    }
    
}
