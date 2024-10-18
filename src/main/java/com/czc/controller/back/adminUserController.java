package com.czc.controller.back;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.czc.domain.Customer;
import com.czc.domain.CustomerAuthority;
import com.czc.domain.Dto.CustomerAndAuthority;
import com.czc.domain.Medicine;
import com.czc.service.CustomerAuthorityService;
import com.czc.service.CustomerServiceMybatisPlus;
import com.czc.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("back/page/backuser")
public class adminUserController {

    @Autowired
    private CustomerServiceMybatisPlus customerService;

    @Autowired
    private CustomerAuthorityService customerAuthorityServer;

    @GetMapping("/createUser.html")
    public String toCreateBackUser(){
        return "back/page/backuser/createUser";
    }

    @GetMapping("/editUser.html")
    public String toEditBackUser(@RequestParam Integer id, Model model){
        model.addAttribute("customer",customerService.getById(id));
        model.addAttribute("authority",customerAuthorityServer.selectByCustomerId(id));
        return "back/page/backuser/editUser";
    }

    @GetMapping("/list.html")
    public String toBackUserList(){
        return "back/page/backuser/list";
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public @ResponseBody R save(@RequestParam String authorityId,Customer customer){
        List<Customer> customernames = customerService.list();
        for (Customer customername : customernames){
            if (customername.getUsername().equals(customer.getUsername())){
                return R.error("用户名已存在");
            }
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Customer customer1 = new Customer();
        CustomerAuthority customerAuthority = new CustomerAuthority();
        customer1.setUsername(customer.getUsername());
        customer1.setPassword(encoder.encode(customer.getPassword()));
        System.out.println("Customer:   "+customer1);
        customerService.insertCustomer(customer1);
        customerAuthority.setCustomerId(customerService.getIdByUsername(customer1.getUsername()));
        customerAuthority.setAuthorityId(Integer.parseInt(authorityId));
        System.out.println("CustomerAuthority:   "+customerAuthority);
        customerAuthorityServer.save(customerAuthority);
        return R.ok();
    }

    @RequestMapping("/list")
    public @ResponseBody R list(@RequestParam Map<String, Object> params){
        IPage<CustomerAndAuthority> page = customerService.getCustomerAuthority(params);
        return R.ok().put("count",page.getTotal()).put("data",page.getRecords());
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public @ResponseBody R update(@RequestParam String authorityId,Customer customer){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        Customer customer1 = new Customer();
        customer1.setUsername(customer.getUsername());
        customer1.setPassword(encoder.encode(customer.getPassword()));
        customerService.updateById(customer1);
        customerAuthorityServer.updateByCustomerId(customer.getId(),Integer.parseInt(authorityId));
        return R.ok();
    }


    /**
     * 删除
     */
    @RequestMapping("/delete")
    public @ResponseBody R delete(@RequestBody Integer[] id){
        customerService.removeByIds(Arrays.asList(id));

        return R.ok();
    }
}
