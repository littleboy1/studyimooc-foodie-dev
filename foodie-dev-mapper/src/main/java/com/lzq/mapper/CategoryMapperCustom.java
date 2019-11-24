package com.lzq.mapper;


import com.lzq.vo.CategoryVO;
import com.lzq.vo.NewItemsVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapperCustom {

    List<CategoryVO> getSubCatList(Integer rootCatId);

    List<NewItemsVO> getSixNewItemLazy(@Param(value = "paramsMap") Map<String,Object> map);

}