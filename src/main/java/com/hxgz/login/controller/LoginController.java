package com.hxgz.login.controller;

import com.hxgz.config.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Api(tags = "loginController",description = "swagger登录测试")
public class LoginController {

    @RequestMapping(value="loginPass.do",method = RequestMethod.POST )
    @ApiOperation("密码登陆(手机号,密码)")
    public Result loginPass(@ApiParam("手机号码") @RequestParam String phone,
                            @ApiParam("密码") @RequestParam String password){
        return new Result();
    }
}
