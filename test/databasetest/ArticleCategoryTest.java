/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasetest;
import entity.ArticleCategory;
import service.ArticleCategoryService;
import service.UserService;

/**
 *
 * @author 王陈宇科
 */
public class ArticleCategoryTest {
    public static void main(String[] args) {
               System.out.println(UserService.login("keke", "12345"));

//        ArticleCategory articleCategory1 = new ArticleCategory();
//        articleCategory1.setArticleId(4);
//        articleCategory1.setCategoryId(1);
//        ArticleCategoryService.addArticleCategory(articleCategory1);
//
//        ArticleCategory articleCategory2 = new ArticleCategory();
//        articleCategory2.setArticleId(5);
//        articleCategory2.setCategoryId(1);
//        ArticleCategoryService.addArticleCategory(articleCategory2);
//
//        ArticleCategory articleCategory3 = new ArticleCategory();
//        articleCategory3.setArticleId(5);
//        articleCategory3.setCategoryId(3);
//        ArticleCategoryService.addArticleCategory(articleCategory3);
//
//        ArticleCategory articleCategory4 = new ArticleCategory();
//        articleCategory4.setArticleId(6);
//        articleCategory4.setCategoryId(4);
//        ArticleCategoryService.addArticleCategory(articleCategory4);
//
//        ArticleCategory articleCategory5 = new ArticleCategory();
//        articleCategory5.setArticleId(2);
//        articleCategory5.setCategoryId(4);
//        ArticleCategoryService.addArticleCategory(articleCategory5);
//
//        ArticleCategory articleCategory6 = new ArticleCategory();
//        articleCategory6.setArticleId(2);
//        articleCategory6.setCategoryId(3);
//        ArticleCategoryService.addArticleCategory(articleCategory6);
//        ArticleCategory articleCategory = new ArticleCategory();
//        articleCategory.setArticleId(4);
//        articleCategory.setCategoryId(1);
//        ArticleCategoryService.removeArticleFromCategory(articleCategory);

        System.out.println(ArticleCategoryService.getAllArticleCategory());
          
//        System.out.println(ArticleCategoryService.getArticleByCategoryId(1));
//        System.out.println(ArticleCategoryService.getArticleByCategoryId(3));
//        System.out.println(ArticleCategoryService.getArticleByCategoryId(4));
    }
}
