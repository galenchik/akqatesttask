package com.akqa.scheduler.core.impl;

import com.akqa.scheduler.core.ScheduleCreator;
import com.akqa.scheduler.model.Meeting;
import com.akqa.scheduler.model.Submission;
import java.util.ArrayList;
import java.util.List;
import org.hamcrest.core.Is;
import org.junit.Test;
import static org.junit.Assert.assertThat;

/**
 * Unit test for ScheduleCreatorImpl
 * 
 * // TODO add tests with variable inputs
 *
 * @author galenchik
 */
public class ScheduleCreatorImplTest {

    @Test
    public void inputTestEmpty() {
        ScheduleCreator scheduleCreator = new ScheduleCreatorImpl();
        String workingHoursStr = "0900 1730";
        List<Submission> submissions = new ArrayList<Submission>();
        List<Meeting> actual = scheduleCreator.createMeetings(submissions, workingHoursStr);
        List<Meeting> expected = new ArrayList<Meeting>();
        assertThat(actual, Is.is(expected));
    }
}
