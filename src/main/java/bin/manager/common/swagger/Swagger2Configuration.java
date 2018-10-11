package bin.manager.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

/**
 * SwaggerConfig
 */
@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    /**
     * @return
     */
    @Bean
    public Docket accessToken() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("bin.manager.controller")) // 拦截的包路径
                .paths(regex("/api/.*"))// 拦截的接口路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }
    @Bean
    public Docket ehcacheToken() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("ehcache")// 定义组
                .select() // 选择那些路径和 api 会生成 document
                .apis(RequestHandlerSelectors.basePackage("bin.manager.controller")) // 拦截的包路径
                .paths(regex("/ehcache/.*"))// 拦截的接口路径
                .build() // 创建
                .apiInfo(apiInfo()); // 配置说明
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()//
                .title("龙果学院")// 标题
                .description("spring boot 全集")// 描述
                .termsOfServiceUrl("http://www.roncoo.com")
                .contact(new Contact("wujing", "http://www.roncoo.com", "297115770@qq.com"))// 联系
                // .license("Apache License Version 2.0")// 开源协议
// .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")// 地址
                .version("1.0")// 版本
                .build();

    }
} 