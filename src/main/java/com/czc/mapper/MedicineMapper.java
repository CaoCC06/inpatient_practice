package com.czc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.czc.domain.Dto.MedicineAndPatientDto;
import com.czc.domain.Medical;
import com.czc.domain.Medicine;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MedicineMapper extends BaseMapper<Medicine> {

    // 添加其他自定义方法
    Medicine getMedicine(Integer id);

    //多条件
    @Select("SELECT * FROM t_medicine ${ew.customSqlSegment}")
    @Results({
            @Result(property = "meId", column = "me_id"),
            @Result(property = "medical", column = "me_id",
                    one = @One(select = "com.czc.mapper.MedicalMapper.selectById"))
    })
    IPage<Medicine> getMedicineAndMedical(IPage page, @Param(Constants.WRAPPER) QueryWrapper queryWrapper);

    @Select("SELECT m_name,efficacy,picture FROM t_medicine")
    List<Medicine> getMedicineNameAndEfficacyList();

    @Select("SELECT m_name,inventory FROM t_medicine")
    List<Medicine> getMedicineForChart();

    @Select("SELECT me_id,SUM(inventory) inventory FROM t_medicine GROUP BY me_id")
    List<Medicine> getMedicineInventoryGroupByMeId();

    @Select("SELECT COUNT(*) FROM t_medicine")
    int getMedicineCount();

    @Select("SELECT m_id,m_name,picture FROM t_medicine")
    List<Medicine> getMedicinePictureList();

    @Update("UPDATE t_medicine SET picture = #{picture} WHERE m_id = #{mId}")
    void updateMedicinePicture(@Param("mId") Integer mId, @Param("picture") String picture);


    @Select("SELECT m.m_id,m.m_name,pm.id FROM t_patient_medicine as pm LEFT JOIN t_medicine as m ON pm.m_id = m.m_id WHERE pm.p_id = #{p_id}")
    List<MedicineAndPatientDto> getMedicineIdAndNameList(@Param("p_id") String id);
}

