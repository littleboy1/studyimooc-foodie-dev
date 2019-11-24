package com.lzq.vo;

import com.lzq.pojo.Items;
import com.lzq.pojo.ItemsImg;
import com.lzq.pojo.ItemsParam;
import com.lzq.pojo.ItemsSpec;
import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class ItemInfoVO {
    private Items item;
    private List<ItemsImg> itemImgList;
    private List<ItemsSpec> itemSpecList;
    private ItemsParam itemParams;
}
