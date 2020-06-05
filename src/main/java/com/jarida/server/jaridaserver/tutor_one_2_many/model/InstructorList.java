package com.jarida.server.jaridaserver.tutor_one_2_many.model;

import java.util.List;

public class InstructorList {
    private List<Instructor> instructorList;

    public InstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    public List<Instructor> getInstructorList() {
        return instructorList;
    }

    public void setInstructorList(List<Instructor> instructorList) {
        this.instructorList = instructorList;
    }

    @Override
    public String toString() {
        return "InstructorList{" +
                "instructorList=" + instructorList +
                '}';
    }
}
