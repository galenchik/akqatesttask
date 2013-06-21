package com.akqa.scheduler.model;

import com.akqa.scheduler.application.SchedulerApp;
import com.akqa.scheduler.utils.Const;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test foe Schedule
 * 
 *  // TODO add tests with variable inputs
 *
 * @author galenchik
 */
public class ScheduleTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private PrintStream originalOut;

    @Before
    public void setUpStreams() {
        originalOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void outputTest() {
        DateTime dateTime = new DateTime();
        Meeting meeting = new Meeting(new Employee("employee"), new Interval(dateTime, dateTime));
        List<Meeting> meetings = new ArrayList<Meeting>();
        meetings.add(meeting);
        Schedule schedule = new Schedule(meetings);
        schedule.showOutput();
        
        String expected = Const.DATE_FORMAT.print(dateTime) + "\r\n" +
                meeting.toString() + "\r\n";
        assertEquals(expected, outContent.toString());
    }
}
