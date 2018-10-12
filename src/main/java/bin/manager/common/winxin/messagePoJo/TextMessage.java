package bin.manager.common.winxin.messagePoJo;

/**
 * Created by lenovo on 2018/10/12.
 */
public class TextMessage extends Message {
    private String Content;

    public TextMessage() {
    }

    public TextMessage(String content) {
        Content = content;
    }

    public TextMessage(String toUserName, String fromUserName, String createTime, String msgType, String content) {
        super(toUserName, fromUserName, createTime, msgType);
        Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
