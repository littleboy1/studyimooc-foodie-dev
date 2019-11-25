package com.lzq.mapper;


import com.lzq.vo.ItemCommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapperCustom {

List<ItemCommentVO> queryItemComments(@Param("paramsMap")Map<String,Object> paramsMap);
}