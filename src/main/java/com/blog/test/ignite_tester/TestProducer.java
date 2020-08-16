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
public class TestProducer implements Runnable{
    
    IgniteCache c;

    public TestProducer(IgniteCache c) {
        this.c = c;
    }
    

    @Override
    public void run() {
        
        while(true)        
        {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            
            String k=LocalDateTime.now().toInstant(ZoneOffset.UTC).getEpochSecond()+"";
            
            System.out.println("Setting key in cache.k="+k);
            c.put(k,k);
        }        
    }
    
}
