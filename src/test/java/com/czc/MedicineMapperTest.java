package com.czc;

import com.czc.mapper.MedicineMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MedicineMapperTest {
    @Autowired
    private MedicineMapper medicineMapper;

    @Test
    public void testGetMedical() {
        System.out.println(medicineMapper.getMedicine(1));
    }
}
