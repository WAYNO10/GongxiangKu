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
public class AddDuplicateArticleCategoryTest {
    
    private ArticleCategoryDao articleCategoryDao;
    private ArticleCategory testArticleCategory;
    
    @Before
    public void setUp() {
        articleCategoryDao = new ArticleCategoryDao();
        testArticleCategory = new ArticleCategory();
        testArticleCategory.setArticleId(1);
        testArticleCategory.setCategoryId(1);
        // 先添加一次
        articleCategoryDao.addArticleCategory(testArticleCategory);
    }
    
    @Test
    public void testAddDuplicateArticleCategory() {
        // 尝试再次添加相同的分类
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        // 验证结果 - 应该只有一条记录
        List<ArticleCategory> categories = articleCategoryDao.getAllArticleCategories();
        int count = 0;
        for (ArticleCategory ac : categories) {
            if (ac.getArticleId() == testArticleCategory.getArticleId() 
                && ac.getCategoryId() == testArticleCategory.getCategoryId()) {
                count++;
            }
        }
        assertEquals("应该只有一条记录", 1, count);
    }
    
    @After
    public void tearDown() {
        if (articleCategoryDao != null && testArticleCategory != null) {
            articleCategoryDao.removeArticleCategory(testArticleCategory);
        }
    }
} 