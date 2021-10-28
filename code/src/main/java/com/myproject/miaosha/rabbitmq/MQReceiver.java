package com.myproject.miaosha.rabbitmq;

import com.myproject.miaosha.domain.MiaoshaOrder;
import com.myproject.miaosha.domain.MiaoshaUser;
import com.myproject.miaosha.redis.RedisService;
import com.myproject.miaosha.result.CodeMsg;
import com.myproject.miaosha.result.Result;
import com.myproject.miaosha.service.GoodsService;
import com.myproject.miaosha.service.MiaoshaService;
import com.myproject.miaosha.service.OrderService;
import com.myproject.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {

    private static Logger log = LoggerFactory.getLogger(MQReceiver.class);

    @Autowired
    private RedisService redisService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;

    @RabbitListener(queues = MQConfig.MIAOSHA_QUEUE)
    public void receiveMiaosha(String message) {
        log.info("receive message：" + message);
        MiaoshaMessage mm = RedisService.stringToBean(message, MiaoshaMessage.class);
        MiaoshaUser user = mm.getUser();
        long goodsId = mm.getGoodsId();

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return;
        }

        // 判断是否已经秒杀到
        MiaoshaOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return ;
        }

        // 减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);


    }


    @RabbitListener(queues = MQConfig.QUEUE)
    public void receive(String message) {
        log.info("receive message：" + message);
    }
    /*


    @RabbitListener(queues = MQConfig.TOPIC_QUEUE1)
    public void receiveTopic1(String message) {
        log.info("topic queue1 message：" + message);
    }

    @RabbitListener(queues = MQConfig.TOPIC_QUEUE2)
    public void receiveTopic2(String message) {
        log.info("topic queue2 message：" + message);
    }

    @RabbitListener(queues = MQConfig.HEADER_QUEUE)
    public void receiveHeaderQueue(byte[] message) {
        log.info("header queue message: " + new String(message));
    }

     */

}
