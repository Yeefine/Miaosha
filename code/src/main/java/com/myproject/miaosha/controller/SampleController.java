package com.myproject.miaosha.controller;

import com.myproject.miaosha.domain.MiaoshaUser;
import com.myproject.miaosha.domain.User;
import com.myproject.miaosha.rabbitmq.MQSender;
import com.myproject.miaosha.rabbitmq.MiaoshaMessage;
import com.myproject.miaosha.redis.KeyPrefix;
import com.myproject.miaosha.redis.RedisService;
import com.myproject.miaosha.redis.UserKey;
import com.myproject.miaosha.result.CodeMsg;
import com.myproject.miaosha.result.Result;
import com.myproject.miaosha.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class SampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MQSender sender;

    /*
    @RequestMapping("/mq/header")
    @ResponseBody
    public Result<String> header() {
        sender.sendHeader("hello, rabbitmq");
        return Result.success("hello, rabbitmq");
    }


    @RequestMapping("/mq/fanout")
    @ResponseBody
    public Result<String> fanout() {
        sender.sendFanout("hello, rabbitmq");
        return Result.success("hello, rabbitmq");
    }

    @RequestMapping("/mq/topic")
    @ResponseBody
    public Result<String> topic() {
        sender.sendTopic("hello, rabbitmq");
        return Result.success("hello, rabbitmq");
    }
*/
    @RequestMapping("/mq")
    @ResponseBody
    public Result<String> mq() {
        sender.send("hello, rabbitmq");
        return Result.success("hello, rabbitmq");
    }

    @RequestMapping("/mq/miaosha")
    @ResponseBody
    public Result<String> mqMiaosha() {
        MiaoshaMessage mm = new MiaoshaMessage();
        MiaoshaUser user = new MiaoshaUser();
        user.setId(18912341234L);
        user.setPassword("1123456");
        long goodsId = 1;

        mm.setUser(user);
        mm.setGoodsId(goodsId);
        sender.sendMiaoshaMessage("{goodsId:1,user:{id:18912341234,lastLoginDate:1634265482000,loginCount:1,nickname:aaa,password:b7797cce01b4b131b433b6acf4add449,registerDate:1634179066000,salt:1a2b3c4d}}\n");
        return Result.success("hello, miaosha");
    }


    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("name", "wyf");
        return "hello";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello, world");
    }


    @RequestMapping("/helloError")
    @ResponseBody
    public Result<String>  helloError() {
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/db/get")
    @ResponseBody
    public Result<User> dbGet() {

        User user = userService.getById(1);
        return Result.success(user);
    }

    @RequestMapping("/db/tx")
    @ResponseBody
    public Result<Boolean> dbtx() {

        userService.tx();
        return Result.success(true);
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<User> redisGet() {

        User user = redisService.get(UserKey.getById,""+1, User.class);
        return Result.success(user);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet() {
        User user = new User();
        user.setId(1);
        user.setName("1111");
        redisService.set(UserKey.getById,""+1, user);   // "UserKey: id1"
        return Result.success(true);
    }

}
