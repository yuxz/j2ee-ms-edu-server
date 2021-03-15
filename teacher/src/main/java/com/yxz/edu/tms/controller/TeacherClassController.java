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
import com.yxz.edu.tms.entity.TeacherClassEntity;
import com.yxz.edu.tms.service.TeacherClassService;
import com.yxz.edu.tms.vo.TeacherClassVo;

/**
 * 班级老师
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
@RestController
@RequestMapping("tms/teacherclass")
public class TeacherClassController {
    @Autowired
    private TeacherClassService teacherClassService;

    /**
     * 列表
     */
    @RequestMapping("/list/{classId}")
    //@RequiresPermissions("tms:teacherclass:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("classId") Long classId){
//        PageUtils page = teacherClassService.queryPage(params);
        PageUtils page = teacherClassService.queryPage(params,classId);
        
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("tms:teacherclass:info")
    public R info(@PathVariable("id") Long id){
		TeacherClassEntity teacherClass = teacherClassService.getById(id);

        return R.ok().put("teacherClass", teacherClass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("tms:teacherclass:save")
    public R save(@Validated({AddGroup.class}) @RequestBody TeacherClassVo teacherClassVo){
    	TeacherClassEntity  teacherClassEntity = new  TeacherClassEntity();
    	BeanUtils.copyProperties( teacherClassVo,  teacherClassEntity);
		teacherClassService.save(teacherClassEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("tms:teacherclass:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody TeacherClassVo teacherClassVo){
    	TeacherClassEntity  teacherClassEntity = new  TeacherClassEntity();
    	BeanUtils.copyProperties( teacherClassVo,  teacherClassEntity);
    	
		teacherClassService.updateById(teacherClassEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("tms:teacherclass:delete")
    public R delete(@RequestBody Long[] ids){
		teacherClassService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}