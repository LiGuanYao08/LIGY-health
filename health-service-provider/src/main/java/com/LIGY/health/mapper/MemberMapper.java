package com.LIGY.health.mapper;

import com.LIGY.pojo.Member;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MemberMapper {
    //查询所有会员
    List<Member> findAll();
    //分页查询显示会员
    Page<Member> selectByCondition(String queryString);
    //新增会员
    void add(Member member);
    //根据id删除会员
    void delete(Integer id);
    //根据id查询会员
    Member findById(Integer id);
    //根据电话号查询会员
    Member findByTelephone(String telephone);
    //修改会员信息
    void edit(Member member);
    public Member findMemberById(Integer id);

    //根据日期获取前会员总数
    Integer findMemberCountBeforeDate(String date);
    //根据日期获取现有会员总数
    Integer findMemberCountByDate(String date);
    //根据日期获取后会员总数
    Integer findMemberCountAfterDate(String date);
    //查询会员总数
    Integer findMemberTotalCount();

    //查询所有
    @Select("select * from t_member")
    List<Member> findMemberById();




}
