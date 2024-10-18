package com.czc.controller.back;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czc.domain.Dto.PatientAndMedicineDto;
import com.czc.domain.Medicine;
import com.czc.domain.Patient;
import com.czc.domain.PatientMedicine;
import com.czc.service.MedicineService;
import com.czc.service.PatientMedicineService;
import com.czc.service.PatientService;
import com.czc.service.WardService;
import com.czc.utils.R;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("back/page")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private WardService wardService;

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PatientMedicineService patientMedicineService;

    //进入病人管理页面
    @GetMapping("/admin/patient/list.html")
    public String tolist(){
        return "back/page/admin/patient/list";
    }
    //进入添加病人页面
    @GetMapping("/admin/patient/add.html")
    public String toadd(){
        return "back/page/admin/patient/add";
    }
    //进入修改病人页面
    @GetMapping("/admin/patient/edit.html")
    public String toedit(@RequestParam Integer pid, Model model){
        model.addAttribute("patient",patientService.getPatientById(pid));
        model.addAttribute("ward",wardService.list());
        return "back/page/admin/patient/edit";
    }
    //进入病人药物管理页面
    @GetMapping({"/admin/patient/patient2.html", "/admin/medicine/page/{pageNo}"})
    public String topicture(@PathVariable(name = "pageNo",required = false)Integer pageNo ,
                            Model model){
        PageHelper.startPage(pageNo == null ? 1 : pageNo,18);
        PageInfo<PatientAndMedicineDto> pageInfo = new PageInfo<>(patientService.getPatientAndMedicine());
//        for (PatientAndMedicineDto patientAndMedicineDto : pageInfo.getList()){
//            System.out.println("----------"+patientAndMedicineDto);
//        }
        model.addAttribute("patientAndMedicineList",pageInfo);
        return "back/page/admin/patient/patient2";
    }

    @RequestMapping("/admin/patient/updateNum")
    public @ResponseBody R updateNum(PatientMedicine patientMedicine){
        patientMedicineService.updateById(patientMedicine);
        return R.ok();
    }

    @GetMapping("/admin/patient/patientM.html")
    public String toupload(@RequestParam Integer pid, Model model){
        model.addAttribute("patient",patientService.getPatientById(pid));
        return "back/page/admin/patient/patientM";
    }

    @GetMapping("/admin/patient/patientM2.html")
    public String toPM2(@RequestParam Integer pid, Model model){
        model.addAttribute("patientMedicine",patientMedicineService.getById(pid));
        return "back/page/admin/patient/patientM2";
    }

    /**
     * 列表
     */
    @RequestMapping("/admin/patient/list")
    public @ResponseBody R list(@RequestParam Map<String, Object> params){
        IPage<Patient> page = patientService.getPatientAndWard(params);
        return R.ok().put("count",page.getTotal()).put("data",page.getRecords());
    }

    /**
     * 信息
     */
    @RequestMapping("/admin/patient/info/{pId}")
    public R info(@PathVariable("pId") Integer pId){
        Patient patient = patientService.getPatientById(pId);
        return R.ok().put("patient", patient);
    }

    /**
     * 保存
     */
    @RequestMapping("/admin/patient/save")
    public @ResponseBody R save(Patient patient){
        patient.setPDateOfHospitalization(new Date());
        int nowNum = patientService.getPatientCountByWardId(patient.getWId());
        if (nowNum >= wardService.getById(patient.getWId()).getMixNum()){
            return R.error("病房人数已满，请选择其他病房");
        }
        patientService.save(patient);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/admin/patient/update")
    public @ResponseBody R update(Patient patient){
        patientService.updateById(patient);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/admin/patient/delete")
    public @ResponseBody R delete(@RequestBody Integer[] pIds){
        patientService.removeByIds(Arrays.asList(pIds));
        for (int i = 0; i < pIds.length; i++) {
            patientMedicineService.deletePatientMedicineByPatientId(pIds[i]);
        }
        return R.ok();
    }

    @GetMapping("/admin/patient/loadAllmedicine")
    public @ResponseBody R toloadAllmedicine(){
        return  R.ok().put("data", medicineService.list());
    }

    @GetMapping("/admin/patient/loadCheckMedicine")
    public @ResponseBody R toloadCheckMedicine(@RequestParam Integer pId){
        return  R.ok().put("data", patientMedicineService.selectMedicineIdByPatientId(pId));
    }


    @RequestMapping("/admin/patient/addPatientMedicine")
    public @ResponseBody R addPatientMedicine(PatientMedicine patientMedicine){
//        System.out.println("patientMedicine:                "+patientMedicine);
        patientMedicineService.insertPatientMedicine(patientMedicine);
        return R.ok();
    }

    //common
    //进入病人管理页面
    @GetMapping("/common/patient/list.html")
    public String toCommonList(){
        return "back/page/common/patient/list";
    }
    //进入添加病人页面
    @GetMapping("/common/patient/add.html")
    public String toCommonAdd(){
        return "back/page/common/patient/add";
    }
    //进入修改病人页面
    @GetMapping("/common/patient/edit.html")
    public String toCommonEdit(@RequestParam Integer pid, Model model){
        model.addAttribute("patient",patientService.getPatientById(pid));
        model.addAttribute("ward",wardService.list());
        return "back/page/common/patient/edit";
    }
    /**
     * 列表
     */
    @RequestMapping("/common/patient/list")
    public @ResponseBody R CommonList(@RequestParam Map<String, Object> params){
        IPage<Patient> page = patientService.getPatientAndWard(params);
        return R.ok().put("count",page.getTotal()).put("data",page.getRecords());
    }

    /**
     * 信息
     */
    @RequestMapping("/common/patient/info/{pId}")
    public R CommonInfo(@PathVariable("pId") Integer pId){
        Patient patient = patientService.getPatientById(pId);
        return R.ok().put("patient", patient);
    }

    /**
     * 保存
     */
    @RequestMapping("/common/patient/save")
    public @ResponseBody R CommonSave(Patient patient){
        patient.setPDateOfHospitalization(new Date());
        patientService.save(patient);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/common/patient/update")
    public @ResponseBody R CommonUpdate(Patient patient){
        patientService.updateById(patient);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/common/patient/delete")
    public @ResponseBody R CommonDelete(@RequestBody Integer[] pIds){
        patientService.removeByIds(Arrays.asList(pIds));
        for (int i = 0; i < pIds.length; i++) {
            patientMedicineService.deletePatientMedicineByPatientId(pIds[i]);
        }
        return R.ok();
    }
    @RequestMapping("/common/patient/updateNum")
    public @ResponseBody R updateNumCommon(PatientMedicine patientMedicine){
        patientMedicineService.updateById(patientMedicine);
        return R.ok();
    }

    @GetMapping("/common/patient/patientM2.html")
    public String toPM2Common(@RequestParam Integer pid, Model model){
        model.addAttribute("patientMedicine",patientMedicineService.getById(pid));
        return "back/page/common/patient/patientM2";
    }

    //进入病人药物管理页面
    @GetMapping({"/common/patient/patient2.html", "/common/medicine/page/{pageNo}"})
    public String topictureCommon(@PathVariable(name = "pageNo",required = false)Integer pageNo ,
                            Model model){
        PageHelper.startPage(pageNo == null ? 1 : pageNo,18);
        PageInfo<PatientAndMedicineDto> pageInfo = new PageInfo<>(patientService.getPatientAndMedicine());
        for (PatientAndMedicineDto patientAndMedicineDto : pageInfo.getList()){
            System.out.println("----------"+patientAndMedicineDto);
        }
        model.addAttribute("patientAndMedicineList",pageInfo);
        return "back/page/common/patient/patient2";
    }
}
