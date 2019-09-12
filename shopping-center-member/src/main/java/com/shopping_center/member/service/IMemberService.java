package com.shopping_center.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.shopping_center.member.bean.dto.RegisterDTO;
import com.shopping_center.member.bean.vo.MemberVO;
import com.shopping_center.member.entity.Member;

public interface IMemberService extends IService<Member> {
    /**
     * 根据主键id查询用户信息
     * @param id 主键id
     * @return 用户需要的信息DTO
     */
    Member selectById(Long id);

    MemberVO register(RegisterDTO registerDTO);
}
