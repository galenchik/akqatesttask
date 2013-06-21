package com.akqa.scheduler.core.impl;

import com.akqa.scheduler.core.ScheduleCreator;
import com.akqa.scheduler.model.Meeting;
import com.akqa.scheduler.model.Submission;
import com.akqa.scheduler.utils.Const;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Interval;

/**
 *
 * Implementation of ScheduleCreator interface
 *  
 * @author galenchik
 */
public class ScheduleCreatorImpl implements ScheduleCreator {

    private int beginWorkingTimeH;
    private int beginWorkingTimeM;
    private int endWorkingTimeH;
    private int endWorkingTimeM;

    public List<Meeting> createMeetings(List<Submission> submissions, String workingHoursStr) {
        List<Meeting> meetings = new ArrayList<Meeting>();
        Collections.sort(submissions, Const.SUBMISSION_COMPARATOR);
        parseWorkHours(workingHoursStr);
        for (Submission submission : submissions) {
            // Check working time
            if (meetingInWorkingDay(submission.getMeetingInterval())) {
                // Check if meeting room isn't free
                if (checkMeetingRoomIsFree(meetings, submission)) {
                    // Create new meeting
                    Meeting newMeeting = (Meeting) submission;
                    meetings.add(newMeeting);
                }
            }
        }
        Collections.sort(meetings, Const.MEETING_COMPARATOR);
        return meetings;
    }

    private void parseWorkHours(String workingHoursStr) {
        String[] workHoursStr = workingHoursStr.split(" ");
        beginWorkingTimeH = Integer.parseInt(workHoursStr[0].substring(0, 2));
        beginWorkingTimeM = Integer.parseInt(workHoursStr[0].substring(2));
        endWorkingTimeH = Integer.parseInt(workHoursStr[1].substring(0, 2));
        endWorkingTimeM = Integer.parseInt(workHoursStr[1].substring(2));
    }

    private boolean meetingInWorkingDay(Interval meetingInterval) {
        DateTime beginningOfWork = meetingInterval.getStart();
        beginningOfWork = beginningOfWork.withTime(beginWorkingTimeH, beginWorkingTimeM, 0, 0);
        DateTime endOfWork = meetingInterval.getStart();
        endOfWork = endOfWork.withTime(endWorkingTimeH, endWorkingTimeM, 0, 0);
        Interval workingDay = new Interval(beginningOfWork, endOfWork);
        return workingDay.contains(meetingInterval);
    }

    private boolean checkMeetingRoomIsFree(List<Meeting> meetings, Submission submission) {
        if (!meetings.isEmpty()) {
            for (Meeting meeting : meetings) {
                if (meeting.getMeetingInterval().overlaps(submission.getMeetingInterval())) {
                    // Meeting room is busy
                    return false;
                }
            }
        }
        return true;
    }
}
