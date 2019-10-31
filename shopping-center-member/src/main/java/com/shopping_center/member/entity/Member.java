package com.shopping_center.member.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2019-08-09
 */
/*@Data
@TableName("member")*/

@Data
//@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)
public class Member  implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 状态(0为正常，1为禁用）
     */
    private Integer state;


    /**
     * 会员唯一编号
     */
    private String memberNo;


}
