package nl.rabo.playground.cachingtest;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import lombok.AllArgsConstructor;

@Disabled
//@ContextConfiguration(classes = {CachingService.class, HazelcastDataService.class, HazelcastConfig.class})
@ExtendWith(MockitoExtension.class)
public class CachingServiceTest {

    @Mock
    private HazelcastDataService hazelcastDataService;

    @InjectMocks
    CachingService cachingService;

    @Test
    void chachingServiceTest() throws ExecutionException, InterruptedException {
       // when(hazelcastDataService.getFromCache(anyString(), anyString())).thenReturn(null);
        String result = cachingService.getData();

        String rr = cachingService.getData();

        return;
    }
}
