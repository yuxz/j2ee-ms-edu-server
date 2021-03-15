package com.yxz.edu.exam.controller;

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
import com.yxz.edu.exam.entity.PaperItemEntity;
import com.yxz.edu.exam.service.PaperItemService;



/**
 * 试题
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-11 22:21:52
 */
@RestController
@RequestMapping("exam/paperitem")
public class PaperItemController {
    @Autowired
    private PaperItemService paperItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("exam:paperitem:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paperItemService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("exam:paperitem:info")
    public R info(@PathVariable("id") Long id){
		PaperItemEntity paperItem = paperItemService.getById(id);

        return R.ok().put("paperItem", paperItem);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("exam:paperitem:save")
    public R save(@Validated({AddGroup.class}) @RequestBody PaperItemEntity paperItem){
		paperItemService.save(paperItem);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("exam:paperitem:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody PaperItemEntity paperItem){
		paperItemService.updateById(paperItem);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("exam:paperitem:delete")
    public R delete(@RequestBody Long[] ids){
		paperItemService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
