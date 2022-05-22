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

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "student")
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "student_id")
    @NotNull(message = "is require")
    @Pattern(regexp = Validator.VALID_STUDENT_ID_REGEX, message = "Student Id: 6 digits")
    private String student_id;

    @Column(name = "name")
    @NotNull(message = "is require")
    private String name;

    @Column(name = "phone")
    @Pattern(regexp = Validator.VALID_PHONE_REGEX, message = "Phone: must be at least 10 digits")
    @NotNull(message = "is require")
    private String phone;

    @Column(name = "gender")
    @NotNull(message = "is require")
    private String gender;

    @Column(name = "gpa")
    @Min(value = 0, message = "must be greater than or equal to zero")
    @Max(value = 10, message = "must be less than or equal to 10")
    private double gpa;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    public Student(String name, String phone, String gender, String student_id, double gpa) {
        this.student_id = student_id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        return String.format("Student [id=%s, name=%s, phone=%s, gender=%s, gpa=%f]", student_id, name, phone, gender, gpa);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((student_id == null) ? 0 : student_id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Student other = (Student) obj;
        if (student_id == null) {
            return other.student_id == null;
        } else return student_id.equals(other.student_id);
    }
}