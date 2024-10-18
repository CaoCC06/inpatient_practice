package com.czc.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czc.domain.Dto.PatientAndMedicineDto;
import com.czc.domain.Patient;
import com.czc.domain.PatientMedicine;
import com.czc.domain.Ward;
import com.czc.mapper.PatientMapper;
import com.czc.service.PatientService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {
    @Override
    public IPage<Patient> getPatientAndWard(Map<String, Object> params) {
        IPage<Ward> page = new Page<>(Integer.parseInt((String)params.get("page")), Integer.parseInt((String)params.get("limit")));
        QueryWrapper<Ward> queryWrapper = new QueryWrapper<>();
        if (params.get("searchParams") != null){
            Map<String,Object> searchParamsMap = (Map) JSON.parse((String) params.get("searchParams"));
            queryWrapper.like(!StringUtils.isEmpty((String)searchParamsMap.get("pname")),"p_name", searchParamsMap.get("pname"))
                    .eq(!StringUtils.isEmpty((String)searchParamsMap.get("psex")),"p_sex", searchParamsMap.get("psex"))
                    .eq(!StringUtils.isEmpty((String)searchParamsMap.get("wid")),"w_id", searchParamsMap.get("wid"))
                    .like(!StringUtils.isEmpty((String)searchParamsMap.get("wclass")),"w_class", searchParamsMap.get("wclass"));
        }
        return this.baseMapper.getPatientAndWard(page, queryWrapper);
    }

    @Override
    public List<Patient> getWardGroup() {
        return this.baseMapper.getWardGroup();
    }

    @Override
    public int getPatientCount() {
        return this.baseMapper.getPatientCount();
    }

    @Override
    public List<PatientAndMedicineDto> getPatientAndMedicine() {
        return this.baseMapper.getPatientAndMedicine();
    }

    @Override
    public Patient getPatientById(int pId) {
        return this.baseMapper.getPatientById(pId);
    }

    @Override
    public int getPatientCountByWardId(int wId) {
        return this.baseMapper.getPatientCountByWardId(wId);
    }

}
