package com.shopping_center.member.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shopping_center.member.bean.dto.RegisterDTO;
import com.shopping_center.member.bean.vo.MemberVO;
import com.shopping_center.member.entity.Member;
import com.shopping_center.member.mapper.MemberMapper;
import com.shopping_center.member.service.IMemberService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

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
        MemberVO memberVO = new MemberVO();
        //验证手机验证码
        Member member = new Member();
        member.setMobile(registerDTO.getMobile());
        member.setCreateTime(new Date());
        member.setState(1);
        member.setMemberNo(UUID.randomUUID().toString());
        member.setPassword(DigestUtils.sha256Hex(registerDTO.getPassword()));
        this.baseMapper.insert(member);
        BeanUtils.copyProperties(member,memberVO );
        return memberVO;
    }

    @PostConstruct
    public void visualVM() throws InterruptedException {
       Map<Integer,Integer> map=new HashMap<>();
       int i=0;
        for (int j=0;j<1000;j++) {
            Thread.sleep(1000);
            while (i<2000000){
                i++;
                try{
                    map.put(i,i);

                }catch (OutOfMemoryError e){
                    e.printStackTrace();
                    break;
                }


            }

        }
    }
}
