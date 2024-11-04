package service;

import Dao.ArticleDao;
import context.UserContext;
import entity.Article;
import entity.User;
import exception.BusinessException;
import exception.ErrorCode;
import entity.UserRoleEnum;
import Dao.ArticleCategoryDao;
import java.util.List;

public class ArticleService {
    private final static ArticleDao ARTICLE_DAO = new ArticleDao();
    private final static ArticleCategoryDao ARTICLE_CATEGORY_DAO = new ArticleCategoryDao();

    /**
     * 添加文章
     * @param article 文章
     * @return articleId 文章id
     */
    public static int addArticle(Article article){
        User currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        article.setUserId(currentUser.getId());
        return ARTICLE_DAO.insertArticle(article);
    }

    public static Article getArticleByTitle(String title){
        return ARTICLE_DAO.getArticleByTitle(title);
    }
    
    /**
     * 获取登录用户的所有文章
     * @return articles 登录用户的文章列表
     */
    public static List<Article> getMyArticle(){
        User currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        List<Article> articles = ARTICLE_DAO.getArticlesByUserId(currentUser.getId());
        return articles;
    }

    /**
     * 获取所有文章
     * @return
     */
    public static List<Article> getAllArticles(){
        List<Article> allArticles = ARTICLE_DAO.getAllArticles();
        return allArticles;
    }
    
    /**
     * 修改文章 （仅文章创作者 和 管理员）
     * @param article
     * @return
     */
    public static boolean editArticle(Article article){
        User currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        //如果文章创作者不是你 并且你不是管理员 那么无法修改文章
        if (currentUser.getId() != article.getUserId() && !UserService.hasRole(UserRoleEnum.ADMIN)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"你没有该文章权限");
        }
        return ARTICLE_DAO.updateArticle(article);
    }
    
    /**
     * 删除文章
     */
    public static boolean deleteArticle(int id){
        User currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        Article article = ARTICLE_DAO.getArticleById(id);
        //如果文章创作者不是你 并且你不是管理员 那么无法修改文章
        if (currentUser.getId() != article.getUserId() && !UserService.hasRole(UserRoleEnum.ADMIN)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR,"你没有该文章权限");
        }
        boolean deletedArticle = ARTICLE_DAO.deleteArticle(id);
        boolean deletedArticleCategory = ARTICLE_CATEGORY_DAO.removeArticleCategoryByArticleId(id);
        if (deletedArticleCategory && deletedArticle)
            return true;
        return false;
    }

}
