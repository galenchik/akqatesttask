package com.akqa.scheduler.model;

import com.akqa.scheduler.utils.Const;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;

/**
 * Model class for Schedule. Has private field schedule with on direct access
 *
 * @author galenchik
 */
public class Schedule {

    private Map<DateTime, List<Meeting>> schedule;

    /**
     * Constructor. Creates new schedule from list of meetings
     *
     * @param meetings - list of meetings
     */
    public Schedule(List<Meeting> meetings) {
        schedule = new LinkedHashMap<DateTime, List<Meeting>>();
        for (Meeting meeting : meetings) {
            DateTime meetingDay = checkDay(meeting);
            if (meetingDay != null) {
                schedule.get(meetingDay).add(meeting);
            } else {
                List<Meeting> newList = new ArrayList<Meeting>();
                newList.add(meeting);
                schedule.put(meeting.getMeetingInterval().getStart(), newList);
            }
        }
    }

    private DateTime checkDay(Meeting meeting) {
        DateTime meetingStart = meeting.getMeetingInterval().getStart();
        for (DateTime meetingDay : schedule.keySet()) {
            if (meetingDay.getYear() == meetingStart.getYear()
                    && meetingDay.getDayOfYear() == meetingStart.getDayOfYear()) {
                return meetingDay;
            }
        }
        return null;
    }

    /**
     * Returns all meetings for input day
     *
     * @param day - day of meetings to find
     * @return - list of meetings for the day
     */
    public List<Meeting> getByDay(DateTime day) {
        List<Meeting> result = new ArrayList<Meeting>();
        for (DateTime meetingDay : schedule.keySet()) {
            if (meetingDay.getYear() == day.getYear() && meetingDay.getDayOfYear() == day.getDayOfYear()) {
                result.addAll(schedule.get(meetingDay));
                return result;
            }
        }
        return result;
    }
    
    /**
     * Show schedule in necessary format
     *
     */
    public void showOutput() {
        for (DateTime meetingDay : schedule.keySet()) {
            System.out.println(Const.DATE_FORMAT.print(meetingDay));
            for (Meeting meeting : schedule.get(meetingDay)) {
                System.out.println(meeting.toString());
            }
        }
    }
}
