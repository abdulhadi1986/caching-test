package nl.rabo.playground.cachingtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<String> saveDataToHazelCast(@RequestParam String key, @RequestParam String val) {

        return ResponseEntity.ok(hazelcastDataService.saveToCache("eligibility-cache", key, val));
    }

    @GetMapping("/get")
    public ResponseEntity<String> getDateFromHazelCast(@RequestParam String key) {
        return ResponseEntity.ok(hazelcastDataService.getFromCache("eligibility-cache", key));
    }
}