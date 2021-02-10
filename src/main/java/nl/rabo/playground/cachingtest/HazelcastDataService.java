package nl.rabo.playground.cachingtest;

import java.util.Map;

import nl.rabo.playground.cachingtest.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    }

    public String saveToCache (String map, Employee employee) {
        ObjectMapper mapper = new ObjectMapper();
        String localMap = principal + "." + map;
        try{
            hazelcastInstance.getMap(localMap).put(employee.getId(),mapper.writeValueAsString(employee));
            return "success";
        } catch (Exception e) {
            log.error("error occurred {}.", e);
            return e.getMessage();
        }

    }

    public Employee getFromCache (String map, Employee employee) {
        String localMap = principal + "." + map;
        ObjectMapper mapper = new ObjectMapper();
        try{
            Employee emp = mapper.readValue(String.valueOf(hazelcastInstance.getMap(localMap).get(employee.getId())), Employee.class);
            return emp;
        }catch (Exception e) {
            log.error("error occurred {}.", e);
            return null;
        }
    }
}
