package com.lzq.service;

import com.lzq.pojo.Items;
import com.lzq.pojo.ItemsImg;
import com.lzq.pojo.ItemsParam;
import com.lzq.pojo.ItemsSpec;
import com.lzq.utils.PagedGridResult;
import com.lzq.vo.CommentLevelCountsVO;
import com.lzq.vo.ItemCommentVO;

import java.util.List;

public interface ItemService {

    Items queryItemById(String itemId);
    List<ItemsImg> queryItemImgList(String itemId);
    List<ItemsSpec> queryItemSpecList(String itemId);
    ItemsParam queryItemParam(String itemId);

    CommentLevelCountsVO queryComments(String itemId);

    PagedGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer size);
}
