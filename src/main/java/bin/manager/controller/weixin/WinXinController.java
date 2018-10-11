package bin.manager.controller.weixin;

import bin.manager.common.winxin.AesException;
import bin.manager.common.winxin.SHA1;
import bin.manager.common.winxin.messagePoJo.ImageMessage;
import bin.manager.common.winxin.messagePoJo.MusicMessage;
import bin.manager.common.winxin.messagePoJo.NewsMessage;
import bin.manager.common.winxin.messagePoJo.TextMessage;
import bin.manager.common.winxin.util.InitMenu;
import bin.manager.common.winxin.util.MessageUtil;
import bin.manager.common.winxin.util.WinXinUploadUtil;
import bin.manager.common.winxin.util.WinXinUtil;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
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


/**
 * Created by lenovo on 2018/10/9.
 */
@RestController
@RequestMapping("/weixin")
public class WinXinController {
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
        TextMessage textMessage = new TextMessage();
        MessageUtil.xmlToObject(textMessage, xmlStr);
        //接收到用户信息判断类似并返回信息
        String fanhuiMessage = fanhuiMessage(textMessage);
        System.out.println("返回的xml" + fanhuiMessage);
        PrintWriter outputStream = response.getWriter();
        outputStream.print(fanhuiMessage);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        return "";
    }

    /**
     * 接收到用户信息判断类似并返回信息
     *
     * @param textMessage
     */

    private String fanhuiMessage(TextMessage textMessage) {
        String content = "";
        JSONObject jsonObject = WinXinUtil.doGetStr(WinXinUtil.URL);
        Object access_token = jsonObject.get("access_token");
        String s = WinXinUploadUtil.uploadFile(WinXinUtil.UPLOAD_URL, access_token + "", MessageUtil.MESSAGE_IMAGE, WinXinUtil.SERVICE_URL + "/static/images/logo.png");
        JSONObject jsonObject1 = JSONObject.parseObject(s);
        Object media_id = jsonObject1.get("media_id");
        switch (textMessage.getMsgType()) {
            case MessageUtil.MESSAGE_TEXT:
                switch (textMessage.getContent()) {
                    case "1":
                        //菜单创建
                        int menu = InitMenu.createMenu(access_token + "");
                        if (menu == 0) {
                            return "菜单创建成功";
                        } else {
                            return "菜单创建失败";
                        }
                    case "2":
                        MusicMessage musicMessage = new MusicMessage("jay", "无与伦比，为杰沉沦", WinXinUtil.SERVICE_URL + "/static/music/123.mp3", WinXinUtil.SERVICE_URL + "/static/music/123.mp3", media_id + "");
                        TextMessage textMessage2 = new TextMessage(textMessage.getFromUserName(), textMessage.getToUserName(), new Date().getTime() + "", MessageUtil.MESSAGE_MUSIC, musicMessage);
                        String objectToXml2 = MessageUtil.ObjectToXml(textMessage2);
                        return objectToXml2;
                    case "3":
                        TextMessage textMessage3 = new TextMessage(textMessage.getFromUserName(), textMessage.getToUserName(), new Date().getTime() + "", MessageUtil.MESSAGE_IMAGE, new ImageMessage(media_id + ""));
                        String objectToXml3 = MessageUtil.ObjectToXml(textMessage3);
                        return objectToXml3;
                    case "4":
                        ArrayList<NewsMessage> messages = new ArrayList<>();
                        NewsMessage newsMessage = new NewsMessage("图文标题", "图文描述", WinXinUtil.SERVICE_URL + "/static/images/logo.png", "www.baidu.com");
                        messages.add(newsMessage);
                        TextMessage textMessage1 = new TextMessage(textMessage.getFromUserName(), textMessage.getToUserName(), new Date().getTime() + "", MessageUtil.MESSAGE_NEWS, 1, messages);
                        String objectToXml = MessageUtil.ObjectToXml(textMessage1);
                        System.out.println(objectToXml);
                        return objectToXml;
                    default:
                        content = "别闹！！";
                        break;
                }
                break;
            case MessageUtil.MESSAGE_EVENT:
                switch (textMessage.getEvent()) {
                    case MessageUtil.MESSAGE_SUBSCRIBE:
                        content = "谢谢您的关注！！";
                        break;
                    case MessageUtil.MESSAGE_UNSUBSCRIBE:
                        content = "欢迎下次关注！！";
                        break;
                }
                break;
        }
        TextMessage textMessage1 = new TextMessage(textMessage.getFromUserName(), textMessage.getToUserName(), new Date().getTime() + "", "text", content);
        String objectToXml = MessageUtil.ObjectToXml(textMessage1);
        return objectToXml;
    }
}
