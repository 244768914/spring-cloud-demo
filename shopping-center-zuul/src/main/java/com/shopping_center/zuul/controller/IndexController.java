/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.shopping_center.zuul.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		System.out.println("=================网关测试=============");

		return "index";
	}

}
