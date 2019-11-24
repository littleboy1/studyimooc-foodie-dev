package com.lzq.controller;

import com.lzq.enums.Level;
import com.lzq.enums.YesOrNo;
import com.lzq.pojo.Carousel;
import com.lzq.pojo.Category;
import com.lzq.service.CarouselService;
import com.lzq.service.CategoryService;
import com.lzq.utils.JsonResult;
import com.lzq.vo.CategoryVO;
import com.lzq.vo.NewItemsVO;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    private CategoryService categoryService;


    @GetMapping("/carousel")
    public JsonResult carousel(){
        List<Carousel> result = carouselService.queryAll(YesOrNo.YES.getType());
        return JsonResult.ok(result);
    }
    @GetMapping("/cats")
    public JsonResult cats(){
        List<Category> result = categoryService.queryAllRootLevelCat();
        return JsonResult.ok(result);
    }
    @GetMapping("/subCat/{rootCatId}")
    public JsonResult subCat(@PathVariable Integer rootCatId){
        if (rootCatId == null){
            return JsonResult.errorMsg("当前请求的参数不正确，请自行调整");
        }
        List<CategoryVO> result = categoryService.getSubCatList(rootCatId);
        return JsonResult.ok(result);
    }

    @GetMapping("/sixNewItems/{rootCatId}")
    public JsonResult sixNewItems(@PathVariable Integer rootCatId){
        if (rootCatId == null){
            return JsonResult.errorMsg("分类不存在");
        }
        List<NewItemsVO> result = categoryService.getSixNewItemLazy(rootCatId);
        return JsonResult.ok(result);
    }

}
