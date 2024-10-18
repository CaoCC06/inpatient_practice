package com.czc.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czc.domain.Medical;
import com.czc.mapper.MedicalMapper;
import com.czc.service.MedicalService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalServiceImpl extends ServiceImpl<MedicalMapper, Medical> implements MedicalService {
    @Override
    public List<Medical> getAllMedical() {
        return this.baseMapper.getAllMedical();
    }
}
