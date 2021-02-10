package nl.rabo.playground.cachingtest;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.MaxSizeConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.security.UsernamePasswordCredentials;

@Configuration
@Profile("dev")
public class HazelcastConfigDev {

    @Bean
    public Config hazelcastConfiguration() {
        Config config = new Config();
        config.setInstanceName("test-instance").addMapConfig(
                new MapConfig()
                        .setName("configuration")
                        .setMaxSizeConfig(new MaxSizeConfig(200, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE))
                        .setEvictionPolicy(EvictionPolicy.LRU)
                        .setTimeToLiveSeconds(2));
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(Config config) {
        HazelcastInstance hazelcastInstance = null;
        try {
            hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        } catch (Exception e) {
        }

        return hazelcastInstance;
    }

    @Bean
    public ClientConfig hazelcastClientConfig() {
        ClientConfig config = new ClientConfig();
        config.setExecutorPoolSize(10000)
              .setGroupConfig(new GroupConfig("test-group"))
              .setCredentials(new UsernamePasswordCredentials("test-user", "test-password"))
              .getNetworkConfig()
              .setAddresses(List.of("localhost"))
              .setSmartRouting(true);
        return config;
    }
}
