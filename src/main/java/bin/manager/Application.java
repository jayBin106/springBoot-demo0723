package bin.manager;

import bin.manager.common.winxin.util.InitMenu;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@EnableRabbit
//@EnableRedisHttpSession
//spring-session的第二种方式
//@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 1200)
//@EnableJms
@EnableCaching  //启动缓存
@ServletComponentScan
@SpringBootApplication
@MapperScan("bin.manager.dao")
@ImportResource(locations = {"classpath:druid-bean.xml"})
//@ComponentScan(value = {"bin.manager.common.winxin.util"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
