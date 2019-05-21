import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.*;

public class Testing {

    @Test
    @Disabled("Test to check if wercker works.")
    public void test_fail() {
        fail("Let's test the failing unit test");
    }

    @Test
    public void testPropertiesMethod() throws IOException {
        Properties props = new Properties();
        InputStream input = new FileInputStream("src/config/config.properties");
        props.load(input);
        System.out.print( props.getProperty("password"));
        assertEquals(props.getProperty("password"), "45e7c5f4925a9d");
    }

}

