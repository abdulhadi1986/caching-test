package nl.rabo.playground.cachingtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HazelcastDataService {

    private final HazelcastInstance hazelcastInstance;
    private final String principal;

    @Autowired
    public HazelcastDataService (HazelcastInstance hazelcastInstance, ClientConfig config) {
        this.hazelcastInstance = hazelcastInstance;
        this.principal = config.getCredentials().getPrincipal();
        log.info("config are set {}, {}", hazelcastInstance, principal);
    }

    public String saveToCache (String map, String key, String value) {
        String localMap = principal + "." + map;
        try{
            hazelcastInstance.getMap(localMap).put(key,value);
            return "success";
        } catch (Exception e) {
            log.error("error occurred {}.", e);
            return e.getMessage();
        }

    }

    public String getFromCache (String map, String key) {
        String localMap = principal + "." + map;
        try{
            return String.valueOf(hazelcastInstance.getMap(localMap).get(key));
        }catch (Exception e) {
            log.error("error occurred {}.", e);
            return null;
        }

    }
}
