package com.czc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czc.domain.PatientMedicine;
import com.czc.mapper.PatientMedicineMapper;
import com.czc.service.PatientMedicineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientMedicineServiceImpl extends ServiceImpl<PatientMedicineMapper,PatientMedicine> implements PatientMedicineService {
    @Override
    public void insertPatientMedicine(PatientMedicine patientMedicine) {
        this.baseMapper.insertPatientMedicine(patientMedicine);
    }

    @Override
    public List<Integer> selectMedicineIdByPatientId(Integer pId) {
        return this.baseMapper.selectMedicineIdByPatientId(pId);
    }

    @Override
    public void deletePatientMedicineByPatientId(Integer pId) {
        this.baseMapper.deletePatientMedicineByPatientId(pId);
    }

    @Override
    public void deletePatientMedicineByMedicineId(Integer mId) {
        this.baseMapper.deletePatientMedicineByMedicineId(mId);
    }
}
