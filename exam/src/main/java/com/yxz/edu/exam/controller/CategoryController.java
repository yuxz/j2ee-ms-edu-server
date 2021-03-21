package com.yxz.edu.exam.controller;

import java.util.Arrays;
import java.util.List;
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
import com.yxz.edu.exam.entity.CategoryEntity;
import com.yxz.edu.exam.service.CategoryService;
import com.yxz.edu.exam.vo.CategoryChildrenVo;
import com.yxz.edu.exam.vo.CategoryVo;



/**
 * 试卷类型
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-08 11:08:06
 */
@RestController
@RequestMapping("exam/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("exam:category:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = categoryService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 树形列表
     */
    @RequestMapping("/list/tree")
    //@RequiresPermissions("exam:category:list")
    public R tree(){
    	// 1. get all
    	List<CategoryChildrenVo> entities = categoryService.listByTree();
    	
    	
    	
        return R.ok().put("data", entities);
    }
    



	/**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("exam:category:info")
    public R info(@PathVariable("id") Long id){
		CategoryEntity categoryEntity = categoryService.getById(id);
		CategoryChildrenVo categoryChildrenVo = new CategoryChildrenVo();
		BeanUtils.copyProperties(categoryEntity, categoryChildrenVo);
		
		categoryChildrenVo.setParentCategoryName(categoryService.getById(categoryChildrenVo.getParentId()).getName());
        return R.ok().put("data", categoryChildrenVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("exam:category:save")
    public R save(@Validated({AddGroup.class}) @RequestBody CategoryVo categoryVo){
    	
    	CategoryEntity categoryEntity = new CategoryEntity();
    	BeanUtils.copyProperties(categoryVo, categoryEntity);
    	
		categoryService.save(categoryEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("exam:category:update")
    public R update(@Validated({AddGroup.class}) @RequestBody CategoryVo categoryVo){
    	CategoryEntity categoryEntity = new CategoryEntity();
    	BeanUtils.copyProperties(categoryVo, categoryEntity);
    	
		categoryService.updateById(categoryEntity);

        return R.ok();
    }
    
    /**
     * 修改
     */
    @RequestMapping("/update/sort")
    //@RequiresPermissions("exam:category:update")
    public R updateSort(@RequestBody CategoryEntity[] category){
        categoryService.updateBatchById(Arrays.asList(category));
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("exam:category:delete")
    public R delete(@RequestBody Long[] catIds){

		//categoryService.removeByIds(Arrays.asList(catIds));

        categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
