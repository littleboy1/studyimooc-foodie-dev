package com.lzq.service.impl;
import java.util.*;

import cn.hutool.core.map.MapUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.lzq.enums.CommentLevel;
import com.lzq.mapper.*;
import com.lzq.pojo.*;
import com.lzq.service.ItemService;
import com.lzq.utils.DesensitizationUtil;
import com.lzq.utils.PagedGridResult;
import com.lzq.vo.CommentLevelCountsVO;
import com.lzq.vo.ItemCommentVO;
import com.lzq.vo.SearchItemsVO;
import com.lzq.vo.ShopcartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;
    @Autowired
    private ItemsImgMapper imgMapper;
    @Autowired
    private ItemsSpecMapper specMapper;
    @Autowired
    private ItemsParamMapper paramMapper;
    @Autowired
    private ItemsCommentsMapper commentsMapper;
    @Autowired
    private ItemsCommentsMapperCustom commentsMapperCustom;
    @Autowired
    private ItemsMapperCustom itemsMapperCustom;

   @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsImg> queryItemImgList(String itemId) {
        Example example = new Example(ItemsImg.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return imgMapper.selectByExample(example);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        Example example = new Example(ItemsSpec.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return specMapper.selectByExample(example);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        Example example = new Example(ItemsParam.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("itemId",itemId);
        return paramMapper.selectOneByExample(example);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountsVO queryComments(String itemId) {
        Integer goodComment = getCommentCounts(itemId, CommentLevel.GOOD.getType());
        Integer normalComment = getCommentCounts(itemId, CommentLevel.NORMAL.getType());
        Integer badComment = getCommentCounts(itemId, CommentLevel.BAD.getType());
        CommentLevelCountsVO result = CommentLevelCountsVO.builder()
                .goodCounts(goodComment)
                .normalCounts(normalComment)
                .badCounts(badComment)
                .totalCounts(goodComment + normalComment + badComment)
                .build();
        return result;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
     Integer getCommentCounts(String itemId,Integer level){
        ItemsComments comments = new ItemsComments();
        comments.setItemId(itemId);
        if (Objects.nonNull(level)){
            comments.setCommentLevel(level);
        }
        return commentsMapper.selectCount(comments);
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult queryPagedComments(String itemId, Integer level,Integer page,Integer size) {
       Map<String, Object> map = Maps.newHashMap();
       map.put("itemId",itemId);
       map.put("level",level);
       PageHelper.startPage(page,size);
       //這裡返回的已經不是自己的List了这里返回的是包装好的配置对象下面只是对当前page进行强转
        List<ItemCommentVO> itemCommentVOS = commentsMapperCustom.queryItemComments(map);

        List<ItemCommentVO> DesensitizationResult = itemCommentVOS.stream().
                peek(item -> item.setNickname(DesensitizationUtil.commonDisplay(item.getNickname())))
                .collect(Collectors.toList());
        PageInfo pageInfo = new PageInfo(itemCommentVOS);
        PagedGridResult result = PagedGridResult.builder().page(page).rows(DesensitizationResult)
                .total(pageInfo.getPages()).records(pageInfo.getTotal()).build();
        return result;
    }
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PagedGridResult searhItems(String keywords, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("keywords", keywords);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> searchItemsVOS = itemsMapperCustom.searchItems(map);
        PagedGridResult result = getPagedGridResult(page, searchItemsVOS);
        return result;
    }

    @Override
    public PagedGridResult searhItems(Integer catId, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("catId", catId);
        map.put("sort", sort);
        PageHelper.startPage(page, pageSize);
        List<SearchItemsVO> searchItemsVOS = itemsMapperCustom.searchItemsByThirdCat(map);
        PagedGridResult result = getPagedGridResult(page, searchItemsVOS);
        return result;
    }

    @Override
    public List<ShopcartVO> queryItemsBySpecIds(String itemSpecIds) {
        String ids[] = itemSpecIds.split(",");
        List<String> specIdsList = new ArrayList<>();
        Collections.addAll(specIdsList, ids);

       return itemsMapperCustom.queryItemsBySpecIds(specIdsList);
    }

    private PagedGridResult getPagedGridResult(Integer page, List<SearchItemsVO> searchItemsVOS) {
        PageInfo<?> pageList = new PageInfo<>(searchItemsVOS);
        return PagedGridResult.builder()
                .page(page)
                .rows(searchItemsVOS)
                .total(pageList.getPages())
                .records(pageList.getTotal()).build();
    }
}
