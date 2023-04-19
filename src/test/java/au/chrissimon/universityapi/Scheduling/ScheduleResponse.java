package au.chrissimon.universityapi.Scheduling;

import java.util.Set;

import au.chrissimon.universityapi.Courses.Course;

public class ScheduleResponse {
    public Set<Course> scheduledCourses;

    public Set<Course> getScheduledCourses() {
        return scheduledCourses;
    }

    public void setScheduledCourses(Set<Course> scheduledCourses) {
        this.scheduledCourses = scheduledCourses;
    }
}
