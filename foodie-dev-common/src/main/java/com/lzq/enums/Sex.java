package com.lzq.enums;

import lombok.Getter;

@Getter
public enum Sex {

    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密"),
    ;

    private Integer type;

    private String value;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
