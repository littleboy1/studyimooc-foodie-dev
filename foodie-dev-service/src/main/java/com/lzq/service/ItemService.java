package com.lzq.service;

import com.lzq.pojo.Items;
import com.lzq.pojo.ItemsImg;
import com.lzq.pojo.ItemsParam;
import com.lzq.pojo.ItemsSpec;
import com.lzq.utils.PagedGridResult;
import com.lzq.vo.CommentLevelCountsVO;
import com.lzq.vo.ShopcartVO;

import java.util.List;

public interface ItemService {

    Items queryItemById(String itemId);
    List<ItemsImg> queryItemImgList(String itemId);
    List<ItemsSpec> queryItemSpecList(String itemId);
    ItemsParam queryItemParam(String itemId);

    CommentLevelCountsVO queryComments(String itemId);

    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer size);

    /**
     * 在面对具有很多相同功能模块的时候，是否考虑分开单独写最主要的考虑是耦合性与后期的维护性
     * @param keywords
     * @param sort
     * @param page
     * @param pageSize
     * @return
     */
    PagedGridResult searhItems(String keywords, String sort, Integer page, Integer pageSize);

    PagedGridResult searhItems(Integer catId, String sort, Integer page, Integer pageSize);

    List<ShopcartVO> queryItemsBySpecIds(String itemSpecIds);
}
