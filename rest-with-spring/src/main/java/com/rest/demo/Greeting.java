package com.rest.demo;
import lombok.Data;
import org.springframework.web.bind.annotation.RestController;

@Data
public class Greeting {

    private final long id;
    private final String content;


}
