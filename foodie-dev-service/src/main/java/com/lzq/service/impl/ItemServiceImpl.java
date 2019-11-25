package com.lzq.service.impl;
import	java.awt.print.Pageable;
import	java.util.Map;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.lzq.enums.CommentLevel;
import com.lzq.mapper.*;
import com.lzq.pojo.*;
import com.lzq.service.CarouselService;
import com.lzq.service.ItemService;
import com.lzq.utils.PagedGridResult;
import com.lzq.vo.CommentLevelCountsVO;
import com.lzq.vo.ItemCommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Objects;

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
        List<ItemCommentVO> itemCommentVOS = commentsMapperCustom.queryItemComments(map);
       PageInfo pageInfo = new PageInfo(itemCommentVOS);
        PagedGridResult result = PagedGridResult.builder().page(page).rows(itemCommentVOS)
                .total(pageInfo.getPages()).records(pageInfo.getTotal()).build();
        return result;
    }
}
