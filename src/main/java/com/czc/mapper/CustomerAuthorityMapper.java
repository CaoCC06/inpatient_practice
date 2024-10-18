package com.czc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czc.domain.CustomerAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerAuthorityMapper extends BaseMapper<CustomerAuthority> {
    @Select("select * from t_customer_authority where customer_id = #{customerId}")
    CustomerAuthority selectByCustomerId(Integer customerId);

    @Update("update t_customer_authority set authority_id = #{authorityId} where customer_id = #{customerId}")
    void updateByCustomerId(Integer customerId, Integer authorityId);
}
