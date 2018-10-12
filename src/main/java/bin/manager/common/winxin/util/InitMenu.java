package bin.manager.common.winxin.util;

import bin.manager.common.winxin.buttonPoJo.Button;
import bin.manager.common.winxin.buttonPoJo.ClickButton;
import bin.manager.common.winxin.buttonPoJo.Menu;
import bin.manager.common.winxin.buttonPoJo.ViewButton;
import com.alibaba.fastjson.JSONObject;

/**
 * 菜单初始化
 * Created by lenovo on 2018/10/11.
 */
public class InitMenu {
    public static String init() {
        ClickButton clickButton = new ClickButton("click菜单", "click", null, "1");
        //url地址，必须要加上http://
        ViewButton clickButton2 = new ViewButton("view菜单", "view", null, "http://www.baidu.com/");
        ClickButton clickButton3 = new ClickButton("拍照", "pic_sysphoto", null, "2");
        ClickButton clickButton4 = new ClickButton("地理位置", "location_select", null, "3");
        ClickButton clickButton5 = new ClickButton("扫码", "scancode_waitmsg", null, "4");
        Menu menu = new Menu();

        Button button = new Button();
        button.setName("菜单");
        button.setSub_button(new Button[]{clickButton3, clickButton4, clickButton5});

        menu.setButton(new Button[]{clickButton, clickButton2, button});
        String menuString = JSONObject.toJSONString(menu);
        return menuString;
    }

    /**
     * 生成菜单
     *
     * @param token
     * @return
     */
    public static int createMenu(String token) {
        String menuUrl = WinXinUtil.MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = WinXinUtil.doPostStr(menuUrl, init());
        if (jsonObject != null) {
            int errcode = jsonObject.getIntValue("errcode");
            return errcode;
        }
        return 1;
    }

    /**
     * 查询 菜单
     *
     * @param token
     * @return
     */
    public static String query_menu_url(String token) {
        String menuUrl = WinXinUtil.QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
        JSONObject jsonObject = WinXinUtil.doGetStr(menuUrl);
        if (jsonObject != null) {
            String string = JSONObject.toJSONString(jsonObject);
            return string;
        }
        return "";
    }
}
