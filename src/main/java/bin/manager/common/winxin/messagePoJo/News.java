package bin.manager.common.winxin.messagePoJo;

/**
 * Created by lenovo on 2018/10/10.
 */
public class News {
    private String Title;
    private String Description;
    private String PicUrl;
    private String Url;

    public News() {
    }

    public News(String title, String description, String picUrl, String url) {
        Title = title;
        Description = description;
        PicUrl = picUrl;
        Url = url;
    }

    @Override
    public String toString() {
        return "News{" +
                "Title='" + Title + '\'' +
                ", Description='" + Description + '\'' +
                ", PicUrl='" + PicUrl + '\'' +
                ", Url='" + Url + '\'' +
                '}';
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
