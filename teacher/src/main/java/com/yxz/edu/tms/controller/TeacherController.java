package com.yxz.edu.tms.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.BeanUtils;
//import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.R;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;
import com.yxz.edu.tms.entity.TeacherEntity;
import com.yxz.edu.tms.service.TeacherService;
import com.yxz.edu.tms.vo.TeacherVo;

/**
 * 教师
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
@RestController
@RequestMapping("tms/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("tms:teacher:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("tms:teacher:info")
    public R info(@PathVariable("id") Long id){
		TeacherEntity teacher = teacherService.getById(id);

        return R.ok().put("teacher", teacher);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("tms:teacher:save")
    public R save(@Validated({AddGroup.class}) @RequestBody TeacherVo teacherVo){
    	TeacherEntity  teacherEntity = new  TeacherEntity();
    	BeanUtils.copyProperties( teacherVo,  teacherEntity);
		teacherService.save(teacherEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("tms:teacher:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody TeacherVo teacherVo){
    	TeacherEntity  teacherEntity = new  TeacherEntity();
    	BeanUtils.copyProperties( teacherVo,  teacherEntity);
    	
		teacherService.updateById(teacherEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("tms:teacher:delete")
    public R delete(@RequestBody Long[] ids){
		teacherService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
