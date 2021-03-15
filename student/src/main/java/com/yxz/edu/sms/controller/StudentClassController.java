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
import com.yxz.edu.sms.entity.StudentClassEntity;
import com.yxz.edu.sms.service.StudentClassService;
import com.yxz.edu.sms.vo.StudentClassVo;

/**
 * 班级学生
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 19:17:49
 */
@RestController
@RequestMapping("sms/studentclass")
public class StudentClassController {
    @Autowired
    private StudentClassService studentClassService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("sms:studentclass:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = studentClassService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sms:studentclass:info")
    public R info(@PathVariable("id") Long id){
		StudentClassEntity studentClass = studentClassService.getById(id);

        return R.ok().put("studentClass", studentClass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sms:studentclass:save")
    public R save(@Validated({AddGroup.class}) @RequestBody StudentClassVo studentClassVo){
    	StudentClassEntity  studentClassEntity = new  StudentClassEntity();
    	BeanUtils.copyProperties( studentClassVo,  studentClassEntity);
		studentClassService.save(studentClassEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sms:studentclass:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody StudentClassVo studentClassVo){
    	StudentClassEntity  studentClassEntity = new  StudentClassEntity();
    	BeanUtils.copyProperties( studentClassVo,  studentClassEntity);
    	
		studentClassService.updateById(studentClassEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sms:studentclass:delete")
    public R delete(@RequestBody Long[] ids){
		studentClassService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
