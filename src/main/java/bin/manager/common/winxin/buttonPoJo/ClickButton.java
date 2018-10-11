package bin.manager.common.winxin.buttonPoJo;

/**
 * Created by lenovo on 2018/10/11.
 */
public class ClickButton extends Button {
    private String key;

    public ClickButton() {
    }

    public ClickButton(String key) {
        this.key = key;
    }

    public ClickButton(String name, String type, Button[] sub_button, String key) {
        super(name, type, sub_button);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
