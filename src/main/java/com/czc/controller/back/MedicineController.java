package com.czc.controller.back;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.UUID;


import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.czc.service.MedicalService;
import com.czc.service.PatientMedicineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.czc.domain.Medicine;
import com.czc.service.MedicineService;
import com.czc.utils.R;
import org.springframework.web.multipart.MultipartFile;


/**
 * 
 *
 * @author caozican
 * @email czc@qq.com
 * @date 2024-04-28 13:13:19
 */
@Controller
@RequestMapping("back/page")
public class MedicineController {
    @Autowired
    private MedicineService medicineService;

    @Autowired
    private PatientMedicineService patientMedicineService;

    @Autowired
    private MedicalService medicalService;
//进入药品信息页面
    @GetMapping("/admin/medicine/list.html")
    public String tolist(){
        return "back/page/admin/medicine/list";
    }
//进入添加药品页面
    @GetMapping("/admin/medicine/add.html")
    public String toadd(){
        return "back/page/admin/medicine/add";
    }
//进入修改药品页面
    @GetMapping("/admin/medicine/edit.html")
    public String toedit(@RequestParam Integer mid, Model model){
        model.addAttribute("medicine",medicineService.getById(mid));
        model.addAttribute("medical",medicalService.getAllMedical());
        return "back/page/admin/medicine/edit";
    }

    //进入修改药品图片页面
    @GetMapping("/admin/medicine/upload.html")
    public String toupload(@RequestParam Integer mid, Model model){
        model.addAttribute("medicine",medicineService.getById(mid));
        return "back/page/admin/medicine/upload";
    }


    //进入药品图片界面
    @GetMapping({"/admin/medicine/medicineP.html", "/admin/medicine/page/{pageNo}"})
    public String topicture(@PathVariable(name = "pageNo",required = false)Integer pageNo ,
                            Model model){
        PageHelper.startPage(pageNo == null ? 1 : pageNo,18);
        PageInfo<Medicine> pageInfo = new PageInfo<>(medicineService.getMedicinePictureList());
        model.addAttribute("medicinesPictureList",pageInfo);
        return "back/page/admin/medicine/medicineP";
    }



    /**
     * 列表
     */
    @RequestMapping("/admin/medicine/list")
    public @ResponseBody R list(@RequestParam Map<String, Object> params){
//        System.out.print("------------------------------------------------------------------------");
//        System.out.println(params);
        IPage<Medicine> page = medicineService.getMedicineAndMedical(params);
        return R.ok().put("count",page.getTotal()).put("data",page.getRecords());
    }


    /**
     * 信息
     */
    @RequestMapping("/admin/medicine/info/{mId}")
    public R info(@PathVariable("mId") Integer mId){
		Medicine medicine = medicineService.getById(mId);

        return R.ok().put("medicine", medicine);
    }

    /**
     * 保存
     */
    @RequestMapping("/admin/medicine/save")
    public @ResponseBody R save(Medicine medicine){
        medicine.setProductionDate(new Date());
		medicineService.save(medicine);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/admin/medicine/update")
    public @ResponseBody R update(Medicine medicine){
		medicineService.updateById(medicine);

        return R.ok();
    }



    /**
     * 删除
     */
    @RequestMapping("/admin/medicine/delete")
    public @ResponseBody R delete(@RequestBody Integer[] mIds){
		medicineService.removeByIds(Arrays.asList(mIds));

        for (int i = 0; i < mIds.length; i++) {
            patientMedicineService.deletePatientMedicineByMedicineId(mIds[i]);
        }
        return R.ok();
    }

    @PostMapping("/admin/medicine/upload")
    public @ResponseBody R UploadPicture(@RequestParam("file") MultipartFile file,Integer mId) throws IOException {
        if(file.isEmpty()){
            return R.error("文件为空");
        }
        // 上传文件/图像到指定文件夹（这里可以改成你想存放地址的相对路径）
        File pathfile = new File("D://DownLoad/SpringBoot/picture/medicine/");

        // 如果原始图片存在，则删除它
        if(medicineService.getById(mId).getPicture()!=null){
        File originalPath  = new File(medicineService.getById(mId).getPicture());
        String originalImagePath1 = new File(pathfile.getCanonicalPath()+originalPath).getCanonicalPath();
        File originalImagePath = new File(originalImagePath1);
        System.out.println(originalImagePath);
            originalImagePath.delete();
        }
        //获取图片后缀
        String imgFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //给图片重新随机生成名字
        String name = UUID.randomUUID().toString() + imgFormat;
        // 获取存放位置的规范路径
        String realPath = pathfile.getCanonicalPath();
        // 上传该文件/图像至该文件夹下
        file.transferTo(new File(realPath + "\\" + name));
//        System.out.println(mId);
        // 修改数据库中该图片的路径
        medicineService.updateMedicinePicture(mId, name);
        return R.ok();
    }


    //common

    //进入修改药品图片页面
    @GetMapping("/common/medicine/upload.html")
    public String toCommonupload(@RequestParam Integer mid, Model model){
        model.addAttribute("medicine",medicineService.getById(mid));
        return "back/page/common/medicine/upload";
    }


    //进入药品图片界面
    @GetMapping({"/common/medicine/medicineP.html", "/common/medicine/page/{pageNo}"})
    public String toCommonpicture(@PathVariable(name = "pageNo",required = false)Integer pageNo ,
                            Model model){
        PageHelper.startPage(pageNo == null ? 1 : pageNo,18);
        PageInfo<Medicine> pageInfo = new PageInfo<>(medicineService.getMedicinePictureList());
        model.addAttribute("medicinesPictureList",pageInfo);
        return "back/page/common/medicine/medicineP";
    }

    @PostMapping("/common/medicine/upload")
    public @ResponseBody R CommonUploadPicture(@RequestParam("file") MultipartFile file,Integer mId) throws IOException {
        if(file.isEmpty()){
            return R.error("文件为空");
        }
        // 上传文件/图像到指定文件夹（这里可以改成你想存放地址的相对路径）
        File pathfile = new File("D://DownLoad/SpringBoot/picture/medicine/");

        // 如果原始图片存在，则删除它
        if(medicineService.getById(mId).getPicture()!=null){
            File originalPath  = new File(medicineService.getById(mId).getPicture());
            String originalImagePath1 = new File(pathfile.getCanonicalPath()+originalPath).getCanonicalPath();
            File originalImagePath = new File(originalImagePath1);
            System.out.println(originalImagePath);
            originalImagePath.delete();
        }
        //获取图片后缀
        String imgFormat = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //给图片重新随机生成名字
        String name = UUID.randomUUID().toString() + imgFormat;
        // 获取存放位置的规范路径
        String realPath = pathfile.getCanonicalPath();
        // 上传该文件/图像至该文件夹下
        file.transferTo(new File(realPath + "\\" + name));
//        System.out.println(mId);
        // 修改数据库中该图片的路径
        medicineService.updateMedicinePicture(mId, name);
        return R.ok();
    }

}
