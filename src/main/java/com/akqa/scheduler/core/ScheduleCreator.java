package com.akqa.scheduler.core;

import com.akqa.scheduler.model.Meeting;
import com.akqa.scheduler.model.Submission;
import java.util.List;

/**
 *
 * @author galenchik
 */
public interface ScheduleCreator {
    
    /**
     *
     * @param submissions - list of submissions
     * @param workingHoursStr - working hours in format "HHmm HHmm"
     * @return - list of created meetings
     */
    List<Meeting> createMeetings(List<Submission> submissions, String workingHoursStr);
    
}
