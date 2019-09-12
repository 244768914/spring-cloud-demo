package com.shopping_center.member.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
@Data
public class RegisterDTO  implements Serializable {

    @ApiModelProperty(value = "手机号码")
    @NotNull
    private String mobile;


    @ApiModelProperty(value = "密码")
    @NotNull
    private String password;

    @NotNull
    @ApiModelProperty(value = "手机验证码")
    private String mobileCode;





}
