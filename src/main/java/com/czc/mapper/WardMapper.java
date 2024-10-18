package com.czc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.czc.domain.Medicine;
import com.czc.domain.Ward;
import org.apache.ibatis.annotations.*;

@Mapper
public interface WardMapper extends BaseMapper<Ward> {
    @Select("SELECT * FROM t_ward ${ew.customSqlSegment}")
    IPage<Ward> getWard(IPage page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);
}
