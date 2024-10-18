package com.czc.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.czc.domain.Medicine;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;


public interface MedicineService extends IService<Medicine> {
    IPage<Medicine> getMedicineAndMedical(Map<String, Object> params);

    List<Medicine> getMedicineNameAndEfficacyList();

    List<Medicine> getMedicineForChart();

    List<Medicine> getMedicineInventoryGroupByMeId();

    int getMedicineCount();

    List<Medicine> getMedicinePictureList();

    void updateMedicinePicture(Integer mId,String picture);
}
