package com.akqa.scheduler.model;

import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 * Model class for Submission.
 *
 * @author galenchik
 */
public class Submission extends Meeting {

    private DateTime submissionDate;

    /**
     * Empty constructor
     */
    public Submission() {
        super();
    }

    /**
     *
     * @param submissionDate - submission date
     * @param employee - employee
     * @param meetingInterval - meeting time interval
     */
    public Submission(DateTime submissionDate, Employee employee, Interval meetingInterval) {
        super(employee, meetingInterval);
        this.submissionDate = submissionDate;
    }

    /**
     *
     * @return - submission date
     */
    public DateTime getSubmissionDate() {
        return submissionDate;
    }

    /**
     *
     * @param submissionDate - submission date
     */
    public void setSubmissionDate(DateTime submissionDate) {
        this.submissionDate = submissionDate;
    }
}
