package service;

import Dao.CategoryDao;
import entity.Category;
import entity.UserRoleEnum;
import exception.BusinessException;
import exception.ErrorCode;
import java.util.List;

public class CategoryService {
    public static final CategoryDao CATEGORY_DAO = new CategoryDao();

    /**
     * 创建分类 (仅管理员)
     * @param category
     * @return
     */
    public static int createCategory(Category category){
        //鉴权
        if (!UserService.hasRole(UserRoleEnum.ADMIN)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return CATEGORY_DAO.addCategory(category);
    }

    /**
     * 获取所有分类
     * @return
     */
    public static List<Category> getAllCategory(){
        return CATEGORY_DAO.getAllCategories();
    }
    
    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    public static Category getCategoryById(Integer id){
        return CATEGORY_DAO.getCategoryById(id);
    }
    
        /**
     * 
     * 根据分类名称查询分类
     * @param categoryName
     * @return
     */
    public static Category getCategoryByName(String categoryName){
        return CATEGORY_DAO.getCategoryByName(categoryName);
    }
}
