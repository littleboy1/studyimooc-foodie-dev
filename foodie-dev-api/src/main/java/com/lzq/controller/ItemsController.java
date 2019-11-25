package com.lzq.controller;

import com.lzq.enums.CommonConstants;
import com.lzq.enums.YesOrNo;
import com.lzq.pojo.*;
import com.lzq.service.CarouselService;
import com.lzq.service.CategoryService;
import com.lzq.service.ItemService;
import com.lzq.utils.JsonResult;
import com.lzq.utils.PagedGridResult;
import com.lzq.vo.CategoryVO;
import com.lzq.vo.CommentLevelCountsVO;
import com.lzq.vo.ItemInfoVO;
import com.lzq.vo.NewItemsVO;
import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/commentLevel")
    public JsonResult commentLevel(@RequestParam String itemId){
        if (StringUtils.isBlank(itemId)){
            return JsonResult.errorMsg("当前商品不存在");
        }
        CommentLevelCountsVO result = itemService.queryComments(itemId);
        return JsonResult.ok(result);
    }

    @GetMapping("/comments")
    public JsonResult commentLevel(@RequestParam String itemId,
                                   @RequestParam Integer level,
                                   @RequestParam Integer page,
                                   @RequestParam Integer pageSize){
        if (StringUtils.isBlank(itemId)){
            return JsonResult.errorMsg("当前商品不存在");
        }
        if (page ==null){
            page = CommonConstants.DEFAULT_PAGE.getValue();
        }
        if (pageSize ==null){
            pageSize = CommonConstants.DEFAULT_PAGE_SIZE.getValue();
        }
        PagedGridResult result = itemService.queryPagedComments(itemId, level, page, pageSize);
        return JsonResult.ok(result);
    }
}
