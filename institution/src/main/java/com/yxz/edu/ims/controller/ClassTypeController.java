package com.yxz.edu.ims.controller;

import java.util.Arrays;
import java.util.Map;

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
import com.yxz.edu.ims.entity.ClassTypeEntity;
import com.yxz.edu.ims.service.ClassTypeService;



/**
 * 班级/课程类型
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
@RestController
@RequestMapping("ims/classtype")
public class ClassTypeController {
    @Autowired
    private ClassTypeService classTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ims:classtype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = classTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ims:classtype:info")
    public R info(@PathVariable("id") Long id){
		ClassTypeEntity classType = classTypeService.getById(id);

        return R.ok().put("classType", classType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ims:classtype:save")
    public R save(@Validated({AddGroup.class}) @RequestBody ClassTypeEntity classType){
		classTypeService.save(classType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ims:classtype:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody ClassTypeEntity classType){
		classTypeService.updateById(classType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ims:classtype:delete")
    public R delete(@RequestBody Long[] ids){
		classTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
