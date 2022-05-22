package com.demo.Model;

import java.util.Comparator;

public class CourseTitleCompare implements Comparator<Course> {
    @Override
    public int compare(Course o1, Course o2) {
        return o1.getCourse_id().compareToIgnoreCase(o2.getCourse_id());
    }
}
