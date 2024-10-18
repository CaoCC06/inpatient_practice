package com.czc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czc.domain.Medical;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MedicalMapper extends BaseMapper<Medical> {
    @Select("SELECT * FROM t_medical")
    List<Medical> getAllMedical();
}
