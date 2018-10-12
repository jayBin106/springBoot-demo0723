package bin.manager.common.winxin.messagePoJo;

import java.util.List;

/**
 * Created by lenovo on 2018/10/12.
 */
public class NewsMessage extends Message {
    private int ArticleCount;
    private List<News> Articles;

    public NewsMessage() {
    }

    public NewsMessage(int articleCount, List<News> articles) {
        ArticleCount = articleCount;
        Articles = articles;
    }

    public NewsMessage(String toUserName, String fromUserName, String createTime, String msgType, int articleCount, List<News> articles) {
        super(toUserName, fromUserName, createTime, msgType);
        ArticleCount = articleCount;
        Articles = articles;
    }

    public int getArticleCount() {
        return ArticleCount;
    }

    public void setArticleCount(int articleCount) {
        ArticleCount = articleCount;
    }

    public List<News> getArticles() {
        return Articles;
    }

    public void setArticles(List<News> articles) {
        Articles = articles;
    }
}
