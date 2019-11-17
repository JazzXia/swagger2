package com.qtatelier.swagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.w3c.dom.DocumentType;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Documentation;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.swing.text.Document;
import java.util.ArrayList;

/**
 * @author JazzXia
 * @create 2019-11-17-9:55
 * @email jazzxiaw@qq.com
 * @since 2019
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //配置多个分组，每个分组一个docket
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("A");
    }

    @Bean
    public Docket docket2(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("B");
    }

    @Bean
    public Docket docket3(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("C");
    }

    //配置swagger的Docket的bean实例
    @Bean
    public Docket docket(Environment environment){

        //设置要显示的Swagger环境
        Profiles profiles = Profiles.of("dev","test");

        //通过enviroment.acceptsProfiles判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);



        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("WEI's test")
                //是否启用swagger，false为否，true为是
                .enable(flag)

                .select()
                //RequestHandlerSelectors,配置要扫描接口的方式
                //basePackage指定要扫描的包
                //any扫描全部
                //none不扫描
                //withClassAnnotation:扫描类上的注解，参数是注解的反射对象(@RestController.class)
                //withMethodAnnotation:扫描方法上的注解,参数是注解的反射对象(@GetMapping.class)
                .apis(RequestHandlerSelectors.basePackage("com.qtatelier.swagger2.controller"))
                //paths()过滤请求路径
                //为paths的参数之一PathSelectors.ant("/JazzXia/**")过滤对应的路径
                .paths(PathSelectors.any())
                .build();//build是工厂
    }


    private ApiInfo apiInfo(){

        //作者信息
        Contact contact = new Contact("JazzXia","https://www.qtatelier.com/","1104841692@qq.com");

        return new ApiInfo(
                "威爷的SwaggerAPI文档",
                "Swagger的测试使用",
                "v1.0",
                "https://www.qtatelier.com",
                 contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                 new ArrayList()
        );
    }

}
