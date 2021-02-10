//package nl.rabo.playground.cachingtest;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.hazelcast.client.HazelcastClient;
//import com.hazelcast.client.config.ClientConfig;
//import com.hazelcast.config.Config;
//import com.hazelcast.config.EvictionPolicy;
//import com.hazelcast.config.MapConfig;
//import com.hazelcast.config.MaxSizeConfig;
//import com.hazelcast.core.HazelcastInstance;
//
//@Configuration
//public class HazelcastConfig {
//
//    @Bean
//    public Config hazelcastConfiguration() {
//        Config config = new Config();
//        config.setInstanceName("test-instance").addMapConfig(
//                new MapConfig()
//                        .setName("configuration")
//                        .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
//                        .setEvictionPolicy(EvictionPolicy.LRU)
//                        .setTimeToLiveSeconds(2));
//        return config;
//    }
//
//    @Bean
//    public HazelcastInstance hazelcastInstance(ClientConfig config) {
//        HazelcastInstance hazelcastInstance = null;
//        try {
//            hazelcastInstance = HazelcastClient.newHazelcastClient(config);
//        } catch (Exception e) {
//        }
//
//        return hazelcastInstance;
//    }
//}
