package com.study.spring.batch.cos.chunk;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.study.spring.batch.cos.domain.CosData;

@Component
public class CosWriter implements ItemWriter<CosData>{

    @SuppressWarnings("unchecked")
    @Override
    public void write(List<? extends CosData> items) throws Exception {
        for (Iterator<CosData> iterator = (Iterator<CosData>) items.iterator(); iterator.hasNext();) {
            CosData cosData = iterator.next();
            System.out.println("write-->" + cosData);
        }
    }

}
