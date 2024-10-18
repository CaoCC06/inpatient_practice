package com.czc;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.czc.domain.Medicine;
import com.czc.service.MedicineService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MedicineServiceTest {
    @Autowired
    private MedicineService medicineService;

    @Test
    public void testGetMedicineList() {
        medicineService.list();
    }
//查询其中能治疗头痛的，且是胶囊的药
    @Test
    public void testGetMedicineConditions() {
        QueryWrapper<Medicine> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("efficacy", "头痛")
                .eq("packing", "胶囊");
        medicineService.list(queryWrapper);
    }

//    分页查询，查询一日三次的药,并且分页，一页两个
@Test
public void testGetMedicineConditionsPage() {
    QueryWrapper<Medicine> queryWrapper = new QueryWrapper<>();
    queryWrapper.like("frequency", "一日三次");
    IPage<Medicine> page = new Page<>(1, 2);
//    medicineService.page(page, queryWrapper);
    System.out.println("总页数："+page.getPages());
    System.out.println("总记录数："+page.getTotal());
    System.out.println(page.getRecords());
    List<Medicine> medicineList = page.getRecords();
    for (Medicine medicine : medicineList){
        System.out.println(medicine.getMName());
        System.out.println(medicine.getMeId());
        }
    }

}
