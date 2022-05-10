package com.kbplus.demo.log.common.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
/**
 * @author kbplus
 * @date 2022-03-28
 * @blog https://blog.csdn.net/cyy9487
 */
@Configuration
public class LogDirectRabbitConfig {

    /**
     * 日志队列
     */
    @Bean
    public Queue createModuleRequestQueue() {
        return new Queue("log",true);
    }

    @Bean
    public DirectExchange createModuleRequestExchange() { return new DirectExchange("log_exchange",true,false); }

    @Bean
    public Binding ModuleRequestBindingDirect() {
        return BindingBuilder.bind(createModuleRequestQueue()).
                to(createModuleRequestExchange()).
                with("log_request_key");
    }

}
