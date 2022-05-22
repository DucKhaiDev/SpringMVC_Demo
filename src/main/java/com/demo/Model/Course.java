package com.demo.Model;

import com.demo.Util.Validator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "course")
public class Course implements Serializable, Statistical {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "course_id")
    @NotNull(message = "is require")
    @Pattern(regexp=Validator.VALID_COURSE_ID_REGEX, message="course Id: 2 upper case letter follows by 3 digits")
    private String course_id;

    @Column(name = "title")
    @NotNull(message = "is require")
    private String title;

    @Column(name = "credit")
    @NotNull(message = "is require")
    @Min(value=0, message="must be greater than or equal to zero")
    @Max(value=10, message="must be less than or equal to 10")
    private double credit;

    @Column(name="enrollment")
    @NotNull(message = "is require")
    private int enrollment;

    @OneToMany(fetch=FetchType.EAGER,
            mappedBy="course",
            cascade= {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    private Set<Student> students;

    public Course(String course_id, String title, double credit, int enrollment) {
        this.course_id = course_id;
        this.title = title;
        this.credit = credit;
        this.enrollment = enrollment;
    }

    public void add(Student tempStudent) {
        if (students == null) {
            students = new HashSet<>();
        }
        students.add(tempStudent);
        tempStudent.setCourse(this);
    }

    public Map<String, Integer> getStatistic() {
        Map<String, Integer> stats = new HashMap<>();
        stats.put("A", 0);
        stats.put("B", 0);
        stats.put("C", 0);
        stats.put("D", 0);
        for (Student student : students) {
            if (student.getGpa() >= 8.5) {
                stats.put("A", stats.get("A") + 1);
            } else if (student.getGpa() < 8.5 && student.getGpa() >= 7) {
                stats.put("B", stats.get("B") + 1);
            } else if (student.getGpa() < 7 && student.getGpa() >= 6) {
                stats.put("C", stats.get("C") + 1);
            } else {
                stats.put("D", stats.get("D") + 1);
            }
        }
        return stats;
    }

}