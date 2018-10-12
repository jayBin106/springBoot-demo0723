package bin.manager.common.winxin.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by lenovo on 2018/10/11.
 */
public class WinXinUtil {
    public static final String SERVICE_URL = "http://zt2zbd.natappfree.cc";
    public static final String appId = "wx4279d2627167c973";
    public static final String appsecret = "2098439bf431335ae2bdc71211f13856";
    public static final String EncodingAESKey = "cfcVbGFRER2K5lB4LdTXuCPb03qVm5NK03ifWDDyEcX";
    //token获取
    public static final String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appsecret;
    //图片上传
    public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    //自定义菜单
    public static final String MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
    //查询菜单
    public static final String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";

    /**
     * get请求
     *
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
           HttpResponse execute = httpClient.execute(httpGet);
            HttpEntity entity = execute.getEntity();
            if (entity != null) {
                String string = EntityUtils.toString(entity, "utf-8");
                JSONObject jsonObject = JSONObject.parseObject(string);
                return jsonObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求
     *
     * @param url
     * @return
     */
    public static   JSONObject doPostStr(String url,String outStr) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
        try {
            HttpResponse execute = httpClient.execute(httpPost);
            HttpEntity entity = execute.getEntity();
            if (entity != null) {
                String string = EntityUtils.toString(entity, "utf-8");
                JSONObject jsonObject = JSONObject.parseObject(string);
                return jsonObject;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
