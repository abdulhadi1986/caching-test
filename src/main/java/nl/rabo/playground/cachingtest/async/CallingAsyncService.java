package nl.rabo.playground.cachingtest.async;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CallingAsyncService {

    private final AsyncService asyncService;

    public String getAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> first = asyncService.getFirst();
        CompletableFuture<String> second = asyncService.getSecond();

        while (!second.isDone()) {
            log.error("waiting for second");
            break;
        }
        while (!first.isDone()) {
            log.error("waiting for first");
            break;
        }

        if (Objects.nonNull(second.get())) {
            log.error("ending the first by force");
            first.complete(null);
            return second.get();
        }
        return first.get() + "-" + second.get();
    }
}
