package com.jarida.server.jaridaserver.tutor_one_2_many.model;

import java.util.List;

public class CourseList {
    private List<Course> courseList;

    public CourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "CourseList{" +
                "courseList=" + courseList +
                '}';
    }
}
