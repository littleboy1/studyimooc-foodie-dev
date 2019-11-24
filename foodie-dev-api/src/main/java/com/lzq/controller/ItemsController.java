package com.lzq.controller;

import com.lzq.enums.YesOrNo;
import com.lzq.pojo.*;
import com.lzq.service.CarouselService;
import com.lzq.service.CategoryService;
import com.lzq.service.ItemService;
import com.lzq.utils.JsonResult;
import com.lzq.vo.CategoryVO;
import com.lzq.vo.ItemInfoVO;
import com.lzq.vo.NewItemsVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lzq
 */

@RestController
@Api(value = "商品接口",tags ={"商品接口"})
@RequestMapping("items")
public class ItemsController {

    final  static Logger logger = LoggerFactory.getLogger(ItemsController.class);

    @Autowired
    private ItemService itemService;


    @GetMapping("/info/{itemId}")
    public JsonResult sixNewItems(@PathVariable String itemId){
        if (StringUtils.isBlank(itemId)){
            return JsonResult.errorMsg("当前商品不存在");
        }
        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemsImgs = itemService.queryItemImgList(itemId);
        List<ItemsSpec> itemsSpecs = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParams = itemService.queryItemParam(itemId);
        ItemInfoVO result = ItemInfoVO.builder().item(items).itemImgList(itemsImgs)
                .itemSpecList(itemsSpecs).itemParams(itemsParams).build();
        return JsonResult.ok(result);
    }

}
