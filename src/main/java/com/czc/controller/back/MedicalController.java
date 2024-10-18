package com.czc.controller.back;

import java.util.Arrays;
import java.util.Map;


import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.czc.domain.Medical;
import com.czc.service.MedicalService;
import com.czc.utils.R;



/**
 *
 *
 * @author caozican
 * @email czc@qq.com
 * @date 2024-04-28 13:13:19
 */
@Controller
@RequestMapping("back/page/admin/medical")
public class MedicalController {
    @Autowired
    private MedicalService medicalService;


    @GetMapping("/all")
    public @ResponseBody R all(){
        return R.ok().put("data", medicalService.getAllMedical());
    }
}
