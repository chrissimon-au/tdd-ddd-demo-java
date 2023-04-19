package au.chrissimon.universityapi.Scheduling;

import java.util.Set;

import au.chrissimon.universityapi.Courses.CourseResponse;

public class ScheduleResponse {
    public Set<CourseResponse> scheduledCourses;

    public Set<CourseResponse> getScheduledCourses() {
        return scheduledCourses;
    }

    public void setScheduledCourses(Set<CourseResponse> scheduledCourses) {
        this.scheduledCourses = scheduledCourses;
    }
}
