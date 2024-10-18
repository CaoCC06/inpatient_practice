package com.czc.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.czc.domain.Medical;

import java.util.List;

public interface MedicalService extends IService<Medical> {
    List<Medical> getAllMedical();
}
