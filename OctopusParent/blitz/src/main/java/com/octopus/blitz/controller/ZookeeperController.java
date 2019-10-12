//package com.octopus.blitz.controller;
//
//import com.octopus.blitz.repository.ProductBaseInfoRepository;
//import com.octopus.blitz.repository.ProductQuotaInfoRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.zookeeper.WatchedEvent;
//import org.apache.zookeeper.Watcher;
//import org.apache.zookeeper.ZooKeeper;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@Slf4j
//@RestController
//@RequestMapping(value = "zk")
//public class ZookeeperController {
//    private static final Logger logger = LoggerFactory.getLogger(ZookeeperController.class);
//
//    @Autowired
//    private ProductQuotaInfoRepository productQuotaInfoRepository;
//    @Autowired
//    private ProductBaseInfoRepository productBaseInfoRepository;
//
//    @RequestMapping(value = "/get/{node}" ,method = RequestMethod.GET)
//    public String zkget(@PathVariable String node) {
//        Watcher watcher= new Watcher(){
//            @Override
//            public void process(WatchedEvent event) {
//                switch (event.getType()) {
//                    case None:
//                        break;
//                    case NodeCreated:
//                        break;
//                    case NodeDeleted:
//                        break;
//                    case NodeDataChanged:
//                        break;
//                    case NodeChildrenChanged:
//                        break;
//                    case DataWatchRemoved:
//                        break;
//                    case ChildWatchRemoved:
//                        break;
//                    default:
//                }
//                System.out.println("receive event："+event);
//            }
//        };
//
//        String value = null;
//        try {
//            final ZooKeeper zookeeper = new ZooKeeper("app.ipdp.top:2181", 999999, watcher);
//            final byte[] data = zookeeper.getData("/"+node, watcher, null);
//            value = new String(data);
//            zookeeper.close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return value;
//    }
//}
