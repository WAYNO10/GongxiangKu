package entity;


public class ArticleCategory {
    public ArticleCategory(){

    }


    private int id;
    private int articleId;
    private int categoryId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", categoryId=" + categoryId +
                '}';
    }
}
