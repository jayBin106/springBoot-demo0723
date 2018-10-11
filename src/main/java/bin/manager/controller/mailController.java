package bin.manager.controller;

import bin.manager.common.mail.RoncooJavaMailComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by lenovo on 2018/7/25.
 */
@Controller
@RequestMapping("/api")
public class mailController {
    @Autowired
    RoncooJavaMailComponent component;

    @RequestMapping("/")
    public String index() {
        return "mails";
    }

    @ResponseBody
    @RequestMapping(value = "/mail")
    public String mail(String email) {
        component.sendMail(email);
        return "success";
    }
}
