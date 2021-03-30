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
import com.yxz.edu.teacher.entity.TeacherExperienceEntity;
import com.yxz.edu.teacher.service.TeacherExperienceService;
import com.yxz.edu.teacher.vo.TeacherExperienceVo;

/**
 * Working Experience
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */
@RestController
@RequestMapping("teacher/teacherexperience")
public class TeacherExperienceController {
    @Autowired
    private TeacherExperienceService teacherExperienceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("teacher:teacherexperience:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherExperienceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("teacher:teacherexperience:info")
    public R info(@PathVariable("id") Long id){
		TeacherExperienceEntity teacherExperience = teacherExperienceService.getById(id);

        return R.ok().put("teacherExperience", teacherExperience);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("teacher:teacherexperience:save")
    public R save(@Validated({AddGroup.class}) @RequestBody TeacherExperienceVo teacherExperienceVo){
    	TeacherExperienceEntity  teacherExperienceEntity = new  TeacherExperienceEntity();
    	BeanUtils.copyProperties( teacherExperienceVo,  teacherExperienceEntity);
		teacherExperienceService.save(teacherExperienceEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("teacher:teacherexperience:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody TeacherExperienceVo teacherExperienceVo){
    	TeacherExperienceEntity  teacherExperienceEntity = new  TeacherExperienceEntity();
    	BeanUtils.copyProperties( teacherExperienceVo,  teacherExperienceEntity);
    	
		teacherExperienceService.updateById(teacherExperienceEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("teacher:teacherexperience:delete")
    public R delete(@RequestBody Long[] ids){
		teacherExperienceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
