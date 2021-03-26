package com.yxz.edu.teacher.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.teacher.entity.TeacherClassEntity;
import com.yxz.edu.teacher.vo.TeacherClassListVo;
import com.yxz.edu.teacher.vo.TeacherClassVo;

/**
 * 班级老师
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
public interface TeacherClassService extends IService<TeacherClassEntity> {

    PageUtils queryPage(Map<String, Object> params);

    
	PageUtils queryPage(Map<String, Object> params, Long classId);


	PageUtils queryPageNotIncludingByClass(Map<String, Object> params, Long classId);


	List<TeacherClassListVo> queryIncludingByClass(Long classId);


	void saveBatch(List<TeacherClassVo> teacherClassVos);
	
}

