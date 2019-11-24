package com.lzq.service;

import com.lzq.pojo.Items;
import com.lzq.pojo.ItemsImg;
import com.lzq.pojo.ItemsParam;
import com.lzq.pojo.ItemsSpec;

import java.util.List;

public interface ItemService {

    Items queryItemById(String itemId);
    List<ItemsImg> queryItemImgList(String itemId);
    List<ItemsSpec> queryItemSpecList(String itemId);
    ItemsParam queryItemParam(String itemId);
}
