import java.io.ByteArrayOutputStream; // As we need to capture the message in standard output stream, we need this class
import java.io.PrintStream; // Used to backup the standard output
import static org.junit.Assert.*; // Junit components

public class TestHelloWorld {
    PrintStream outstream = null; // Used to backup the standard output stream
    ByteArrayOutputStream mybytes = null; // Used to store the captured data
    HelloWorld helloworld; // Used to create an instace of our HelloWorld

    @org.junit.Before // prepare for test
    public void setUp() throws Exception {
        mybytes = new ByteArrayOutputStream(); // Instantiate the byte array
        outstream = System.out; // Bind outstream with the oringinal standard output
        System.setOut(new PrintStream(mybytes)); // Redirect output stream to our byte array
    }

    @org.junit.Test // Testing
    public void testResult() throws Exception {
        helloworld = new HelloWorld(); // Instantiate our class
        String str = new String("Hello,World\n"); // Expected output
        assertEquals(str, mybytes.toString()); // Assert: str == output
    }

    @org.junit.After
    public void tearDown() throws Exception {
        System.setOut(outstream); // Recovery the standard output
    }
}