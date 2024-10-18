package com.czc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czc.domain.Ward;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface WardService extends IService<Ward> {
    IPage<Ward> getWard(Map<String, Object> params);
}
