package bin.manager.common.winxin.buttonPoJo;

/**
 * Created by lenovo on 2018/10/11.
 */
public class ViewButton extends Button {
    private String url;

    public ViewButton() {
    }

    public ViewButton(String url) {
        this.url = url;
    }

    public ViewButton(String name, String type, Button[] sub_button, String url) {
        super(name, type, sub_button);
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
