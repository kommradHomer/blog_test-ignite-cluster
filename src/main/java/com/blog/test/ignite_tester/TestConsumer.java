/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.test.ignite_tester;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import org.apache.ignite.IgniteCache;

/**
 *
 * @author yigit
 */
public class TestConsumer implements Runnable{
    
    IgniteCache c;

    public TestConsumer(IgniteCache c) {
        this.c = c;
    }
    

    @Override
    public void run() {
        
        while(true)        
        {
            try {
                Thread.sleep(2000);
            } catch (Exception e) {}
            
            // read from 3 seconds behind
            String k=LocalDateTime.now().minusSeconds(3).toInstant(ZoneOffset.UTC).getEpochSecond()+"";
            
            System.out.println("Reading from cache.k="+k);
            System.out.println(c.get(k));
        }        
    }
    
}
