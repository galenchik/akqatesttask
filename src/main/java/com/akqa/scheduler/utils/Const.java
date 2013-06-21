package com.akqa.scheduler.utils;

import com.akqa.scheduler.model.Meeting;
import com.akqa.scheduler.model.Submission;
import java.util.Comparator;
import java.util.regex.Pattern;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Class with constants
 *
 * @author galenchik
 */
public interface Const {
    // TODO Add patterns for other input strings

    static final Pattern WORK_HOURS_PATTERN = Pattern.compile("^\\d{4} \\d{4}$");

    static final DateTimeFormatter SUBMISSION_TIME_FORMAT =
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    static final DateTimeFormatter MEETING_TIME_FORMAT =
            DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormat.forPattern("yyyy-MM-dd");
    static final DateTimeFormatter TIME_FORMAT =
            DateTimeFormat.forPattern("HH:mm");

    static final Comparator<Submission> SUBMISSION_COMPARATOR = new Comparator<Submission>() {
        @Override
        public int compare(Submission first, Submission second) {
            return first.getSubmissionDate().compareTo(second.getSubmissionDate());
        }
    };
    static final Comparator<Meeting> MEETING_COMPARATOR = new Comparator<Meeting>() {
        @Override
        public int compare(Meeting first, Meeting second) {
            return first.getMeetingInterval().getStart().compareTo(second.getMeetingInterval().getStart());
        }
    };
}
