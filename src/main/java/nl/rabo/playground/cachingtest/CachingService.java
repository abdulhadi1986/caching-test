package nl.rabo.playground.cachingtest;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileUrlResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@CacheConfig(cacheNames = "it4it-org.panzer-space.test-cache.cache")
public class CachingService {

    @Cacheable
    public String getData() throws ExecutionException, InterruptedException {
        String s = manageData("key1");
        System.out.println("continue the flow ......");
        //String ss = manageData("key1");
        return s ;
    }


    public String manageData(String key1) throws InterruptedException, ExecutionException {
        String key = "key2";
        String value = "value";
        String map = "myMap";

        System.out.println("method started ......");
        //String valueFromCache = getDataFromCache(map,key);
//        if (Objects.nonNull(valueFromCache)) {
//            System.out.println("found value in the cache");
//            return valueFromCache;
//        }

        String runningTask = getDataFromApi(key);
//        if (runningTask.isDone()) {
//            if (runningTask.get().equals(key)) {
//                System.out.println("execution is done, saving vaue to");
//                saveDataToCache(map, key, value);
//                return runningTask.get();
//            }
//        }
//        runningTask.thenRun(() -> saveDataToCache(map, key, value));

        return runningTask;
    }

//    public String saveDataToCache(String map, String key, String value) {
//        return hazelcastDataService.saveToCache(map, key, value);
//    }
//
//    public String getDataFromCache(String map, String key) {
//        return hazelcastDataService.getFromCache(map, key);
//    }

    public String getDataFromApi(String key) throws InterruptedException {

        Thread.sleep(5000);
        return key;
    }
}
