/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import java.sql.Timestamp;

/**
 *
 * @author 王陈宇科
 */
public class Article {
    private int id;
    private String title;
    private String content;
    private int userId;
    private Timestamp updateTime;
    private Timestamp createTime;

    // 构造函数
    public Article() {}

    public Article(int id, String title, String content, int userId, Timestamp updateTime, Timestamp createTime) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    // Getter 和 Setter 方法
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
