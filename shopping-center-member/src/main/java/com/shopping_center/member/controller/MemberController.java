package com.shopping_center.member.controller;

import com.shopping_center.member.bean.dto.RegisterDTO;
import com.shopping_center.member.bean.vo.UserVO;
import com.shopping_center.Result;
import com.shopping_center.member.entity.Member;
import com.shopping_center.member.service.IMemberService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RequestMapping("/api/member")
@RestController
public class MemberController {


    private static final int MINUTE = 1;
    @Autowired
    private IMemberService memberService;


    @Autowired
    private RedisTemplate redisTemplate;



    @GetMapping("/{id}")
    @ApiOperation(value = "查询会员信息", notes = "查询会员信息")
    public Result<Member> findById(@PathVariable("id") Long id){
        Member member = memberService.selectById(id);
        return Result.success(member);
    }


    @PostMapping("/setRedisKey")
    @ApiOperation(value = "redis测试设置", notes = "获取站点信息")
    public Result setRedisKey(@RequestBody UserVO vo){
        String key="test";
        redisTemplate.opsForValue().set(key, vo, MINUTE, TimeUnit.MINUTES);
        return Result.success();
    }

    @PostMapping("/register")
    @ApiOperation(value = "会员注册", notes = "会员注册")
    public Result<Member> register(@RequestBody RegisterDTO registerDTO){
        memberService.register(registerDTO);
        return Result.success();
    }


}
