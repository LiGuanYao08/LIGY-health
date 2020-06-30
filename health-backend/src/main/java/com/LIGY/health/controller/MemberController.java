package com.LIGY.health.controller;

import com.LIGY.constant.MessageConstant;
import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.entity.Result;
import com.LIGY.pojo.Member;
import com.LIGY.service.MemberService;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    @Reference
     private MemberService memberService;

    @RequestMapping("/add")
    public Result addMember(@RequestBody Member member) {
        if (member == null) {
            return new Result(false, MessageConstant.ADD_USER_FAIL);
        }
        try {
            memberService.add(member);
            return new Result(true, MessageConstant.ADD_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_USER_FAIL);
        }
    }


    @RequestMapping("/findPage")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult pageResult = memberService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
        return pageResult;
    }

    @RequestMapping("/findMemberById")
    public Result findMemberById(Integer id) {
        try {
            Member member = memberService.findMemberById(id);
            return new Result(true, MessageConstant.GET_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.GET_USER_FAIL);
        }
    }
    //编辑功能
    @RequestMapping("/findById")
    public Result findById(Integer id){
        Member member = memberService.findById(id);
        if (member!=null){
            Result result = new Result(true,MessageConstant.QUERY_USER_SUCCESS);
            result.setData(member);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_USER_FAIL);
    }

    //编辑
    @RequestMapping("/edit")
    public Result editMemberById(@RequestBody Member member) {
        try {
            memberService.edit(member);
            return new Result(true, MessageConstant.UPDATE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.UPDATE_USER_FAIL);
        }
    }

    //删除会员
    @RequestMapping("/delete")
    public Result delete(Integer id) {
        try {
            memberService.delete(id);
            return new Result(true, MessageConstant.DELETE_USER_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_USER_FAIL);
        }
    }

    //查询所有检查项数据
    @RequestMapping("/findAll")
    public Result findAll(){
        List<Member> membersList = memberService.findAll();
        if (membersList != null && membersList.size() > 0){
            Result result = new Result(true,MessageConstant.QUERY_USER_SUCCESS);
            result.setData(membersList);
            return result;
        }
        return new Result(false,MessageConstant.QUERY_USER_FAIL);
    }
}
