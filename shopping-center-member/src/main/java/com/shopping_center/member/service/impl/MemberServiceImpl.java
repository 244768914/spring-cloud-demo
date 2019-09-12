package com.shopping_center.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping_center.member.bean.dto.RegisterDTO;
import com.shopping_center.member.bean.vo.MemberVO;
import com.shopping_center.member.entity.Member;
import com.shopping_center.member.mapper.MemberMapper;
import com.shopping_center.member.service.IMemberService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    /*@Resource
    private UserDao userDao;*/

    @Override
    public Member selectById(Long id) {
        return this.getById(id);

    }

    @Override
    public MemberVO register(RegisterDTO registerDTO) {
        //验证手机验证码

        Member member = new Member();
        member.setMobile(registerDTO.getMobile());
        member.setCreateTime(new Date());
        member.setState(1);
        member.setMemberNo(UUID.randomUUID().toString());
        member.setPassword(DigestUtils.sha256Hex(registerDTO.getPassword()));
        this.baseMapper.insert(member);
        return null;
    }
}
