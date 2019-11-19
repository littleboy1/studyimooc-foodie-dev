package com.lzq.controller;

import com.lzq.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lzq
 */
@RestController
public class StuController {

    @Autowired
    private StuService stuService;
    @GetMapping("/getStu")
    public Object getStu(int id){
        return stuService.getStuInfo(id);
    }
}
