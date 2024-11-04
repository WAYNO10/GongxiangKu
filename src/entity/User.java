package entity;

public class User {
    private Integer id;

    private String userName;

    private String password;

    private String userRole;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    /**
     * 判读用户权限
     * @param userRole
     * @return
     */
    public boolean hasPermission(String userRole) {
        if (this.userRole.equals(userRole)){
            return true;
        }
        if (userRole.equals(UserRoleEnum.USER.getValue()) && this.userRole.equals(UserRoleEnum.ADMIN.getValue())){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }
}
