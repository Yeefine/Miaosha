package com.myproject.miaosha.controller;

import com.myproject.miaosha.domain.User;
import com.myproject.miaosha.redis.RedisService;
import com.myproject.miaosha.redis.UserKey;
import com.myproject.miaosha.result.CodeMsg;
import com.myproject.miaosha.result.Result;
import com.myproject.miaosha.service.MiaoshaUserService;
import com.myproject.miaosha.service.UserService;
import com.myproject.miaosha.util.ValidatorUtil;
import com.myproject.miaosha.vo.LoginVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private MiaoshaUserService userService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }


    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String>  doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        // 登录
        String token = userService.login(response, loginVo);

        return Result.success(token);

    }


}
