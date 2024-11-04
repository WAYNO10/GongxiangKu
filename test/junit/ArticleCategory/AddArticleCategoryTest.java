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
public class AddArticleCategoryTest {
    
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
    public void testAddArticleCategory() {
        // 测试添加文章分类
        articleCategoryDao.addArticleCategory(testArticleCategory);
        
        // 验证添加是否成功
        List<ArticleCategory> categories = articleCategoryDao.getAllArticleCategories();
        boolean found = false;
        for (ArticleCategory ac : categories) {
            if (ac.getArticleId() == testArticleCategory.getArticleId() 
                && ac.getCategoryId() == testArticleCategory.getCategoryId()) {
                found = true;
                break;
            }
        }
        assertTrue("文章分类应该被成功添加", found);
    }
    
    @After
    public void tearDown() {
        // 清理测试数据
        if (articleCategoryDao != null && testArticleCategory != null) {
            articleCategoryDao.removeArticleCategory(testArticleCategory);
        }
    }
} 