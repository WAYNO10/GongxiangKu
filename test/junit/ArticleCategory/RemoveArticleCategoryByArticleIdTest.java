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
public class RemoveArticleCategoryByArticleIdTest {
    
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
    public void testRemoveArticleCategoryByArticleId() {
        boolean result = articleCategoryDao.removeArticleCategoryByArticleId(testArticleCategory.getArticleId());
        assertTrue("删除操作应该成功", result);
        
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(testArticleCategory.getCategoryId());
        assertFalse("不应包含已删除的文章ID", articleIds.contains(testArticleCategory.getArticleId()));
    }
} 