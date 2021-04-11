package com.yxz.edu.institution.controller;

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

import com.yxz.base.common.to.ClassTo;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.R;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;
import com.yxz.edu.institution.entity.ClassEntity;
import com.yxz.edu.institution.service.ClassService;
import com.yxz.edu.institution.vo.ClassDetailVo;
import com.yxz.edu.institution.vo.ClassVo;



/**
 * 
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 10:13:23
 */
@RestController
@RequestMapping("ims/class")
public class ClassController {
    @Autowired
    private ClassService classService;

    /**
     * 列表
     */
    @RequestMapping("/list/{isFinished}")
    //@RequiresPermissions("ims:class:list")
    public R list(@RequestParam Map<String, Object> params, @PathVariable("isFinished") Long isFinished){
        PageUtils page = classService.queryPage(params,isFinished);

        return R.ok().put("page", page);
    }

    @RequestMapping("/listAll")
    //@RequiresPermissions("ims:class:list")
    public R listAllClass(){
        List<ClassTo> classTos = classService.queryAllClasses();

        return R.ok().put("classTos", classTos);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("ims:class:info")
    public R info(@PathVariable("id") Long id){
		ClassDetailVo classDetailVo = classService.getDetailById(id);

        return R.ok().put("class", classDetailVo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("ims:class:save")
    public R save(@Validated({AddGroup.class}) @RequestBody ClassVo classVo){
    	ClassEntity classEntity = new ClassEntity();
    	BeanUtils.copyProperties(classVo, classEntity);
    	
		classService.save(classEntity);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("ims:class:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody ClassEntity classEntity){
		classService.updateById(classEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("ims:class:delete")
    public R delete(@RequestBody Long[] ids){
		classService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }
    
    
    

}
