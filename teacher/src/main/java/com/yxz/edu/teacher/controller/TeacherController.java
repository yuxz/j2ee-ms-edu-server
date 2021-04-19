package com.yxz.edu.teacher.controller;

import java.util.Arrays;
import java.util.Date;
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

import com.yxz.base.common.constant.MemberConstant;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.R;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;
import com.yxz.edu.teacher.entity.TeacherEntity;
import com.yxz.edu.teacher.feign.MemberFeignService;
import com.yxz.edu.teacher.service.TeacherService;
import com.yxz.edu.teacher.vo.TeacherVo;
import com.yxz.edu.teacher.vo.UserSignUpVo;

/**
 * 教师
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
@RestController
@RequestMapping("tms/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    MemberFeignService memberFeignService;
    /**
     * 列表
     */
    @RequestMapping("/list/{isLeaved}")
    //@RequiresPermissions("tms:teacher:list")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("isLeaved") Long isLeaved){
        PageUtils page = teacherService.queryPage(params, isLeaved);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("tms:teacher:info")
    public R info(@PathVariable("id") Long id){
		TeacherEntity teacher = teacherService.getById(id);

        return R.ok().put("teacher", teacher);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("tms:teacher:save")
    public R save(@Validated({AddGroup.class}) @RequestBody TeacherVo teacherVo){
    	TeacherEntity  teacherEntity = new  TeacherEntity();
    	BeanUtils.copyProperties( teacherVo,  teacherEntity);
    	teacherEntity.setCreated(new Date());
    	teacherEntity.setUpdated(new Date());
		teacherService.save(teacherEntity);

		//注册机构账号		
		UserSignUpVo userSignUpVo = new UserSignUpVo();
		userSignUpVo.setEmail(teacherVo.getEmail());
		//TODO 自动生成机构密码123456 
		userSignUpVo.setPassword("123456");	
		userSignUpVo.setStatus(Integer.toString(MemberConstant.AttrEnum.MEMBER_TEACHER.getCode()));
		memberFeignService.signup(userSignUpVo);
				
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("tms:teacher:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody TeacherVo teacherVo){
    	TeacherEntity  teacherEntity = new  TeacherEntity();
    	BeanUtils.copyProperties( teacherVo,  teacherEntity);
    	
    	teacherEntity.setUpdated(new Date());
		teacherService.updateById(teacherEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("tms:teacher:delete")
    public R delete(@RequestBody Long[] ids){
		teacherService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
