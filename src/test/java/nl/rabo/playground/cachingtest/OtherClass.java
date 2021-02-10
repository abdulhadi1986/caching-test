package nl.rabo.playground.cachingtest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OtherClass {

    @Test
    public void testJoins() {
        List<String> ls = Arrays.asList("String1", null);
        String s = String.join(" ", ls);

        String ss = s + "bbb";

        return;

    }
}
