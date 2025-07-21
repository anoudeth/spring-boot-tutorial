package com.noh.springmcp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.ai.tool.ToolCallbacks;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringMcpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMcpApplication.class, args);
    }

    @Bean
    public List<ToolCallback> danTools(CourseService courseService) {
        return List.of(ToolCallbacks.from(courseService));
    }

}
