package com.demo.Controller;

import com.demo.Model.Course;
import com.demo.Model.Student;
import com.demo.Service.CourseService;
import com.demo.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    // send a student list of the selected course to view based on courseID
    @RequestMapping("{courseId}/list")
    private String listStudents(ModelMap modelMap, @PathVariable("courseId") String courseId) {
        Course course = courseService.getCourse(courseId);

        // if the course doesn't exist, return 404 page
        if (course == null) {
            return "404";
        }
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("students", course.getStudents());
        return "list-students";
    }


    @PostMapping("{courseId}/save-student")
    public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, ModelMap modelMap,
                              @PathVariable("courseId") String courseId) {

        Course course = courseService.getCourse(courseId);

        // if the course doesn't exist, return 404 page
        if (course == null) {
            return "404";

            // if having errors, return to the form that uses to input data of the student rather than update database
        }else if (bindingResult.hasErrors()) {
            modelMap.addAttribute("course", course);
            return "student-form";
        } else {
            // if the course is full, return to the form that uses to input data of the student rather than update database
            if (course.getEnrollment() <= course.getStudents().size() && studentService.getStudent(student.getStudent_id()) == null) {
                modelMap.addAttribute("message", "The course is full !");
                return "student-form";
            }else{
                student.setCourse(course);
                studentService.saveStudent(student);
                return "redirect:/student/" + courseId + "/list";
            }
        }

    }

    // send data of the selected student based on studentID to view to modify
    @GetMapping("/{courseId}/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("studentId") String id, ModelMap modelMap,
                                    @PathVariable("courseId") String courseId) {
        Course course = courseService.getCourse(courseId);
        Student student = studentService.getStudent(id);
        // if the course or the student doesn't exist, return 404 page
        if (course == null || student == null) {
            return "404";
        }
        modelMap.addAttribute("course", course);
        modelMap.addAttribute("student", student);

        return "student-form";
    }

    // send an empty object to store the data of a new student
    @GetMapping("/{courseId}/showFormForAdd")
    public String showFormForAdd(Model model, @PathVariable("courseId") String courseId) {
        Course course = courseService.getCourse(courseId);

        // if the course doesn't exist, return 404 page
        if (course == null) {
            return "404";
        }
        model.addAttribute("course", course);
        model.addAttribute("student", new Student());
        return "student-form";
    }

    // delete the seleted student based on studentID then redirect to student list page
    @GetMapping("{courseId}/delete")
    public String deleteCustomer(@RequestParam("studentId") String id, ModelMap modelMap,
                                 @PathVariable("courseId") String courseId) {

        studentService.deleteStudent(id);
        Course course = courseService.getCourse(courseId);

        if (course == null) {
            return "404";
        }
        return "redirect:/student/" + courseId + "/list";
    }
}