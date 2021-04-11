package com.yxz.edu.institution.controller;

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
import com.yxz.edu.institution.entity.ClassLevelEntity;
import com.yxz.edu.institution.service.ClassLevelService;
import com.yxz.edu.institution.vo.ClassLevelVo;

/**
 * 年级
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-04-11 12:29:23
 */
@RestController
@RequestMapping("ims/classlevel")
public class ClassLevelController {
    @Autowired
    private ClassLevelService classLevelService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("institution:classlevel:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = classLevelService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("institution:classlevel:info")
    public R info(@PathVariable("id") Long id){
		ClassLevelEntity classLevel = classLevelService.getById(id);

        return R.ok().put("classLevel", classLevel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("institution:classlevel:save")
    public R save(@Validated({AddGroup.class}) @RequestBody ClassLevelVo classLevelVo){
    	ClassLevelEntity  classLevelEntity = new  ClassLevelEntity();
    	BeanUtils.copyProperties( classLevelVo,  classLevelEntity);
		classLevelService.save(classLevelEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("institution:classlevel:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody ClassLevelVo classLevelVo){
    	ClassLevelEntity  classLevelEntity = new  ClassLevelEntity();
    	BeanUtils.copyProperties( classLevelVo,  classLevelEntity);
    	
		classLevelService.updateById(classLevelEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("institution:classlevel:delete")
    public R delete(@RequestBody Long[] ids){
		classLevelService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
