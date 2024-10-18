package com.czc.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.czc.domain.Medical;
import com.czc.domain.Ward;
import com.czc.mapper.WardMapper;
import com.czc.service.WardService;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Map;

@Service
public class WardServiceImpl  extends ServiceImpl<WardMapper, Ward> implements WardService {
    @Override
    public IPage<Ward> getWard(Map<String, Object> params) {
        IPage<Medical> page = new Page<>(Integer.parseInt((String)params.get("page")), Integer.parseInt((String)params.get("limit")));
        QueryWrapper<Medical> queryWrapper = new QueryWrapper<>();
        if (params.get("searchParams") != null){
            Map<String,Object> searchParamsMap = (Map) JSON.parse((String) params.get("searchParams"));
            queryWrapper.like(!StringUtils.isEmpty((String)searchParamsMap.get("wId")),"w_id", searchParamsMap.get("wId"))
                    .eq(!StringUtils.isEmpty((String)searchParamsMap.get("size")),"size", searchParamsMap.get("size"))
                    .like(!StringUtils.isEmpty((String)searchParamsMap.get("WClass")),"w_class", searchParamsMap.get("WClass"));
        }
        return this.baseMapper.getWard(page, queryWrapper);
    }
}
