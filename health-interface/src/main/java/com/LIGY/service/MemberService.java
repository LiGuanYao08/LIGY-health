package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.pojo.Member;

import java.util.List;

public interface MemberService {
    //新增会员
    void add(Member member);
    //查询会员信息通过电话号
    Member findByTelephone(String telephone);
    //根据list的各个月份查询统计会员数
    List<Integer> findMemberCountByMonth(List<String> month);

     public void delete(Integer id);

     public void edit(Member member);
     public Member findMemberById(Integer id);
    //编辑功能
    public Member findById(Integer id);

    //查询所有
    public List<Member> findAll();


    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param queryString
     * @return
     */
    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);
}
