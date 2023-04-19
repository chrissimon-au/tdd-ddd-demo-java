package au.chrissimon.universityapi.Scheduling;

import java.util.Collection;

import au.chrissimon.universityapi.Courses.Course;

public class Schedule {
    private Collection<Course> scheduledCourses;

    public Schedule(Collection<Course> scheduledCourses) {
        super();
        setScheduledCourses(scheduledCourses);
    }

    public Collection<Course> getScheduledCourses() {
        return scheduledCourses;
    }

    public void setScheduledCourses(Collection<Course> scheduledCourses) {
        this.scheduledCourses = scheduledCourses;
    }
}