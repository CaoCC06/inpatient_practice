package com.czc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czc.domain.CustomerAuthority;
import com.czc.mapper.CustomerAuthorityMapper;
import com.czc.service.CustomerAuthorityService;
import org.springframework.stereotype.Service;

@Service
public class CustomerAuthorityServiceImpl extends ServiceImpl<CustomerAuthorityMapper, CustomerAuthority> implements CustomerAuthorityService {
    @Override
    public CustomerAuthority selectByCustomerId(Integer customerId) {
        return this.baseMapper.selectByCustomerId(customerId);
    }

    @Override
    public void updateByCustomerId(Integer customerId, Integer authorityId) {
        this.baseMapper.updateByCustomerId(customerId, authorityId);
    }
}
