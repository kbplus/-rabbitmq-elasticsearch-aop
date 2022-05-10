package com.kbplus.demo.common.sender;

import com.kbplus.demo.common.entity.MyLog;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Component
public class ModuleRequestSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(MyLog msg){
        //消息发送失败返回到队列中, yml需要配置 publisher-returns: true
        rabbitTemplate.setMandatory(true);
        //发送消息
        rabbitTemplate.convertAndSend("log_exchange","log_request_key", msg);
    }
}
