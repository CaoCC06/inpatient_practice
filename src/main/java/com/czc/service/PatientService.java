package com.czc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czc.domain.Dto.PatientAndMedicineDto;
import com.czc.domain.Patient;

import java.util.List;
import java.util.Map;

public interface PatientService extends IService<Patient> {
    IPage<Patient> getPatientAndWard(Map<String, Object> params);

    List<Patient> getWardGroup();

    int getPatientCount();

    List<PatientAndMedicineDto> getPatientAndMedicine();

    Patient getPatientById(int pId);

    int getPatientCountByWardId(int wId);
}
