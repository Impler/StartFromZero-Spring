package com.study.spring.batch.cos.chunk;

import java.util.Random;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import com.study.spring.batch.cos.domain.CosData;

@Component
public class CosReader implements ItemReader<CosData> {

    private Random random = new Random();
    @Override
    public CosData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        int r = random.nextInt(10);
        if(r % 5 == 0){
            return null;
        }
        r = random.nextInt(10);
        if(r % 7 == 0){
            throw new Exception("read excepiton");
        }
        int id = random.nextInt(1000);
        String name = "a" + random.nextInt(10) + "b" + random.nextInt(20) + "c" + random.nextInt(5);
        CosData data = new CosData(id, name);
        return data;
    }

}
