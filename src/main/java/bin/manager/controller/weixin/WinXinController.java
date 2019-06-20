package bin.manager.controller.weixin;

import bin.manager.common.winxin.AesException;
import bin.manager.common.winxin.SHA1;
import bin.manager.common.winxin.messagePoJo.*;
import bin.manager.common.winxin.util.InitMenu;
import bin.manager.common.winxin.util.MessageUtil;
import bin.manager.common.winxin.util.WinXinUploadUtil;
import bin.manager.common.winxin.util.WinXinUtil;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import static bin.manager.common.winxin.util.WinXinUtil.doGetStr;


/**
 * Created by lenovo on 2018/10/9.
 */
@RestController
@RequestMapping("/weixin")
public class WinXinController {
    @Value("${SERVICE_URL}")
    String SERVICE_URL;
    @Value("${UPLOAD_URL}")
    String UPLOAD_URL;
    @Value("${URL}")
    String URL;
    @Value("${MENU_URL}")
    String MENU_URL;
    @Value("${QUERY_MENU_URL}")
    String QUERY_MENU_URL;

    private final static String token = "imooc";


    @GetMapping("/get")
    public String get(String signature, String msg_signature, String timestamp, String nonce, String echostr) throws AesException {
        String content = SHA1.getSHA1(token, timestamp, nonce, echostr);
        System.out.println(content);
        if (content.equals(signature)) {
            return echostr;
        }
        return "";
    }

    @PostMapping("/get")
    public String postGet(HttpServletRequest request, HttpServletResponse response) throws AesException, DocumentException, IOException {
        //字符串转码：
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 从request中取得输入流
        InputStream inputStream = request.getInputStream();
        // 读取输入流 ，获取加密明文
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        String xmlStr = document.asXML();

        //个人微信订阅后加密信息
//        Object[] extract = XMLParse.extract(asXML);
//        //通过 appID和token构造对象
//        WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt(token, EncodingAESKey, appId);
//        //解密明文，转换对象
//        String xmlStr = wxBizMsgCrypt.decrypt(extract[1].toString());

        System.out.println("解密后明文xml: " + xmlStr);
        TextMessage message = new TextMessage();
        //xml转换为对象，适用于不变的xml
//        MessageUtil.xmlToObject(message, xmlStr);
        MessageUtil.xmlToMap(xmlStr);

        //接收到用户信息判断类似并返回信息
        String fanhuiMessage = fanhuiMessage(MessageUtil.map);
        System.out.println("返回的map：" + JSONObject.toJSONString(MessageUtil.map));
        System.out.println("返回的xml：" + fanhuiMessage);
        PrintWriter outputStream = response.getWriter();
        outputStream.print(fanhuiMessage);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return "";
    }

    /**
     * 接收到用户信息判断类似并返回信息
     */

    private String fanhuiMessage(Map<String, Object> objectMap) {
        InitMenu initMenu = new InitMenu();
        String content = "";
        JSONObject jsonObject = doGetStr(URL);
        Object access_token = jsonObject.get("access_token");
        String s = WinXinUploadUtil.uploadFile(UPLOAD_URL, access_token + "", MessageUtil.MESSAGE_IMAGE, SERVICE_URL + "/static/images/logo.png");
        JSONObject jsonObject1 = JSONObject.parseObject(s);
        Object media_id = jsonObject1.get("media_id");

        //从map中 获取值
        String toUserName = objectMap.get("ToUserName").toString();
        String FromUserName = objectMap.get("FromUserName").toString();
        String MsgType = objectMap.get("MsgType").toString();

        switch (MsgType) {
            case MessageUtil.MESSAGE_TEXT:
                switch (objectMap.get("Content").toString()) {
                    case "1":
                        //菜单创建
                        int menu = initMenu.createMenu(MENU_URL, access_token + "");
                        if (menu == 0) {
                            return "菜单创建成功";
                        } else {
                            return "菜单创建失败";
                        }
                    case "2":
                        Music music = new Music("jay", "无与伦比，为杰沉沦", SERVICE_URL + "/static/music/123.mp3", SERVICE_URL + "/static/music/123.mp3", media_id + "");
                        MusicMessage message2 = new MusicMessage(FromUserName, toUserName, System.currentTimeMillis() + "", MessageUtil.MESSAGE_MUSIC, music);
                        String objectToXml2 = MessageUtil.ObjectToXml(message2);
                        return objectToXml2;
                    case "3":
                        ImageMessage message3 = new ImageMessage(FromUserName, toUserName, System.currentTimeMillis() + "", MessageUtil.MESSAGE_IMAGE, new Image(media_id + ""));
                        String objectToXml3 = MessageUtil.ObjectToXml(message3);
                        return objectToXml3;
                    case "4":
                        ArrayList<News> messages = new ArrayList<>();
                        News news = new News("图文标题", "图文描述", SERVICE_URL + "/static/images/logo.png", "www.baidu.com");
                        messages.add(news);
                        NewsMessage message1 = new NewsMessage(FromUserName, toUserName, System.currentTimeMillis() + "", MessageUtil.MESSAGE_NEWS, 1, messages);
                        String objectToXml = MessageUtil.ObjectToXml(message1);
                        System.out.println(objectToXml);
                        return objectToXml;
                    case "5":
                        String s1 = initMenu.query_menu_url(QUERY_MENU_URL, access_token.toString());
                        content = s1;
                        break;
                    default:
                        content = "别闹！！";
                        break;
                }
                break;
            case MessageUtil.MESSAGE_EVENT:
                switch (objectMap.get("Event").toString()) {
                    case MessageUtil.MESSAGE_SUBSCRIBE:
                        content = "谢谢您的关注！！";
                        break;
                    case MessageUtil.MESSAGE_UNSUBSCRIBE:
                        content = "欢迎下次关注！！";
                        break;
                    case MessageUtil.MESSAGE_CLICK:
                        content = "点击click菜单";
                        break;
                    case MessageUtil.MESSAGE_SCANCODE_PUSH:
                        content = "扫码推送";
                        break;
                    case MessageUtil.MESSAGE_SSCANCODE_WAITMSG:
                        content = objectMap.get("ScanResult").toString();
                        break;
                    case MessageUtil.MESSAGE_LOCATION_SELECT:
                        content = objectMap.get("Label").toString();
                        break;
                }
                break;
            case MessageUtil.MESSAGE_IMAGE:
                switch (objectMap.get("Event").toString()) {
                    case MessageUtil.MESSAGE_PIC_SYSPHOTO:
                        content = "PicMd5Sum：" + objectMap.get("PicMd5Sum").toString() + "  PicUrl：" + objectMap.get("PicUrl").toString();
                        break;
                }
                break;
        }
        System.out.println("content------" + content);
        TextMessage message1 = new TextMessage(FromUserName, toUserName, System.currentTimeMillis() + "", "text", content);
        String objectToXml = MessageUtil.ObjectToXml(message1);
        return objectToXml;
    }
}
