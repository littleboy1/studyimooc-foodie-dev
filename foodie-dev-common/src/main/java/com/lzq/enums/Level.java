package com.lzq.enums;

import lombok.Getter;

@Getter
public enum Level {

    ROOT(1,"跟分类"),
    TWO(2,"二级分类"),
    THREE(3,"三级分类"),
    ;

    private Integer type;

    private String value;

    Level(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
