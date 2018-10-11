package bin.manager.common.druid;

import com.alibaba.druid.support.http.StatViewServlet;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/druid/*"}, initParams = {@WebInitParam(name = "loginUsername", value = "root"), @WebInitParam(name = "loginPassword", value = "root")})
public class DruidStatViewServlet extends StatViewServlet {
    private static final long serialVersionUID = 1L;

} 
 