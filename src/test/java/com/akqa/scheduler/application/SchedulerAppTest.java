package com.akqa.scheduler.application;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Unit test for SchedulerApp
 */
public class SchedulerAppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private PrintStream originalErr;

    @Before
    public void setUpStreams() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        originalErr = System.err;
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void inputTestEmpty() {
        SchedulerApp.setInputFileName("/test0.txt");
        SchedulerApp.main(null);
        assertEquals("Error: input file doesn't contains work hours.\r\n", errContent.toString());
    }

    @Test
    public void inputTestIncorrect() {
        SchedulerApp.setInputFileName("/test1.txt");
        SchedulerApp.main(null);
        assertEquals("Error: work hours are in wrong format.\r\n", errContent.toString());
    }

    @Test
    public void inputTestWrongSubmissions() {
        SchedulerApp.setInputFileName("/test2.txt");
        SchedulerApp.main(null);
        assertEquals("2011-03-21\r\n09:00 11:00 EMP002\r\n2011-03-22\r\n14:00 16:00 EMP003\r\n16:00 17:00 EMP004\r\n", outContent.toString());
    }
}
