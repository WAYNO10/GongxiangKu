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
public class GetArticleIdsByCategoryIdTest {
    
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
    public void testGetArticleIdsByCategoryId() {
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(testArticleCategory.getCategoryId());
        assertFalse("文章ID列表不应为空", articleIds.isEmpty());
        assertTrue("应包含测试添加的文章ID", articleIds.contains(testArticleCategory.getArticleId()));
    }
    
    @After
    public void tearDown() {
        if (articleCategoryDao != null && testArticleCategory != null) {
            articleCategoryDao.removeArticleCategory(testArticleCategory);
        }
    }
} 