package bin.manager.common.winxin.buttonPoJo;

/**
 * Created by lenovo on 2018/10/11.
 */
public class Menu {
    private Button[] button;

    public Menu() {
    }

    public Menu(Button[] button) {
        this.button = button;
    }

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }
}
