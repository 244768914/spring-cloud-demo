/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.shopping_center.zuul.common;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.http.ServletInputStreamWrapper;

import com.thoughtworks.xstream.core.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

/**
 * 请求开始前执行 登录拦截
 */
@Component
public class FilterPre extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(FilterPre.class);
	private static final String TOKEN = "token";

	/*@Autowired
	private StringRedisTemplate stringRedisTemplate;*/

	@Override
	public String filterType() {
		return FilterConstants.PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.PRE_DECORATION_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		String uri = RequestContext.getCurrentContext().getRequest().getServletPath();

		if (uri.startsWith("/callback")) {
			// 回调使用
			return false;
		}

		if (uri.startsWith("/zuul")) {
			// 文件上传
			return false;
		}

		if (!uri.startsWith("/auth")) {
			// 非token校验
			return false;
		}

		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		Long userNo = null;

		System.out.println("拦截");
		/*try {
			userNo = getUserNoByToken(request);
		} catch (BaseException e) {
			logger.error("系统异常", e.getMessage());
			resp(ctx, e.getMessage(), e.getCode());
		}

		// 参数封装
		try {
			ctx.setRequest(requestWrapper(request, userNo));
		} catch (IOException e) {
			logger.error("封装参数异常", e.getMessage());
			resp(ctx, "系统异常，请重试");
		}*/

		return null;
	}
    /*

	*//**
	 * token 拦截功能
	 *//*
	private Long getUserNoByToken(HttpServletRequest request) {
		String token = request.getHeader(TOKEN); // 检验token
		if (StringUtils.isEmpty(token)) { // token不存在，则从报文里面获取
			throw new BaseException("token不存在，请重新登录");
		}

		// 解析 token
		DecodedJWT jwt = null;
		try {
			jwt = JWTUtil.verify(token);
		} catch (Exception e) {
			logger.error("token异常，token={}", token.toString());
			throw new BaseException(ResultEnum.TOKEN_ERROR);
		}

		// 校验token
		if (null == jwt) {
			throw new BaseException(ResultEnum.TOKEN_ERROR);
		}
		Long userNo = JWTUtil.getUserNo(jwt);
		if (userNo <= 0) {
			throw new BaseException(ResultEnum.TOKEN_ERROR);
		}

		// 单点登录处理，注意，登录的时候必须要放入缓存
		*//*if (!stringRedisTemplate.hasKey(userNo.toString())) {
			// 不存在，则登录异常，有效期为1小时
			throw new BaseException(ResultEnum.TOKEN_PAST);
		}

		// 存在，判断是否token相同
		String tk = stringRedisTemplate.opsForValue().get(userNo.toString());
		if (!token.equals(tk)) {
			// 不同则为不同的用户登录，这时候提示异地登录
			throw new BaseException(ResultEnum.REMOTE_ERROR);
		}*//*

		// 更新时间，使token不过期
		stringRedisTemplate.opsForValue().set(userNo.toString(), token, 1, TimeUnit.HOURS);

		return userNo;
	}

	private HttpServletRequestWrapper requestWrapper(HttpServletRequest request, Long userNo) throws IOException {
		Map<String, Object> map = getParamMap(request);
		if (null == map) {
			map = new HashMap<>();
		}
		if (userNo != null) {
			map.put("userNo", userNo);
		}
		String newBody = JSONUtil.toJSONString(map);
		logger.info("转发参数={}", newBody);
		final byte[] reqBodyBytes = newBody.getBytes();
		return new HttpServletRequestWrapper(request) {

			@Override
			public BufferedReader getReader() throws IOException {
				return new BufferedReader(new InputStreamReader(getInputStream()));
			}

			@Override
			public ServletInputStream getInputStream() throws IOException {
				return new ServletInputStreamWrapper(reqBodyBytes);
			}

			@Override
			public int getContentLength() {
				return reqBodyBytes.length;
			}

			@Override
			public long getContentLengthLong() {
				return reqBodyBytes.length;
			}
		};
	}

	private void resp(RequestContext ctx, String msg) {
		ctx.getResponse().setCharacterEncoding("UTF-8");
		ctx.setResponseBody(JSONUtil.toJSONString(Result.error(msg)));
	}

	private void resp(RequestContext ctx, String msg, int code) {
		ctx.getResponse().setCharacterEncoding("UTF-8");
		ctx.setResponseBody(JSONUtil.toJSONString(Result.error(code, msg)));
	}

	@SuppressWarnings("unchecked")
	private static TreeMap<String, Object> getParamMap(HttpServletRequest request) {
		TreeMap<String, Object> paramMap = new TreeMap<>();
		Map<String, String[]> map = request.getParameterMap();
		for (String key : map.keySet()) {
			paramMap.put(key, map.get(key)[0]);
		}
		if (paramMap.isEmpty()) {
			DataInputStream in = null;
			try {
				in = new DataInputStream(request.getInputStream());
				byte[] buf = new byte[request.getContentLength()];
				in.readFully(buf);
				String t = new String(buf, "UTF-8");
				if (StringUtils.hasText(t)) {
					return new TreeMap<>(JSONUtil.parseObject(t, TreeMap.class));
				}
			} catch (Exception e) {
				logger.warn("获取不到任何参数");
			} finally {
				if (null != in)
					try {
						in.close();// 关闭数据流
					} catch (IOException e) {
					}
			}
		}
		return paramMap;
	}*/

}
