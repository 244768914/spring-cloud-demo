/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.shopping_center;

import com.shopping_center.enums.ResultEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 接口返回对象实体
 * 
 * @author wujing
 * @param <T>
 */
@ApiModel
@Data
@AllArgsConstructor
public final class Result<T extends Serializable> implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(Result.class);

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(example = "true", notes = "是否成功")
	private boolean isSuccess;

	/**
	 * 错误码
	 */
	@ApiModelProperty(example = "200", notes = "返回编码")
	private Integer code = ResultEnum.ERROR.getCode();

	/**
	 * 返回信息
	 */

	@ApiModelProperty(example = "操作成功", notes = "返回信息")
	private String msg = null;

	/**
	 * 返回结果实体
	 */

	@ApiModelProperty(example = "this is data", notes = "返回数据")
	private T data = null;



	public static <T extends Serializable> Result<T> error(String msg) {
		logger.debug("返回错误：code={}, msg={}", ResultEnum.ERROR.getCode(), msg);
		return new Result<T>(Boolean.FALSE,ResultEnum.ERROR.getCode(), msg, null);
	}

	public static <T extends Serializable> Result<T> error(ResultEnum resultEnum) {
		logger.debug("返回错误：code={}, msg={}", resultEnum.getCode(), resultEnum.getDesc());
		return new Result<T>(Boolean.FALSE,resultEnum.getCode(), resultEnum.getDesc(), null);
	}

	public static <T extends Serializable> Result<T> error(int code, String msg) {
		logger.debug("返回错误：code={}, msg={}", code, msg);
		return new Result<T>(Boolean.FALSE,code, msg, null);
	}
	public static <T extends Serializable> Result<T> success() {
		return new Result<T>(Boolean.TRUE, ResultEnum.SUCCESS.getCode(),"",null);
	}

	public static <T extends Serializable> Result<T> success(T data) {
		return new Result<T>(Boolean.TRUE, ResultEnum.SUCCESS.getCode(), "", data);
	}

	public static <T extends Serializable> Result<T> success(T data,String msg) {
		return new Result<T>(Boolean.TRUE, ResultEnum.SUCCESS.getCode(), msg, data);
	}



}
