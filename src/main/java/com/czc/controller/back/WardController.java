package com.czc.controller.back;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czc.domain.Medicine;
import com.czc.domain.Ward;
import com.czc.service.WardService;
import com.czc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("back/page")
public class WardController {
    @Autowired
    private WardService wardService;

    @GetMapping("/admin/ward/all")
    public @ResponseBody R all(){
        return R.ok().put("data", wardService.list());
    }


    //进入信息页面
    @GetMapping("/admin/ward/list.html")
    public String tolist(){
        return "back/page/admin/ward/list";
    }

    //进入添加页面
    @GetMapping("/admin/ward/add.html")
    public String toadd(){
        return "back/page/admin/ward/add";
    }
    //进入修改页面
    @GetMapping("/admin/ward/edit.html")
    public String toedit(@RequestParam Integer wid, Model model){
        model.addAttribute("ward",wardService.getById(wid));
        return "back/page/admin/ward/edit";
    }


    /**
     * 列表
     */
    @RequestMapping("/admin/ward/list")
    public @ResponseBody R list(@RequestParam Map<String, Object> params){
        IPage<Ward> page = wardService.getWard(params);
        return R.ok().put("count",page.getTotal()).put("data",page.getRecords());
    }

    /**
     * 修改
     */
    @RequestMapping("/admin/ward/update")
    public @ResponseBody R update(Ward ward){
        wardService.updateById(ward);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/admin/ward/save")
    public @ResponseBody R save(Ward ward){
        wardService.save(ward);
        return R.ok();
    }


//    common
//进入信息页面
    @GetMapping("/common/ward/list.html")
    public String toCommonList(){
        return "back/page/common/ward/list";
    }

    //进入添加页面
    @GetMapping("/common/ward/add.html")
    public String toCommonAdd(){
        return "back/page/common/ward/add";
    }
    //进入修改页面
    @GetMapping("/common/ward/edit.html")
    public String toCommonEdit(@RequestParam Integer wid, Model model){
        model.addAttribute("ward",wardService.getById(wid));
        return "back/page/common/ward/edit";
    }
    @GetMapping("/common/ward/all")
    public @ResponseBody R allCommon(){
        return R.ok().put("data", wardService.list());
    }


    /**
     * 列表
     */
    @RequestMapping("/common/ward/list")
    public @ResponseBody R CommonList(@RequestParam Map<String, Object> params){
        IPage<Ward> page = wardService.getWard(params);
        return R.ok().put("count",page.getTotal()).put("data",page.getRecords());
    }

    /**
     * 修改
     */
    @RequestMapping("/common/ward/update")
    public @ResponseBody R CommonUpdate(Ward ward){
        wardService.updateById(ward);
        return R.ok();
    }

    /**
     * 保存
     */
    @RequestMapping("/common/ward/save")
    public @ResponseBody R CommonSave(Ward ward){
        wardService.save(ward);
        return R.ok();
    }
}
