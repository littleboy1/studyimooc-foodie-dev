package com.lzq.enums;

import lombok.Getter;

@Getter
public enum CommentLevel {

    GOOD(1,"好评"),
    NORMAL(2,"中评"),
    BAD(3,"差评"),
    ;

    private Integer type;

    private String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
