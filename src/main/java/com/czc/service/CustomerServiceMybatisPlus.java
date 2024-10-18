package com.czc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czc.domain.Customer;
import com.czc.domain.Dto.CustomerAndAuthority;
import com.czc.domain.Medicine;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


public interface CustomerServiceMybatisPlus extends IService<Customer> {

    IPage<Customer> getCustomer(Map<String, Object> params);
    void insertCustomer(Customer customer);
    int getIdByUsername(String username);
    IPage<CustomerAndAuthority> getCustomerAuthority(Map<String, Object> params);
}
