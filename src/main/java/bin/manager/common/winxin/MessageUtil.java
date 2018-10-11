package bin.manager.common.winxin;

import com.thoughtworks.xstream.XStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.List;

/**
 * Created by lenovo on 2018/10/10.
 */
public class MessageUtil {
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_NEWS = "news";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";
    public static final String MESSAGE_VIDEO = "video";
    public static final String MESSAGE_LINK = "link";
    public static final String MESSAGE_LOCATION = "location";
    public static final String MESSAGE_EVENT = "event";
    public static final String MESSAGE_SUBSCRIBE = "subscribe";
    public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
    public static final String MESSAGE_CLICK = "CLICK";
    public static final String MESSAGE_VIEW = "VIEW";


    /**
     * 对象转xml
     *
     * @param textMessage
     * @return
     */
    public static String ObjectToXml(Object textMessage) {
        XStream xStream = new XStream();
        xStream.alias("xml", textMessage.getClass());
        xStream.alias("item", new NewsMessage().getClass());
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
        Document document2 = DocumentHelper.parseText(xmlStr);
        org.dom4j.Element rootElement = document2.getRootElement();
        List<Element> elements = rootElement.elements();
        for (org.dom4j.Element element : elements) {
            ReflexUtil.invokeSet(object, element.getName(), element.getText());
        }
        System.out.println(object.toString());
        return object;
    }
}
