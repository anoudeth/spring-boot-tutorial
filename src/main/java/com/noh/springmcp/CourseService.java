package com.noh.springmcp;

import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private static final Logger log = LoggerFactory.getLogger(CourseService.class);
    private final List<Course> courses = new ArrayList<>();

    @Tool(name = "noh_get_courses", description = "Get a list of courses from Noh Anoudeth")
    public List<Course> getCourses() {
        return courses;
    }

    @Tool(name = "noh_get_course", description = "Get a single courses from Noh anoudetn by title")
    public Course getCourse(String title) {
        return courses.stream().filter(course -> course.title().equals(title)).findFirst().orElse(null);
    }

    @PostConstruct
    public void init() {
        courses.addAll(List.of(
                new Course("Building Web Applications with Spring Boot (FreeCodeCamp)", "https://youtu.be/31KTdfRH6nY"),
                new Course("Spring Boot Tutorial for Beginners - 2023 Crash Course using Spring Boot 3","https://youtu.be/UgX5lgv4uVM")
        ));
    }
}
