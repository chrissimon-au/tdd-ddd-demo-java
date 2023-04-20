package au.chrissimon.universityapi.Scheduling;

import au.chrissimon.universityapi.Courses.Course;

public class CourseEnrolments implements Comparable<CourseEnrolments> {
    private Course course;
    private Long enrolmentCount;

    public CourseEnrolments(Course course, Long enrolmentCount) {
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
    public Long getEnrolmentCount() {
        return enrolmentCount;
    }

    public void setEnrolmentCount(Long enrolmentCount) {
        this.enrolmentCount = enrolmentCount;
    }

    @Override
    public int compareTo(CourseEnrolments arg) {
        return (int) (arg.getEnrolmentCount() - this.getEnrolmentCount());
    }
}
