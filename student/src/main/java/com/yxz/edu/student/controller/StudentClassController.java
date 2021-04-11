package com.yxz.edu.student.controller;

import java.util.Arrays;
import java.util.List;
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

import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.R;
import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;
import com.yxz.edu.student.entity.StudentClassEntity;
import com.yxz.edu.student.service.StudentClassService;
import com.yxz.edu.student.vo.StudentClassListVo;
import com.yxz.edu.student.vo.StudentClassVo;

/**
 * 班级学生
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 19:17:49
 */
@RestController
@RequestMapping("sms/studentclass")
public class StudentClassController {
    @Autowired
    private StudentClassService studentClassService;

    /**
     * 列表
     */
    @RequestMapping("/list/{classId}")
    //@RequiresPermissions("sms:studentclass:list")
    public R listIncludingByClass(@RequestParam Map<String, Object> params,@PathVariable("classId") Long classId){
//        PageUtils page = studentClassService.queryPage(params);
        List<StudentClassListVo> list = studentClassService.queryIncludingByClass(classId);
        return R.ok().put("data", list);
    }
    ///tms/studentclass/list/" + this.classId + "/includingOfClass
    @RequestMapping("/list/{classId}/includingByClass")
    //@RequiresPermissions("sms:studentclass:list")
    public R listNotIncludingByClass(@RequestParam Map<String, Object> params, @PathVariable("classId") Long classId){
    	
        PageUtils page = studentClassService.queryPageNotIncludingByClass(params,classId);
        
        return R.ok().put("page", page);
    }
    
    

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("sms:studentclass:info")
    public R info(@PathVariable("id") Long id){
		StudentClassEntity studentClass = studentClassService.getById(id);

        return R.ok().put("studentClass", studentClass);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("sms:studentclass:save")
    public R save(@Validated({AddGroup.class}) @RequestBody StudentClassVo studentClassVo){
    	StudentClassEntity  studentClassEntity = new  StudentClassEntity();
    	BeanUtils.copyProperties( studentClassVo,  studentClassEntity);
		studentClassService.save(studentClassEntity);

        return R.ok();
    }
    
    /**
     * 批量保存
     */
    @RequestMapping("/saveBatch")
    //@RequiresPermissions("sms:studentclass:save")
    public R saveBatch(@Validated({AddGroup.class}) @RequestBody List<StudentClassVo> studentClassVos){
		studentClassService.saveBatch(studentClassVos);

        return R.ok();
    }
    
    /**
     * 一鍵分配，將所有未分班學生，按照所報校區、時段、班級類型、年級和班級最大學生數，自動分配
     */
    @RequestMapping("/autoAssign")
    //@RequiresPermissions("sms:studentclass:save")
    public R autoAssign(){
//    	TeacherClassEntity  teacherClassEntity = new  TeacherClassEntity();
//    	BeanUtils.copyProperties( teacherClassVo,  teacherClassEntity);
		studentClassService.autoAssign();

        return R.ok();
    }


    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("sms:studentclass:update")
    public R update(@Validated(UpdateGroup.class)  @RequestBody StudentClassVo studentClassVo){
    	StudentClassEntity  studentClassEntity = new  StudentClassEntity();
    	BeanUtils.copyProperties( studentClassVo,  studentClassEntity);
    	
		studentClassService.updateById(studentClassEntity);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("sms:studentclass:delete")
    public R delete(@RequestBody Long[] ids){
		studentClassService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
