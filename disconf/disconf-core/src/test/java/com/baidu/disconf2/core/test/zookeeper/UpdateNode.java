package com.baidu.disconf2.core.test.zookeeper;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;

import com.baidu.disconf2.core.common.zookeeper.inner.ResilientActiveKeyValueStore;

/**
 * 更新 结点
 * 
 * @author liaoqiqi
 * @version 2014-6-16
 */
public class UpdateNode {

    public static String hosts = "10.48.57.42:8581,10.48.57.42:8582,10.48.57.42:8583";
    public static String disconfFileNode = "/disconf/dsp_demo_1_0_0_0_online/file/confA.properties";

    public static final String PATH = disconfFileNode;

    private ResilientActiveKeyValueStore store;
    private Random random = new Random();

    public UpdateNode(String hosts) throws IOException, InterruptedException {
        store = new ResilientActiveKeyValueStore();
        store.connect(hosts);
    }

    public void run() throws InterruptedException, KeeperException {

        String value = random.nextInt(100) + "";
        store.write(PATH, value);
        System.out.printf("Set %s to %s\n", PATH, value);
        TimeUnit.SECONDS.sleep(random.nextInt(10));
    }

    public static void main(String[] args) throws Exception {

        UpdateNode updateNode = new UpdateNode(hosts);
        updateNode.run();
    }
}