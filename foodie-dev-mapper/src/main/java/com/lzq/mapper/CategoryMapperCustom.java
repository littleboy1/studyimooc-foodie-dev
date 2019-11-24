package com.lzq.mapper;


import com.lzq.vo.CategoryVO;

import java.util.List;

public interface CategoryMapperCustom {

    List<CategoryVO> getSubCatList(Integer rootCatId);
}