package com.czc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.czc.domain.PatientMedicine;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PatientMedicineMapper extends BaseMapper<PatientMedicine> {
    @Insert("INSERT INTO t_patient_medicine(m_id,p_id) VALUES(#{mId},#{pId})")
    void insertPatientMedicine(PatientMedicine patientMedicine);

    @Select("SELECT m_id FROM t_patient_medicine WHERE p_id = #{pId}")
    List<Integer> selectMedicineIdByPatientId(Integer pId);

    @Delete("DELETE FROM t_patient_medicine WHERE p_id = #{pId}")
    void deletePatientMedicineByPatientId(Integer pId);

    @Delete("DELETE FROM t_patient_medicine WHERE m_id = #{mId}")
    void deletePatientMedicineByMedicineId(Integer mId);
}
