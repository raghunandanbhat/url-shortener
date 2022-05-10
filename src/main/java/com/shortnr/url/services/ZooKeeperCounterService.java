package com.shortnr.url.services;

import com.shortnr.url.model.CounterRange;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.atomic.AtomicValue;
import org.apache.curator.framework.recipes.atomic.DistributedAtomicLong;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ZooKeeperCounterService implements SharedCountListener {
    private static final String ZK_SERVER = "localhost:2181";
    private static final String ZK_COUNTER_PATH = "/urlShrtnr/counter";
    private static final long SPAN = 100;

    private CuratorFramework zkCuratorClient;
    private DistributedAtomicLong count;

    @PostConstruct
    private void sharedCounterConstruct(){
        zkCuratorClient = CuratorFrameworkFactory.newClient(ZK_SERVER, new ExponentialBackoffRetry(1000,3));

        zkCuratorClient.start();
        count = new DistributedAtomicLong(zkCuratorClient, ZK_COUNTER_PATH, new RetryNTimes(10, 10));
    }

    @Bean("range")
    public CounterRange getRange(){
        try{
            if(zkCuratorClient == null){
                sharedCounterConstruct();
            }
            AtomicValue<Long> value = count.increment();
            if(value.succeeded()){
                long start = value.preValue()*SPAN;
                return new CounterRange(start, start+SPAN-1);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCountReader, int i) throws Exception {

    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

    }
}
