package com.coderetreat.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class Runner implements CommandLineRunner {


    @Autowired
    private KafkaTemplate template;
    @Autowired
    private ConfigurableApplicationContext context;

    @Override
    public void run(String... args) throws Exception {
        ListenableFuture<SendResult<Integer, String>> future = template.send("test", "foo");
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, String>>() {

            @Override
            public void onSuccess(SendResult<Integer, String> result) {
                System.out.println("==============> onSuccess");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println("==============> onFailure");
            }
        });
        context.close();
    }

}
