package com.yxz.edu.sms.controller;

import java.util.Arrays;
import java.util.Map;

//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.R;
import com.yxz.edu.sms.entity.StudentEntity;
import com.yxz.edu.sms.service.StudentService;
import com.yxz.edu.sms.vo.StudentVo;

/**
 * 学生
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 19:17:49
 */
@RestController
@RequestMapping("sms/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sms:student:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = studentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sms:student:info")
    public R info(@PathVariable("id") Long id){
		StudentEntity student = studentService.getById(id);

        return R.ok().put("student", student);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sms:student:save")
    public R save(@Validated({AddGroup.class}) @RequestBody StudentVo studentVo){
    	StudentEntity  studentEntity = new  StudentEntity();
    	BeanUtils.copyProperties( studentVo,  studentEntity);
		studentService.save(studentEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sms:student:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody StudentVo studentVo){
    	StudentEntity  studentEntity = new  StudentEntity();
    	BeanUtils.copyProperties( studentVo,  studentEntity);
    	
		studentService.updateById(studentEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sms:student:delete")
    public R delete(@RequestBody Long[] ids){
		studentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
