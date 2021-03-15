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
import com.yxz.edu.ims.entity.InstitutionEntity;
import com.yxz.edu.ims.service.InstitutionService;



/**
 * 教育机构
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
@RestController
@RequestMapping("ims/institution")
public class InstitutionController {
    @Autowired
    private InstitutionService institutionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("ims:institution:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = institutionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ims:institution:info")
    public R info(@PathVariable("id") Long id){
		InstitutionEntity institution = institutionService.getById(id);

        return R.ok().put("institution", institution);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ims:institution:save")
    public R save(@Validated({AddGroup.class}) @RequestBody InstitutionEntity institution){
		institutionService.save(institution);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ims:institution:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody InstitutionEntity institution){
		institutionService.updateById(institution);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ims:institution:delete")
    public R delete(@RequestBody Long[] ids){
		institutionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
