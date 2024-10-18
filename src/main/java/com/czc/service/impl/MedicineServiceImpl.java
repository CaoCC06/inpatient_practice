package com.czc.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czc.domain.Medical;
import com.czc.domain.Medicine;
import com.czc.mapper.MedicineMapper;
import com.czc.service.MedicineService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Map;

@Service
public class MedicineServiceImpl extends ServiceImpl<MedicineMapper, Medicine> implements MedicineService {
    @Override
    public IPage<Medicine> getMedicineAndMedical(Map<String, Object> params) {
        IPage<Medical> page = new Page<>(Integer.parseInt((String)params.get("page")), Integer.parseInt((String)params.get("limit")));
        QueryWrapper<Medical> queryWrapper = new QueryWrapper<>();
        if (params.get("searchParams") != null){
            Map<String,Object> searchParamsMap = (Map) JSON.parse((String) params.get("searchParams"));
            queryWrapper.like(!StringUtils.isEmpty((String)searchParamsMap.get("mname")),"m_name", searchParamsMap.get("mname"))
                    .like(!StringUtils.isEmpty((String)searchParamsMap.get("frequency")),"frequency", searchParamsMap.get("frequency"))
                    .eq(!StringUtils.isEmpty((String)searchParamsMap.get("packing")),"packing", searchParamsMap.get("packing"))
                    .like(!StringUtils.isEmpty((String)searchParamsMap.get("efficacy")),"efficacy", searchParamsMap.get("efficacy"));
        }
        return this.baseMapper.getMedicineAndMedical(page, queryWrapper);
    }

    @Override
    public List<Medicine> getMedicineNameAndEfficacyList() {
        return this.baseMapper.getMedicineNameAndEfficacyList();
    }

    @Override
    public List<Medicine> getMedicineForChart() {
        return this.baseMapper.getMedicineForChart();
    }

    @Override
    public List<Medicine> getMedicineInventoryGroupByMeId() {
        return this.baseMapper.getMedicineInventoryGroupByMeId();
    }

    @Override
    public int getMedicineCount() {
        return this.baseMapper.getMedicineCount();
    }

    @Override
    public List<Medicine> getMedicinePictureList() {
        return this.baseMapper.getMedicinePictureList();
    }

    @Override
    public void updateMedicinePicture(Integer mId, String picture) {
        this.baseMapper.updateMedicinePicture(mId, picture);
    }
}

