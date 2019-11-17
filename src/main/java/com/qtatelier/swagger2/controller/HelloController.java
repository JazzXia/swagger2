package com.qtatelier.swagger2.controller;

import com.qtatelier.swagger2.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JazzXia
 * @create 2019-11-17-9:52
 * @email jazzxiaw@qq.com
 * @since 2019
 */

@Api(value = "用户模块",tags = "用户模块的接口信息")
@RestController
public class HelloController {


    @ApiOperation(value = "问好",notes = "问好的具体路径")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    //只要我们的接口中，返回值中存在实体类，他就会被扫描到Swagger中
    @ApiOperation(value = "获取用户列表",notes = "获取所有用户的列表")
    @PostMapping("/user")
    public User user(){
        return new User();
    }
}
