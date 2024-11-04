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
public class CategoryValidationTest {
    
    private ArticleCategoryDao articleCategoryDao;
    private ArticleCategory testArticleCategory;
    
    @Before
    public void setUp() {
        articleCategoryDao = new ArticleCategoryDao();
        testArticleCategory = new ArticleCategory();
    }
    
    @Test
    public void testMaxCategoryId() {
        testArticleCategory.setArticleId(1);
        testArticleCategory.setCategoryId(Integer.MAX_VALUE);
        
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(Integer.MAX_VALUE);
        assertTrue("应该能添加最大整数值的分类ID", articleIds.contains(1));
    }
    
    @Test
    public void testMaxArticleId() {
        testArticleCategory.setArticleId(Integer.MAX_VALUE);
        testArticleCategory.setCategoryId(1);
        
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(1);
        assertTrue("应该能添加最大整数值的文章ID", articleIds.contains(Integer.MAX_VALUE));
    }
} 