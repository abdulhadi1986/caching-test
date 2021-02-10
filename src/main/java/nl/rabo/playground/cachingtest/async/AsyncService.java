package nl.rabo.playground.cachingtest.async;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AsyncService {

    @Async("asyncExecutor")
    public CompletableFuture<String> getFirst() {
        try{
            log.error("getFirst Started");
            Thread.sleep(4000L);
            log.error("getFirst finished");
            return CompletableFuture.completedFuture("getFirst");
        } catch (InterruptedException e) {
            return CompletableFuture.completedFuture("getFirst failed");
        }

    }

    @Async("asyncExecutor")
    public CompletableFuture<String> getSecond() {
        try{
            log.error("getSecond started");
            Thread.sleep(2000L);
            log.error("getSecond finished");
            return CompletableFuture.completedFuture("getSecond");
        } catch (InterruptedException e) {
            return CompletableFuture.completedFuture("getSecond failed");
        }

    }
}
