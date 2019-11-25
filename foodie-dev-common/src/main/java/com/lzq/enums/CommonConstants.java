package com.lzq.enums;

import lombok.Getter;

@Getter
public enum CommonConstants {

    DEFAULT_PAGE(1,"默认页数"),
    DEFAULT_PAGE_SIZE(10,"中评")
    ;

    private Integer value;

    private String desc;

    CommonConstants(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
