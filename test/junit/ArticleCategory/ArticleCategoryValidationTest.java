package junit.ArticleCategory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Dao.ArticleCategoryDao;
import entity.ArticleCategory;
import java.util.List;

/**
 * @author 滕昊宸
 */
public class ArticleCategoryValidationTest {
    
    private ArticleCategoryDao articleCategoryDao;
    private ArticleCategory testArticleCategory;
    
    @Before
    public void setUp() {
        articleCategoryDao = new ArticleCategoryDao();
        testArticleCategory = new ArticleCategory();
    }
    
    @Test
    public void testLargeArticleId() {
        testArticleCategory.setArticleId(999999999);
        testArticleCategory.setCategoryId(1);
        
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(1);
        assertTrue("应该能添加大数值的文章ID", articleIds.contains(999999999));
    }
    
    @Test
    public void testLargeCategoryId() {
        testArticleCategory.setArticleId(1);
        testArticleCategory.setCategoryId(999999999);
        
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(999999999);
        assertTrue("应该能添加大数值的分类ID", articleIds.contains(1));
    }
} 