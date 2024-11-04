package service;

import Dao.ArticleCategoryDao;
import context.UserContext;
import entity.Article;
import entity.ArticleCategory;
import entity.User;
import entity.UserRoleEnum;
import exception.BusinessException;
import Dao.ArticleDao;
import exception.ErrorCode;

import java.util.List;

public class ArticleCategoryService {
    private final static ArticleDao ARTICLE_DAO = new ArticleDao();
    private final static ArticleCategoryDao ARTICLE_CATEGORY_DAO = new ArticleCategoryDao();


    /**
     * 添加文章分类关系 （仅管理员）
     * @param articleCategory
     */
    public static void addArticleCategory(ArticleCategory articleCategory){
        User currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        if (!UserService.hasRole(UserRoleEnum.ADMIN)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"你没有该权限");
        }
        int articleId = articleCategory.getArticleId();
        int categoryId = articleCategory.getCategoryId();
        if (articleId == 0 || categoryId == 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"文章id和分类id错误");
        }
        ARTICLE_CATEGORY_DAO.addArticleCategory(articleCategory);
    }

    /**
     * 返回某个分类下所有文章数据
     * @param categoryId
     * @return
     */
    public static List<Article> getArticleByCategoryId(Integer categoryId){
        List<Integer> articleIds = ARTICLE_CATEGORY_DAO.getArticleIdsByCategoryId(categoryId);
        List<Article> articles = ARTICLE_DAO.getArticlesByIds(articleIds);
        return articles;
    }


    /**
     * 将某个文章从某个分类中移除 （仅管理员）
     * @param articleCategory
     * @return
     */
    public static boolean removeArticleFromCategory(ArticleCategory articleCategory){
        User currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        //如果文章创作者不是你 并且你不是管理员 那么无法修改文章
        if (!UserService.hasRole(UserRoleEnum.ADMIN)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"你没有该文章权限");
        }
        return ARTICLE_CATEGORY_DAO.removeArticleCategory(articleCategory);
    }

    /**
     * 删除某文章在分类中的所有数据 （删除文章的下游接口）
     * @param articleId
     * @return
     */
    public static boolean removeArticleAllFromCategory(Integer articleId){
        return ARTICLE_CATEGORY_DAO.removeArticleCategoryByArticleId(articleId);
    }
    
        /**
     * 查询所有文章分类关联数据
     * @return
     */
    public static List<ArticleCategory> getAllArticleCategory(){
        return ARTICLE_CATEGORY_DAO.getAllArticleCategories();
    }
}
