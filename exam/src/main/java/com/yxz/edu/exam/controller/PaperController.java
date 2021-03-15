package com.yxz.edu.exam.controller;

import java.util.Arrays;
import java.util.Date;
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
import com.yxz.edu.exam.entity.PaperEntity;
import com.yxz.edu.exam.entity.PaperItemEntity;
import com.yxz.edu.exam.service.CategoryService;
import com.yxz.edu.exam.service.PaperItemService;
import com.yxz.edu.exam.service.PaperService;
import com.yxz.edu.exam.vo.PaperDetailVo;
import com.yxz.edu.exam.vo.PaperItemVo;



/**
 * 试卷
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-08 11:08:07
 */
@RestController
@RequestMapping("exam/paper")
public class PaperController {
    @Autowired
    private PaperService paperService;
    
    @Autowired
    private CategoryService categoryService;
    
    @Autowired
    private PaperItemService paperItemService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("exam:paper:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = paperService.queryPage(params);

        return R.ok().put("page", page);
    }
    
    /**
     * 列表：按照试卷分类Id查询数据
     */
    @RequestMapping("/list/{categoryId}")
    //@RequiresPermissions("exam:paper:list")
    public R listByCategoryId(@RequestParam Map<String, Object> params, @PathVariable("categoryId") Long categoryId){
       // PageUtils page = paperService.queryPage(params);
        PageUtils page = paperService.queryPage(params, categoryId);
        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("exam:paper:info")
    public R info(@PathVariable("id") Long id){
		PaperEntity paper = paperService.getById(id);
		
		Long catagoryId = paper.getCategoryId();
		
		// get the  whole path of category
		Long[] fullPath = categoryService.getFullPath(catagoryId);
		
		paper.setCategoryFullPath(fullPath);			
		
        return R.ok().put("paper", paper);
    }
    
    /**
     * 包括试卷和试题信息
     */
    @RequestMapping("/paperDetail/{id}")
    //@RequiresPermissions("exam:paper:info")
    public R paperDetail(@PathVariable("id") Long id){
    	
    	PaperDetailVo paperDetailVo = new PaperDetailVo();
    	
		PaperEntity paper = paperService.getById(id);		
		BeanUtils.copyProperties(paper, paperDetailVo);
		
		Long paperId = paper.getId();		
		List<PaperItemVo> paperItemVo = paperItemService.querytByPaperId(paperId);
		
		
		
		paperDetailVo.setPaperItems(paperItemVo);
		
				
		
        return R.ok().put("paper", paperDetailVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("exam:paper:save")
    public R save(@Validated({AddGroup.class}) @RequestBody PaperEntity paper){
    	paper.setCreateTime(new Date());
    	paper.setUpdateTime(new Date());
		paperService.save(paper);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("exam:paper:update")
    public R update(@Validated({AddGroup.class}) @RequestBody PaperEntity paper){
    	paper.setUpdateTime(new Date());
		paperService.updateById(paper);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("exam:paper:delete")
    public R delete(@RequestBody Long[] ids){
		paperService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
