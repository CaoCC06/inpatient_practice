package com.czc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czc.domain.Customer;
import com.czc.domain.Dto.CustomerAndAuthority;
import com.czc.mapper.CustomerMapper;
import com.czc.service.CustomerServiceMybatisPlus;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomerServiceMybatisPlusImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerServiceMybatisPlus {

    @Override
    public IPage<Customer> getCustomer(Map<String, Object> params) {
        IPage<Customer> page = new Page<>(Integer.parseInt((String)params.get("page")), Integer.parseInt((String)params.get("limit")));
        return this.baseMapper.getCustomer(page);
    }

    @Override
    public void insertCustomer(Customer customer) {
        this.baseMapper.insertCustomer(customer);
    }

    @Override
    public int getIdByUsername(String username) {
        return this.baseMapper.getIdByUsername(username);
    }

    @Override
    public IPage<CustomerAndAuthority> getCustomerAuthority(Map<String, Object> params) {
        IPage<Customer> page = new Page<>(Integer.parseInt((String)params.get("page")), Integer.parseInt((String)params.get("limit")));
        return this.baseMapper.getCustomerAuthority(page);
    }
}
