package com.lzq.service;

import com.lzq.pojo.Category;
import com.lzq.vo.CategoryVO;

import java.util.List;

public interface CategoryService {

    List<Category> queryAllRootLevelCat();

    List<CategoryVO> getSubCatList(Integer rootCatId);
}
