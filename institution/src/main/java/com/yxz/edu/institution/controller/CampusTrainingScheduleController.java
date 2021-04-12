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
import com.yxz.edu.institution.entity.CampusTrainingScheduleEntity;
import com.yxz.edu.institution.service.CampusTrainingScheduleService;
import com.yxz.edu.institution.vo.CampusTrainingScheduleVo;

/**
 * 
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-28 17:50:49
 */
@RestController
@RequestMapping("ims/campustrainingschedule")
public class CampusTrainingScheduleController {
    @Autowired
    private CampusTrainingScheduleService campusTrainingScheduleService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("institution:campustrainingschedule:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = campusTrainingScheduleService.queryPage(params);

        return R.ok().put("page", page);
    }
    
    @RequestMapping("/list/{campusId}")
    //@RequiresPermissions("institution:campustrainingschedule:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("campusId") Long campusId){
        PageUtils page = campusTrainingScheduleService.queryPage(params,campusId);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("institution:campustrainingschedule:info")
    public R campusscheduleInfo(@PathVariable("id") Long id){
		CampusTrainingScheduleEntity campusTrainingSchedule = campusTrainingScheduleService.getById(id);

        return R.ok().put("campusTrainingSchedule", campusTrainingSchedule);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("institution:campustrainingschedule:save")
    public R save(@Validated({AddGroup.class}) @RequestBody CampusTrainingScheduleVo campusTrainingScheduleVo){
    	CampusTrainingScheduleEntity  campusTrainingScheduleEntity = new  CampusTrainingScheduleEntity();
    	BeanUtils.copyProperties( campusTrainingScheduleVo,  campusTrainingScheduleEntity);
		campusTrainingScheduleService.save(campusTrainingScheduleEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("institution:campustrainingschedule:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody CampusTrainingScheduleVo campusTrainingScheduleVo){
    	CampusTrainingScheduleEntity  campusTrainingScheduleEntity = new  CampusTrainingScheduleEntity();
    	BeanUtils.copyProperties( campusTrainingScheduleVo,  campusTrainingScheduleEntity);
    	
		campusTrainingScheduleService.updateById(campusTrainingScheduleEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("institution:campustrainingschedule:delete")
    public R delete(@RequestBody Long[] ids){
		campusTrainingScheduleService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
