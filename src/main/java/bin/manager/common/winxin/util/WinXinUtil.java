package bin.manager.common.winxin.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
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
    public static final String URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appsecret;
    public static final String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

    public static JSONObject doGetStr(String url) {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse execute = defaultHttpClient.execute(httpGet);
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
