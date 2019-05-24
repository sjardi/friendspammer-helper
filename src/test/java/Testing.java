import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class Testing {

    @Test
    @Disabled("Test to check if wercker works.")
    public void test_fail() {
        fail("Let's test the failing unit test");
    }


}

