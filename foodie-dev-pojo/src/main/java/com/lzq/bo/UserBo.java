package com.lzq.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel(value = "用户对象BO",description ="客户端传入的数据封装的对象")
public class UserBo {

    @ApiModelProperty(value = "用户名",name = "username",example ="lzq",required =true)
    private String username;
    @ApiModelProperty(value = "输入密码",name = "password",example ="lzq",required =true)
    private String password;
    @ApiModelProperty(value = "确认密码",name = "confirmPassword",example ="lzq",required =true)
    private String confirmPassword;


}
