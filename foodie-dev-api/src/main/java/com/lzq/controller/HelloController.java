package com.lzq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author lzq
 */
@ApiIgnore
@RestController
public class HelloController {

    @GetMapping("/hello")
    public Object hello(){
        return "hello world";
    }
}
