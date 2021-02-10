package nl.rabo.playground.cachingtest;

import nl.rabo.playground.cachingtest.model.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hazelcast.core.HazelcastInstance;

@RestController
@RequestMapping("/hazelcast")
public class AppController {

    @Autowired
    private HazelcastInstance hazelcastInstance;

    @Autowired
    private HazelcastDataService hazelcastDataService;

    @GetMapping("/save")
    public ResponseEntity<String> saveDataToHazelCast(@RequestBody Employee employee) {

        return ResponseEntity.ok(hazelcastDataService.saveToCache("eligibility-cache", employee));
    }

    @GetMapping("/get")
    public ResponseEntity<Employee> getDateFromHazelCast(@RequestBody Employee employee) {
        return ResponseEntity.ok(hazelcastDataService.getFromCache("eligibility-cache", employee));
    }
}