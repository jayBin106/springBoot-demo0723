package bin.manager.common.winxin.util;

import bin.manager.common.winxin.messagePoJo.News;
import com.alibaba.fastjson.JSONObject;
import com.thoughtworks.xstream.XStream;
import org.dom4j.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/10/10.
 */
public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_MUSIC = "music";
    public static final String MESSAGE_THUMB = "thumb";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";
    public static final String MESSAGE_PIC_SYSPHOTO = "pic_sysphoto";
    public static final String MESSAGE_PIC_WEIXIN = "pic_weixin";
    public static final String MESSAGE_SCANCODE_PUSH = "scancode_push";
    public static final String MESSAGE_SSCANCODE_WAITMSG = "scancode_waitmsg";
    public static final String MESSAGE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
    public static final String MESSAGE_LOCATION_SELECT = "location_select";

    //存放解析xml的map
    public static Map<String, Object> map = new HashMap<>();

    /**
     * 对象转xml
     *
     * @param textMessage
     * @return
     */
    public static String ObjectToXml(Object textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        xStream.alias("item", new News().getClass());
        String s = xStream.toXML(textMessage);
        return s;
    }

    /**
     * xml转对象
     *
     * @param object
     * @param xmlStr
     * @return
     * @throws DocumentException
     */
    public static Object xmlToObject(Object object, String xmlStr) throws DocumentException {
        //明文xml解析内容
        Document document = DocumentHelper.parseText(xmlStr);
        Element element = document.getRootElement();
        List<Element> elements = element.elements();
        for (Element e : elements) {
            //利用反射生成对象
            ReflexUtil.invokeSet(object, e.getName(), e.getText());
        }
        System.out.println(object.toString());
        return object;
    }

    /**
     * xml转Map
     *
     * @param xmlStr
     * @throws DocumentException
     */
    public static void xmlToMap(String xmlStr) throws DocumentException {
        //明文xml解析内容
        Document document = DocumentHelper.parseText(xmlStr);
        Element element = document.getRootElement();
        listNodes(element);
        String string = JSONObject.toJSONString(map);
        System.out.println(string);
    }


    /**
     * 遍历当前节点元素下面的所有(元素的)子节点
     *
     * @param node
     */
    public static void listNodes(Element node) {
        System.out.println("当前节点的名称：：" + node.getName());
        // 获取当前节点的所有属性节点
        List<Attribute> list = node.attributes();
        // 遍历属性节点
        for (Attribute attr : list) {
            System.out.println(attr.getText() + "-----" + attr.getName()
                    + "---" + attr.getValue());
        }
        if (!(node.getTextTrim().equals(""))) {
            System.out.println("文本内容：：：：" + node.getText());
            map.put(node.getName(), node.getText());
        }
        // 当前节点下面子节点迭代器
        Iterator<Element> it = node.elementIterator();
        // 遍历
        while (it.hasNext()) {
            // 获取某个子节点对象
            Element e = it.next();
            // 对子节点进行遍历
            listNodes(e);
        }
    }
}
