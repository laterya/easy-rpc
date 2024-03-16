package com.yp.examplespringbootconsumer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author yp
 * @date: 2024/3/16
 */
@SpringBootTest
class ExampleServiceConsumerTest {

    @Resource
    private ExampleServiceConsumer exampleServiceConsumer;

    @Test
    void test1() {
        exampleServiceConsumer.test();
    }
}