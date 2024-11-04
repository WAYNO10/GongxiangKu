package junit.ArticleCategory;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import Dao.ArticleCategoryDao;
import java.util.List;

/**
 * @author 莫涵越
 */
public class GetArticleIdsFromNonExistentCategoryTest {
    
    private ArticleCategoryDao articleCategoryDao;
    
    @Before
    public void setUp() {
        articleCategoryDao = new ArticleCategoryDao();
    }
    
    @Test
    public void testGetArticleIdsFromNonExistentCategory() {
        List<Integer> articleIds = articleCategoryDao.getArticleIdsByCategoryId(999); // 使用一个不太可能存在的分类ID
        assertNotNull("返回的列表不应为null", articleIds);
        assertTrue("不存在的分类应该返回空列表", articleIds.isEmpty());
    }
} 