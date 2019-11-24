package com.lzq.controller;

import com.lzq.enums.YesOrNo;
import com.lzq.pojo.Carousel;
import com.lzq.service.CarouselService;
import com.lzq.utils.JsonResult;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author lzq
 */

@RestController
@Api(value = "首页",tags ={"首页展示的相关接口"})
@RequestMapping("index")
public class indexController {

    final  static Logger logger = LoggerFactory.getLogger(indexController.class);

    @Autowired
    private CarouselService carouselService;

    @GetMapping("/carousel")
    public JsonResult carousel(){
        List<Carousel> result = carouselService.queryAll(YesOrNo.YES.getType());
        return JsonResult.ok(result);
    }


}
