package bin.manager.common.winxin.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by lenovo on 2018/10/11.
 */
@Component
public class WinXinUtil {
    @Value("${SERVICE_URL}")
    String SERVICE_URL;
    @Value("${appId}")
    public static String appId;
    @Value("${appsecret}")
    public static String appsecret;
    @Value("${EncodingAESKey}")
    String EncodingAESKey;


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
    public static JSONObject doPostStr(String url, String outStr) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(outStr, "UTF-8"));
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
