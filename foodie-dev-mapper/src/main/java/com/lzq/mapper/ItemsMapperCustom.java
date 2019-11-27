package com.lzq.mapper;

import com.lzq.my.mapper.MyMapper;
import com.lzq.pojo.Items;
import com.lzq.vo.SearchItemsVO;
import com.lzq.vo.ShopcartVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsMapperCustom {

    List<SearchItemsVO> searchItems(@Param("paramsMap")Map<String, Object> map);
    List<SearchItemsVO> searchItemsByThirdCat(@Param("paramsMap")Map<String, Object> map);
    List<ShopcartVO> queryItemsBySpecIds(@Param("paramsList") List specIdsList);
}