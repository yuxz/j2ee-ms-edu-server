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
import com.yxz.edu.ims.entity.ClassroomEntity;
import com.yxz.edu.ims.service.ClassroomService;



/**
 * 教室
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
@RestController
@RequestMapping("ims/classroom")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ims:classroom:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = classroomService.queryPageClassRoom(params);

        return R.ok().put("page", page);
    }

//    @RequestMapping("/list")
//    //@RequiresPermissions("ims:classroom:list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = classroomService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ims:classroom:info")
    public R info(@PathVariable("id") Long id){
		ClassroomEntity classroom = classroomService.getById(id);

        return R.ok().put("classroom", classroom);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ims:classroom:save")
    public R save(@Validated({AddGroup.class}) @RequestBody ClassroomEntity classroom){
		classroomService.save(classroom);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ims:classroom:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody ClassroomEntity classroom){
		classroomService.updateById(classroom);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ims:classroom:delete")
    public R delete(@RequestBody Long[] ids){
		classroomService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
