package com.LIGY.health.service.impl;

import com.LIGY.entity.PageResult;
import com.LIGY.health.mapper.MemberMapper;
import com.LIGY.pojo.Member;
import com.LIGY.service.MemberService;
import com.LIGY.utils.MD5Utils;
import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 会员服务
 */
@Service(interfaceClass = MemberService.class)
@Transactional
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberMapper memberMapper;
    //新增会员
    @Override
    public void add(Member member) {
        if (member.getPassword()!=null){
            member.setPassword(MD5Utils.md5(member.getPassword()));
        }
        memberMapper.add(member);
    }


    //根据手机号查询会员
    @Override
    public Member findByTelephone(String telephone) {
        return memberMapper.findByTelephone(telephone);
    }

    //根据月份来统计会员数
    @Override
    public List<Integer> findMemberCountByMonth(List<String> month) {
        ArrayList<Integer> list = new ArrayList<>();
        for (String m : month) {
            m = m + "-31";
            Integer count = memberMapper.findMemberCountBeforeDate(m);
            list.add(count);
        }
        return list;
    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage,pageSize);
        Page<Member> page = memberMapper.selectByCondition(queryString);
        return new PageResult(page.getTotal(),page.getResult());
    }
    //查询所有
    @Override
    public List<Member> findAll() {
        return memberMapper.findAll();
    }

    @Override
    public Member findById(Integer id) {
        return memberMapper.findById(id);
    }


    //  删除
    @Override
    public void delete(Integer id) {
        memberMapper.delete(id);
    }
    //编辑
    @Override
    public void edit(Member member) {
        memberMapper.edit(member);
    }
    //查询
    @Override
    public Member findMemberById(Integer id) {return memberMapper.findMemberById(id);
    }


}
