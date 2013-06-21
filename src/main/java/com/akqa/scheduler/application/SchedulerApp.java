package com.akqa.scheduler.application;

import com.akqa.scheduler.core.ScheduleCreator;
import com.akqa.scheduler.core.impl.ScheduleCreatorImpl;
import com.akqa.scheduler.model.Employee;
import com.akqa.scheduler.model.Meeting;
import com.akqa.scheduler.model.Schedule;
import com.akqa.scheduler.model.Submission;
import com.akqa.scheduler.utils.Const;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Main class
 *
 */
public class SchedulerApp {

    private static String workingHoursStr;
    private static List<Submission> submissions = new ArrayList<Submission>();
    // TODO init ScheduleCreator in context
    private static ScheduleCreator scheduleCreator = new ScheduleCreatorImpl();
    private static String inputFileName = "/input.txt";

    /**
     * Main function. Parses file, making schedule and show it at standard output
     * 
     * @param args
     */
    public static void main(String[] args) {
        String error = readInput();
        if (error != null) {
            System.err.println(error);
            return;
        }

        List<Meeting> meetings = scheduleCreator.createMeetings(submissions, workingHoursStr);

        Schedule schedule = new Schedule(meetings);
        schedule.showOutput();
    }

    /**
     * // TODO Add processing of errors while parsing
     *
     * Function parses input file
     */
    private static String readInput() {
        try {
            InputStream is = SchedulerApp.class.getResourceAsStream(inputFileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String strLine;
            // Read work hours
            if ((strLine = br.readLine()) != null) {
                if (Const.WORK_HOURS_PATTERN.matcher(strLine).matches()) {
                    workingHoursStr = strLine;
                } else {
                    return "Error: work hours are in wrong format.";
                }
            } else {
                return "Error: input file doesn't contains work hours.";
            }
            // Read File Line By Line
            while ((strLine = br.readLine()) != null) {
                // parse first line
                int lastSpace = strLine.lastIndexOf(" ");
                String submissionDateStr = strLine.substring(0, lastSpace);

                Submission submission = new Submission();
                submission.setEmployee(new Employee(strLine.substring(lastSpace + 1)));
                submission.setSubmissionDate(Const.SUBMISSION_TIME_FORMAT.parseDateTime(submissionDateStr));

                if ((strLine = br.readLine()) != null) {
                    // parse second line
                    lastSpace = strLine.lastIndexOf(" ");
                    String meetingDateStr = strLine.substring(0, lastSpace);
                    int meetingDuration = Integer.parseInt(strLine.substring(lastSpace + 1));
                    DateTime meetingStartDate = Const.MEETING_TIME_FORMAT.parseDateTime(meetingDateStr);
                    DateTime meetingEndDate = meetingStartDate.plusHours(meetingDuration);

                    submission.setMeetingInterval(new Interval(meetingStartDate, meetingEndDate));
                    submissions.add(submission);
                }
            }
            // Close the input stream
            br.close();
        } catch (IOException e) {
            // TODO Add logging.
            System.err.println("Error: " + e.getMessage());
        }
        return null;
    }

    public static void setInputFileName(String inputFileName) {
        SchedulerApp.inputFileName = inputFileName;
    }
}
