/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.blog.test.ignite_tester;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

/**
 *
 * @author yigit
 */
public class App {
    
    private static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2);

    
    public static void main(String[] args) {
        
        IgniteConfiguration cfg = new IgniteConfiguration();
        
        
        cfg.setClientMode(true);
        
        cfg.setPeerClassLoadingEnabled(true);
        
        cfg.setWorkDirectory("/tmp/");
        
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("IP OF NODE01","IP OF NODE02"));
        
        
        cfg.setDiscoverySpi(new TcpDiscoverySpi().setIpFinder(ipFinder));
        
        
        Ignite ignite=Ignition.start(cfg);
        
        IgniteCache c=ignite.getOrCreateCache("myReplicatedCache");
        
        EXECUTOR.execute(new TestProducer(c));
        
        
        /*  WAITING 5 seconds to let the Producer set some keys */
        try {
            Thread.sleep(5000);            
        } catch (Exception e) {
        }
        
        EXECUTOR.execute(new TestConsumer(c));
    }
    
}
