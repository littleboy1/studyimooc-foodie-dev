package com.lzq.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentLevelCountsVO {
    public Integer totalCounts;
    public Integer goodCounts;
    public Integer normalCounts;
    public Integer badCounts;
}
