package bin.manager.common.winxin.messagePoJo;

/**
 * Created by lenovo on 2018/10/12.
 */
public class ImageMessage extends Message {
    private Image Image;

    public ImageMessage() {
    }

    public ImageMessage(bin.manager.common.winxin.messagePoJo.Image image) {
        Image = image;
    }

    public ImageMessage(String toUserName, String fromUserName, String createTime, String msgType, bin.manager.common.winxin.messagePoJo.Image image) {
        super(toUserName, fromUserName, createTime, msgType);
        Image = image;
    }

    public bin.manager.common.winxin.messagePoJo.Image getImage() {
        return Image;
    }

    public void setImage(bin.manager.common.winxin.messagePoJo.Image image) {
        Image = image;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "Image=" + Image +
                '}';
    }
}
