package junit.ArticleCategory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Dao.ArticleCategoryDao;
import entity.ArticleCategory;
import java.util.List;

/**
 * @author 莫涵越
 */
public class DataConsistencyTest {
    
    private ArticleCategoryDao articleCategoryDao;
    private ArticleCategory testArticleCategory;
    
    @Before
    public void setUp() {
        articleCategoryDao = new ArticleCategoryDao();
        testArticleCategory = new ArticleCategory();
        testArticleCategory.setArticleId(1);
        testArticleCategory.setCategoryId(1);
    }
    
    @Test
    public void testDataConsistencyAfterCRUD() {
        // 添加记录
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        // 验证添加
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(1);
        assertTrue(articleIds.contains(1));
        
        // 删除记录
        articleCategoryDao.removeArticleCategory(testArticleCategory);
        
        // 验证删除
        articleIds = articleCategoryDao.getArticleIdsByCategoryId(1);
        assertFalse(articleIds.contains(1));
        
        // 再次添加
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        // 验证再次添加
        articleIds = articleCategoryDao.getArticleIdsByCategoryId(1);
        assertTrue(articleIds.contains(1));
    }
} 