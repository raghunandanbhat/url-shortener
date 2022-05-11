package com.shortnr.url.services;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.shared.SharedCount;
import org.apache.curator.framework.recipes.shared.SharedCountListener;
import org.apache.curator.framework.recipes.shared.SharedCountReader;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ZooKeeperCounterService implements SharedCountListener {
    private static final String ZK_SERVER = "localhost:2181";
    private static final String ZK_COUNTER_PATH = "/urlShrtnr/counter";

    private CuratorFramework zkCuratorClient;
    //private DistributedAtomicLong count;
    private SharedCount count;

    @PostConstruct
    private void sharedCounterConstruct(){
        zkCuratorClient = CuratorFrameworkFactory.newClient(ZK_SERVER, new ExponentialBackoffRetry(1000,3));
        zkCuratorClient.start();

        count = new SharedCount(zkCuratorClient, ZK_COUNTER_PATH, 0);

        try{
            if(zkCuratorClient == null){
                sharedCounterConstruct();
            }

            count.start();
            count.setCount(count.getCount()+1);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Bean("range")
    public int getRange(){
        int before = count.getCount();
        try{
            count.setCount(before+1);
            return count.getCount();
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void countHasChanged(SharedCountReader sharedCountReader, int i) throws Exception {

    }

    @Override
    public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

    }
}
