package com.lzq.bo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBo {

    private String username;
    private String password;
    private String confirmPassword;


}
