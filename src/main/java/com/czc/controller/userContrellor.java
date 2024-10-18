package com.czc.controller;

import com.czc.domain.Medicine;
import com.czc.service.MedicineService;
import com.czc.service.PatientService;
import com.czc.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

@Controller
public class userContrellor {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PatientService patientService;

    @GetMapping({"/","/page/{pageNo}"})
    public String toUserIndex(@PathVariable(name = "pageNo",required = false)Integer pageNo ,
                              Model model){
        PageHelper.startPage(pageNo == null ? 1 : pageNo,8);
        PageInfo<Medicine> pageInfo = new PageInfo<>(medicineService.getMedicineNameAndEfficacyList());
        model.addAttribute("medicinesList",pageInfo);
        return "client/index";
    }

    @GetMapping("/MedicineData")
    public @ResponseBody R toMedicineDataForChart(){
        //图表
        return R.ok().put("data", medicineService.getMedicineForChart());
    }


    @GetMapping("/admin")
    public String toAdminIndex(){
        return "back/index";
    }

    @GetMapping("/back/page/welcome.html")
    public String toWelcome(Model model){
        model.addAttribute("patientCount",patientService.getPatientCount());
        model.addAttribute("medicineCount",medicineService.getMedicineCount());
        return "back/page/welcome";
    }

    @GetMapping("/getuserByContext")
    public @ResponseBody R getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        System.out.println("userDetails: "+context);
        Authentication authentication = context.getAuthentication();
        UserDetails principal = (UserDetails)authentication.getPrincipal();
        System.out.println(principal);
        System.out.println("username: "+principal.getUsername());
        return  R.ok().put("username", principal.getUsername());
    }


    @GetMapping("/MedicineInventory")
    public @ResponseBody R toMedicineInventoryGroupByMeId(){
        return  R.ok().put("data", medicineService.getMedicineInventoryGroupByMeId());
    }

    @GetMapping("/PatientWard")
    public @ResponseBody R toPatientData(){
        return  R.ok().put("data", patientService.getWardGroup());
    }


    @GetMapping("/userLogin")
    public String toLogin(){
        return "back/page/login/backlogin";
    }
}
