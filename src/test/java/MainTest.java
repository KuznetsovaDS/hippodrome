import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;


class MainTest {

    @Test @Timeout(value = 22, unit = TimeUnit.SECONDS)
    @Disabled
    void mainExecutionTime() throws Exception {
        Main.main(null);
    }
}