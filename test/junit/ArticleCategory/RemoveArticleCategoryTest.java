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
public class RemoveArticleCategoryTest {
    
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
    public void testRemoveArticleCategory() {
        boolean result = articleCategoryDao.removeArticleCategory(testArticleCategory);
        assertTrue("删除操作应该成功", result);
        
        List<ArticleCategory> categories = articleCategoryDao.getAllArticleCategories();
        boolean found = false;
        for (ArticleCategory ac : categories) {
            if (ac.getArticleId() == testArticleCategory.getArticleId() 
                && ac.getCategoryId() == testArticleCategory.getCategoryId()) {
                found = true;
                break;
            }
        }
        assertFalse("文章分类应该已被删除", found);
    }
} 