package junit.ArticleCategory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Dao.ArticleCategoryDao;
import entity.ArticleCategory;

/**
 * @author 莫涵越
 */
public class RemoveNonExistentArticleCategoryTest {
    
    private ArticleCategoryDao articleCategoryDao;
    private ArticleCategory testArticleCategory;
    
    @Before
    public void setUp() {
        articleCategoryDao = new ArticleCategoryDao();
        testArticleCategory = new ArticleCategory();
        testArticleCategory.setArticleId(999); // 使用一个不太可能存在的ID
        testArticleCategory.setCategoryId(999);
    }
    
    @Test
    public void testRemoveNonExistentArticleCategory() {
        boolean result = articleCategoryDao.removeArticleCategory(testArticleCategory);
        assertFalse("删除不存在的记录应该返回false", result);
    }
} 