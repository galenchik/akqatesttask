package com.akqa.scheduler.model;

import com.akqa.scheduler.utils.Const;
import org.joda.time.Interval;

/**
 * Model class for Meeting
 *
 * @author galenchik
 */
public class Meeting {

    private Employee employee;
    private Interval meetingInterval;

    /**
     * Empty constructor
     */
    public Meeting() {
    }

    /**
     *
     * @param employee - Employee
     * @param meetingInterval - meeting time interval
     */
    public Meeting(Employee employee, Interval meetingInterval) {
        this.employee = employee;
        this.meetingInterval = meetingInterval;
    }

    /**
     *
     * @return - Employee
     */
    public Employee getEmployee() {
        return employee;
    }

    /**
     *
     * @param employee - Employee
     */
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    /**
     *
     * @return - meeting time interval
     */
    public Interval getMeetingInterval() {
        return meetingInterval;
    }

    /**
     *
     * @param meetingInterval - meeting time interval
     */
    public void setMeetingInterval(Interval meetingInterval) {
        this.meetingInterval = meetingInterval;
    }

    @Override
    public String toString() {
        return Const.TIME_FORMAT.print(meetingInterval.getStart()) + " " +
                Const.TIME_FORMAT.print(meetingInterval.getEnd()) + " " +
                employee.getName();
    }
}
