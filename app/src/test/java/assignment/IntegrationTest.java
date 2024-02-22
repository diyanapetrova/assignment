package assignment;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IntegrationTest {

    // Add an integration test that redirects the System.out to a file and checks the output of the main method.
    @Test
    void integrationTest_comparePrintedResults() throws IOException {
        // Redirect System.out to a file
        String outputFile = "output.txt";
        PrintStream out = new PrintStream(new FileOutputStream(outputFile));
        System.setOut(out);

        // Run the main method
        App.main(new String[0]);

        // Compare the output.txt file with the expected_output.txt file
        byte[] output = Files.readAllBytes(Paths.get(outputFile));
        byte[] expected_output = Files.readAllBytes(Paths.get("expected_output.txt"));
        assertArrayEquals(expected_output, output);

    }
}
