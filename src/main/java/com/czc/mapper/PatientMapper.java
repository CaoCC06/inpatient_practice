package com.czc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.czc.domain.Dto.PatientAndMedicineDto;
import com.czc.domain.Patient;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PatientMapper extends BaseMapper<Patient> {
    // 查询所有患者
    @Select("SELECT * FROM t_patient ${ew.customSqlSegment}")
    @Results({
            @Result(property = "wId", column = "w_id"),
            @Result(property = "ward", column = "w_id",
                    one = @One(select = "com.czc.mapper.WardMapper.selectById"))
    })
    IPage<Patient> getPatientAndWard(IPage page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    @Select("SELECT w_id,COUNT(w_id) symptom FROM t_patient GROUP BY w_id")
    List<Patient> getWardGroup();

    @Select("SELECT COUNT(*) FROM t_patient")
    int getPatientCount();

    @Select("SELECT * FROM t_patient WHERE p_id = #{pId}")
    Patient getPatientById(int pId);

    @Select("SELECT p_id,p_name FROM t_patient")
    @Results({
            @Result(property = "pId", column = "p_id"),
            @Result(
                    property = "medicines", column = "p_id",
                    javaType = List.class,
                    many = @Many(select = "com.czc.mapper.MedicineMapper.getMedicineIdAndNameList")
            )
    })
    List<PatientAndMedicineDto> getPatientAndMedicine();

    @Select("SELECT count(*) FROM t_patient WHERE w_id = #{wId}")
    int getPatientCountByWardId(int wId);
}

