package com.LIGY.service;

import com.LIGY.entity.PageResult;
import com.LIGY.entity.QueryPageBean;
import com.LIGY.pojo.Company;

import java.util.List;

public interface CompanyListService {
    PageResult pageQuery(QueryPageBean queryPageBean);

    void add(Company company);

    void delete(Integer id);

    List<Company> findAll();

    String[] findAllAddress();

    Company findAllAddress(String companyAddress);
}
