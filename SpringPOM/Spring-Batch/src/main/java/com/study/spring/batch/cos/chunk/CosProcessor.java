package com.study.spring.batch.cos.chunk;

import java.util.Random;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.study.spring.batch.cos.domain.CosData;

@Component
public class CosProcessor implements ItemProcessor<CosData, CosData> {

    private Random random = new Random();
    private int count = 1;
    @Override
    public CosData process(CosData item) throws Exception {
        System.out.println("process-->" + item + "-->" + count++);
        int r = random.nextInt(10);
        if(r % 2 == 0){
            throw new Exception("process exception");
        }
        item.setDealed(true);
        return item;
    }

}
