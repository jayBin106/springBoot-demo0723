package bin.manager.controller.weixin;

import bin.manager.common.winxin.util.WinXinUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

import static bin.manager.common.winxin.util.WinXinUtil.doGetStr;

/**
 * Created by lenovo on 2018/10/13.
 */
@Controller
@RequestMapping("/weixin")
public class WinXinLogin {
    @Value("${SERVICE_URL}")
    String SERVICE_URL;
    @Value("${GET_CODE_URL}")
    String GET_CODE_URL;
    @Value("${GET_ACCESS_TOKEN_URL}")
    String GET_ACCESS_TOKEN_URL;
    @Value("${GET_USERINFO_BY_OPENID_URL}")
    String GET_USERINFO_BY_OPENID_URL;

    @RequestMapping("/index")
    public String index() {
        return "weixinIndex";
    }

    @RequestMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String backUrl = SERVICE_URL + "/weixin/callBack";
        String encode = URLEncoder.encode(backUrl, "UTF-8");
        String scope = "snsapi_userinfo";
        //替换参数
        String getCodeUrl = GET_CODE_URL.replace("REDIRECT_URI", encode).replace("SCOPE", scope);
        //重定向
        response.sendRedirect(getCodeUrl);
    }

    /**
     * 获取code后，请求以下链接获取access_token
     *
     * @param code
     */
    @RequestMapping("/callBack")
    public String callBack(String code, Model model) {
        String getAccessTokenUrl = GET_ACCESS_TOKEN_URL.replace("CODE", code);
        JSONObject jsonObject = doGetStr(getAccessTokenUrl);
        System.out.println("CallBack 数据：" + jsonObject);
        System.out.println("开始获取用户信息");
        String openid = jsonObject.getString("openid");
        String access_token = jsonObject.getString("access_token");
        String getuserinfourl = GET_USERINFO_BY_OPENID_URL.replace("ACCESS_TOKEN", access_token).replace("OPENID", openid);
        JSONObject userINfo = doGetStr(getuserinfourl);
        System.out.println(" userINfo数据：" + userINfo);
        model.addAttribute("user", userINfo);
        return "userInfo";
    }
}
