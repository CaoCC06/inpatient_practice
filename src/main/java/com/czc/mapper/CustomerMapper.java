package com.czc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.czc.domain.Customer;
import com.czc.domain.Dto.CustomerAndAuthority;
import com.czc.domain.Medicine;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CustomerMapper extends BaseMapper<Customer> {

    @Select("SELECT * FROM t_customer")
    IPage<Customer> getCustomer(IPage page);

    @Insert("INSERT INTO t_customer(username,password) VALUES(#{username},#{password})")
    void insertCustomer(Customer customer);

    @Select("SELECT id FROM t_customer WHERE username = #{username}")
    int getIdByUsername(String username);

    @Select("SELECT c.id,c.username,a.authority FROM t_customer as c " +
            "LEFT JOIN t_customer_authority as ca ON c.id = ca.customer_id " +
            "LEFT JOIN t_authority as a ON ca.authority_id = a.id")
    IPage<CustomerAndAuthority> getCustomerAuthority(IPage page);
}
