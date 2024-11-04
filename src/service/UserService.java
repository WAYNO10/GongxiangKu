package service;

import Dao.UserDao;
import entity.User;
import context.UserContext;
import exception.BusinessException;
import exception.ErrorCode;
import entity.UserRoleEnum;

import java.util.List;

public class UserService {
    private final static UserDao USER_DAO = new UserDao();

    
    /**
     * 登录 并将用户信息存入全局
     * @param username 用户名
     * @param password 密码
     * @return true 登录成功
     */
    public static boolean login(String username ,String password){
        // 查询用户
        List<User> users = USER_DAO.findUser(username);
        User user = users.get(0);

        if (!user.getPassword().equals(password)){
            return false;
        }
        //将用户信息存入全局
        UserContext.getInstance().setCurrentUser(user);
        return true;
    }
    
    public static User getUserById(Integer id){
        return USER_DAO.findUserById(id);
    }

    /**
     * 新增用户 (仅管理员可用)
     * @param user 用户
     * @return 新增成功 true
     */
    public static boolean addUser(User user){
        if (!hasRole(UserRoleEnum.ADMIN)){
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        return USER_DAO.addUser(user);
    }

    /**
     * 用户鉴权
     * @param needRole 需要的权限  admin 管理员 user 用户
     * @return true 当前用户有权限 false 当前用户没有权限
     */
    public static boolean hasRole(UserRoleEnum needRole){
        User currentUser = UserContext.getInstance().getCurrentUser();
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        String userRole = currentUser.getUserRole();
        //如果被封禁 直接无权限
        if (userRole.equals(UserRoleEnum.BAN.getValue())){
            return false;
        }

        if(userRole.equals(needRole.getValue())){
            return true;
        }
        //管理员最高权限
        return userRole.equals(UserRoleEnum.ADMIN.getValue());
    }
}
