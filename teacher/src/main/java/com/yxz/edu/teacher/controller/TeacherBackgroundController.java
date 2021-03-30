package com.yxz.edu.teacher.controller;

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
import com.yxz.edu.teacher.entity.TeacherBackgroundEntity;
import com.yxz.edu.teacher.service.TeacherBackgroundService;
import com.yxz.edu.teacher.vo.TeacherBackgroundVo;

/**
 * Education Background
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */
@RestController
@RequestMapping("teacher/teacherbackground")
public class TeacherBackgroundController {
    @Autowired
    private TeacherBackgroundService teacherBackgroundService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("teacher:teacherbackground:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherBackgroundService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("teacher:teacherbackground:info")
    public R info(@PathVariable("id") Long id){
		TeacherBackgroundEntity teacherBackground = teacherBackgroundService.getById(id);

        return R.ok().put("teacherBackground", teacherBackground);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("teacher:teacherbackground:save")
    public R save(@Validated({AddGroup.class}) @RequestBody TeacherBackgroundVo teacherBackgroundVo){
    	TeacherBackgroundEntity  teacherBackgroundEntity = new  TeacherBackgroundEntity();
    	BeanUtils.copyProperties( teacherBackgroundVo,  teacherBackgroundEntity);
		teacherBackgroundService.save(teacherBackgroundEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("teacher:teacherbackground:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody TeacherBackgroundVo teacherBackgroundVo){
    	TeacherBackgroundEntity  teacherBackgroundEntity = new  TeacherBackgroundEntity();
    	BeanUtils.copyProperties( teacherBackgroundVo,  teacherBackgroundEntity);
    	
		teacherBackgroundService.updateById(teacherBackgroundEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("teacher:teacherbackground:delete")
    public R delete(@RequestBody Long[] ids){
		teacherBackgroundService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
