package com.kbplus.demo.log.recevier;

import com.kbplus.demo.log.model.entity.MyLog;
import com.kbplus.demo.log.service.LogService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Slf4j
@Component
public class ModuleReceiver {

    @Autowired
    private LogService logService;

    /**
     * 日志收集处理
     *
     */
    @RabbitListener(queues = "log")
    @Transactional(rollbackFor = Exception.class)
    public void connectBase(MyLog myLog, Channel channel, Message message) throws IOException {

        try {
            logService.save(myLog);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            e.printStackTrace();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
