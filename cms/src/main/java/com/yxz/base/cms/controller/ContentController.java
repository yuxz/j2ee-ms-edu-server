package com.yxz.base.cms.controller;

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
import com.yxz.base.cms.entity.ContentEntity;
import com.yxz.base.cms.service.ContentService;
import com.yxz.base.cms.vo.ContentVo;

/**
 * 文章
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-18 09:12:43
 */
@RestController
@RequestMapping("cms/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("cms:content:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = contentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("cms:content:info")
    public R info(@PathVariable("id") Long id){
		ContentEntity content = contentService.getById(id);

        return R.ok().put("content", content);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("cms:content:save")
    public R save(@Validated({AddGroup.class}) @RequestBody ContentVo contentVo){
    	ContentEntity  contentEntity = new  ContentEntity();
    	BeanUtils.copyProperties( contentVo,  contentEntity);
		contentService.save(contentEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("cms:content:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody ContentVo contentVo){
    	ContentEntity  contentEntity = new  ContentEntity();
    	BeanUtils.copyProperties( contentVo,  contentEntity);
    	
		contentService.updateById(contentEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("cms:content:delete")
    public R delete(@RequestBody Long[] ids){
		contentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
