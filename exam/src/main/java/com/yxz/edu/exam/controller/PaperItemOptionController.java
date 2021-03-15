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
import com.yxz.edu.exam.entity.PaperItemOptionEntity;
import com.yxz.edu.exam.service.PaperItemOptionService;



/**
 * 试题选项
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-12 09:38:44
 */
@RestController
@RequestMapping("exam/paperitemoption")
public class PaperItemOptionController {
    @Autowired
    private PaperItemOptionService paperItemOptionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("exam:paperitemoption:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paperItemOptionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("exam:paperitemoption:info")
    public R info(@PathVariable("id") Long id){
		PaperItemOptionEntity paperItemOption = paperItemOptionService.getById(id);

        return R.ok().put("paperItemOption", paperItemOption);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("exam:paperitemoption:save")
    public R save(@Validated({AddGroup.class}) @RequestBody PaperItemOptionEntity paperItemOption){
		paperItemOptionService.save(paperItemOption);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("exam:paperitemoption:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody PaperItemOptionEntity paperItemOption){
		paperItemOptionService.updateById(paperItemOption);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("exam:paperitemoption:delete")
    public R delete(@RequestBody Long[] ids){
		paperItemOptionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
