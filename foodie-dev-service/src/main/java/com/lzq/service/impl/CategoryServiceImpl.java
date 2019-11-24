package com.lzq.service.impl;

import com.google.common.collect.Maps;
import com.lzq.enums.Level;
import com.lzq.mapper.CarouselMapper;
import com.lzq.mapper.CategoryMapper;
import com.lzq.mapper.CategoryMapperCustom;
import com.lzq.pojo.Carousel;
import com.lzq.pojo.Category;
import com.lzq.service.CarouselService;
import com.lzq.service.CategoryService;
import com.lzq.vo.CategoryVO;
import com.lzq.vo.NewItemsVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryMapperCustom categoryMapperCustom;
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        Example example = new Example(Category.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("type", Level.ROOT.getType());
        List<Category> categories = categoryMapper.selectByExample(example);
        return categories;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVO> getSubCatList(Integer rootCatId) {
        return categoryMapperCustom.getSubCatList(rootCatId);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<NewItemsVO> getSixNewItemLazy(Integer rootCatId) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("rootCatId",rootCatId);
        return categoryMapperCustom.getSixNewItemLazy(map);
    }
}
