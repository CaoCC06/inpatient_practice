package com.czc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czc.domain.CustomerAuthority;

public interface CustomerAuthorityService extends IService<CustomerAuthority> {
    CustomerAuthority selectByCustomerId(Integer customerId);

    void updateByCustomerId(Integer customerId, Integer authorityId);
}
