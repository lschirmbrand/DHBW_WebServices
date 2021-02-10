package com.example.demo.entrypoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/helloWorld")
public class HelloWorld {

    @GetMapping()
    public String getHelloWorld() {return "<h1>Hello World!</h1>";}
}
