package nl.rabo.playground.cachingtest.async;

import static org.junit.Assert.assertFalse;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class AsyncServiceTest {

    @Autowired
    private CallingAsyncService asyncService;

    @Test
    public void testAsync() throws ExecutionException, InterruptedException {
        String result = asyncService.getAsync();
        log.error("the result is :{}", result);
        assertFalse(result.isEmpty());
    }
}
