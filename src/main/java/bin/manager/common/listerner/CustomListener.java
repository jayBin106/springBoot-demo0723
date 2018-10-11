package bin.manager.common.listerner;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 自定义 listener
 *
 * @author wujing
 */
@WebListener
public class CustomListener implements ServletContextListener {

    /**
     * 项目启动时
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("contextInitialized-111111111111111111");
    }

    /**
     * 服务停止时
     * @param sce
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("contextDestroyed--222222222222222222");
    }

} 
 