/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package databasetest;

import java.util.List;
import Dao.UserDao;
import constant.UserRoleEnum;
import entity.User;

/**
 *
 * @author 王陈宇科
 */
public class UserTest {
    public static void main(String[] args) {
            UserDao userDao = new UserDao();

            // 创建用户对象
            User newUser = new User();
            newUser.setUserName("keke");
            newUser.setPassword("123456");
            newUser.setUserRole(UserRoleEnum.ADMIN.getValue());
//            
//            System.out.println(newUser.toString());
//            
//            // 添加用户
//            boolean added = userDao.addUser(newUser);
//            System.out.println("User added: " + added);

            // 查询用户
            List<User> foundUsers = userDao.findUser("keke");
            for (User user : foundUsers) {
                System.out.println("Found User: " + user.toString());
            }

//            // 更新用户信息
//            newUser.setPassword("12345");
//            boolean updated = userDao.updateUser(newUser);
//            System.out.println("User updated: " + updated);

//            // 查询所有用户
//            List<User> allUsers = userDao.findAllUsers();
//            for (User user : allUsers) {
//                System.out.println("All Users: " + user.getUserName());
//            }
            

//            // 删除用户
//            boolean deleted = userDao.deleteUser("keke");
//            System.out.println("User deleted: " + deleted);
        }
}
