package org.editor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EditorApplication {

    private final String PREFIX = "/api/v1/";

    public static void main(String[] args) {
        SpringApplication.run(EditorApplication.class, args);
    }
}