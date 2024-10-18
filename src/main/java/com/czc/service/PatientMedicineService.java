package com.czc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czc.domain.PatientMedicine;

import java.util.List;

public interface PatientMedicineService extends IService<PatientMedicine> {
    void insertPatientMedicine(PatientMedicine patientMedicine);

    List<Integer> selectMedicineIdByPatientId(Integer pId);

    void deletePatientMedicineByPatientId(Integer pId);

    void deletePatientMedicineByMedicineId(Integer mId);
}
