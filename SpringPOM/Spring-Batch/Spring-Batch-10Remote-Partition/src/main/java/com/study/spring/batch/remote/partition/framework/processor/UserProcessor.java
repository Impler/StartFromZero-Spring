package com.study.spring.batch.remote.partition.framework.processor;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import com.study.spring.batch.remote.partition.domain.User;

public class UserProcessor implements ItemProcessor<User, User> {

    private static Logger log = LoggerFactory.getLogger(UserProcessor.class);

    private Random r = new Random();

    @Override
    public User process(User item) throws Exception {
        String resultCode = null;
        String resultContent = null;
        if (r.nextInt(20) % 7 == 0) {
            resultCode = "0000";
            resultContent = "实名认证一致";
        } else {
            resultCode = "9999";
            resultContent = "实名认证不一致";
        }
        item.setResultCode(resultCode);
        item.setResultContent(resultContent);
        log.info(item.toString());
        
        /*if(r.nextInt(10) % 7 == 0) {
            throw new Exception("test exp");
        }*/
        Thread.sleep(100);
        return item;
    }

}
