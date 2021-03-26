package com.yxz.edu.institution.controller;

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
import com.yxz.edu.institution.entity.CampusEntity;
import com.yxz.edu.institution.service.CampusService;



/**
 * 校区
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
@RestController
@RequestMapping("ims/campus")
public class CampusController {
    @Autowired
    private CampusService campusService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ims:campus:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = campusService.queryPageCampus(params);

        return R.ok().put("page", page);
    }
//    @RequestMapping("/list")
//    //@RequiresPermissions("ims:campus:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = campusService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }



    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ims:campus:info")
    public R info(@PathVariable("id") Long id){
		CampusEntity campus = campusService.getById(id);

        return R.ok().put("campus", campus);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ims:campus:save")
    public R save(@Validated({AddGroup.class}) @RequestBody CampusEntity campus){
		campusService.save(campus);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ims:campus:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody CampusEntity campus){
		campusService.updateById(campus);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ims:campus:delete")
    public R delete(@RequestBody Long[] ids){
		campusService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
