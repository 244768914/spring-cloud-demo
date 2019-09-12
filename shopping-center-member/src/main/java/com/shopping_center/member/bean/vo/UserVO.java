package com.shopping_center.member.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserVO implements Serializable {


    /**
     * 用户ID
     */
    @ApiModelProperty(value = "主键id")
    private Long userId;



    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private String mobile;

    @ApiModelProperty("用户状态")
    private byte status;
}
