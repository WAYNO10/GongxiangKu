package junit.ArticleCategory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Dao.ArticleCategoryDao;
import entity.ArticleCategory;
import java.util.List;

/**
 * @author 莫涵越
 */
public class GetAllArticleCategoriesTest {
    
    private ArticleCategoryDao articleCategoryDao;
    private ArticleCategory testArticleCategory;
    
    @Before
    public void setUp() {
        articleCategoryDao = new ArticleCategoryDao();
        testArticleCategory = new ArticleCategory();
        testArticleCategory.setArticleId(1);
        testArticleCategory.setCategoryId(1);
        articleCategoryDao.addArticleCategory(testArticleCategory);
    }
    
    @Test
    public void testGetAllArticleCategories() {
        List<ArticleCategory> categories = articleCategoryDao.getAllArticleCategories();
        assertNotNull("返回的列表不应为null", categories);
        assertFalse("返回的列表不应为空", categories.isEmpty());
    }
    
    @After
    public void tearDown() {
        if (articleCategoryDao != null && testArticleCategory != null) {
            articleCategoryDao.removeArticleCategory(testArticleCategory);
        }
    }
} 