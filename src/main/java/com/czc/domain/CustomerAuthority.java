package com.czc.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("t_customer_authority")
public class CustomerAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId
    private Integer id;
    /**
     * 客户ID
     */
    private Integer customerId;

    /**
     * 权限ID
     */
    private Integer authorityId;
}
