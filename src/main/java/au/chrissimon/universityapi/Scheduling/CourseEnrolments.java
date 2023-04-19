package au.chrissimon.universityapi.Scheduling;

import au.chrissimon.universityapi.Courses.Course;

public class CourseEnrolments {
    private Course course;
    private int enrolmentCount;

    public CourseEnrolments(Course course, int enrolmentCount) {
        super();
        setCourse(course);
        setEnrolmentCount(enrolmentCount);
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    public int getEnrolmentCount() {
        return enrolmentCount;
    }

    public void setEnrolmentCount(int enrolmentCount) {
        this.enrolmentCount = enrolmentCount;
    }
}
