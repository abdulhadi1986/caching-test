package nl.rabo.playground.cachingtest;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.security.UsernamePasswordCredentials;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Configuration
public class HazelcastConfig {

    @Value("${vcap.services.rails-caching-service.credentials.principal:principal}")
    private String hazelcastPrincipal;
    @Value("${vcap.services.rails-caching-service.credentials.password:password}")
    private String hazelcastPassword;
    @Value("${vcap.services.rails-caching-service.credentials.group:group}")
    private String hazelcastGroup;
    @Value("${vcap.services.rails-caching-service.credentials.ips:localhost}")
    private List<String> hazelcastNodeIps;

    @Bean
    public ClientConfig hazelcastClientConfig() {
        ClientConfig config = new ClientConfig();
        config.setExecutorPoolSize(10000)
              .setGroupConfig(new GroupConfig(hazelcastGroup))
              .setCredentials(new UsernamePasswordCredentials(hazelcastPrincipal, hazelcastPassword))
              .getNetworkConfig()
              .setAddresses(hazelcastNodeIps)
              .setSmartRouting(true);
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(ClientConfig config) {
        HazelcastInstance hazelcastInstance = null;
        try {
            hazelcastInstance = HazelcastClient.newHazelcastClient(config);
            log.info("successfully connected to cluster {}.", hazelcastGroup);
        } catch (Exception e) {
            log.error("Not able to connect to cluster {}.", hazelcastGroup);
        }

        return hazelcastInstance;
    }
}
