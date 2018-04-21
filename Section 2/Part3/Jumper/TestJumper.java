import static org.junit.Assert.*; // Junit components

public class TestHelloWorld {
    PrintStream outstream = null; // Used to backup the standard output stream
    ByteArrayOutputStream mybytes = null; // Used to store the captured data
    HelloWorld helloworld; // Used to create an instace of our HelloWorld

    @org.junit.Before // prepare for test
    public void initialise() throws Exception {

    }

    @org.junit.Test // Testing
    public void testResult() throws Exception {
        helloworld = new HelloWorld(); // Instantiate our class
        String str = new String("Hello,World\n"); // Expected output
        assertEquals(str, mybytes.toString()); // Assert: str == output
    }
}