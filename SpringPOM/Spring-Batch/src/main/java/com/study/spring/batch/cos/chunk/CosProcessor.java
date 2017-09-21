package com.study.spring.batch.cos.chunk;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.study.spring.batch.cos.domain.CosData;

@Component
public class CosProcessor implements ItemProcessor<CosData, CosData> {


    @Override
    public CosData process(CosData item) throws Exception {
        System.out.println("process-->" + item);
        item.setDealed(true);
        return item;
    }

}
