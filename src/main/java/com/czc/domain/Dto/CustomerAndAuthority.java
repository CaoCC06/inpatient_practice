package com.czc.domain.Dto;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class CustomerAndAuthority {
    private Integer id;
    private String username;
    private String password;
    private String authority;
}
