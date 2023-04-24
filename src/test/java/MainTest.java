import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertTimeout;

public class MainTest {
    @Disabled
    @Test
    //@Timeout(22) + Main.main(null);
    public void timeoutMainTest(){
        assertTimeout(
                ofSeconds(22),
                () -> {
                    Main.main(null);
                }
        );
    }
}
