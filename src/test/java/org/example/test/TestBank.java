package org.example.test;

import org.example.bank.config.SpringConfiguration;
import org.example.bank.service.AccountService;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestBank {
    @Test
    public void test01() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        try {
            accountService.transfer("act001", "act002", 1000.0);
            System.out.println("转账成功");
            Logger logger = LoggerFactory.getLogger(TestBank.class);
            logger.info("我是一条日志信息");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("转账失败");
        }
    }

}
