package com.lzq.enums;

import lombok.Getter;

@Getter
public enum YesOrNo {

    NO(0,"否"),
    YES(1,"是"),

    ;

    private Integer type;

    private String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
