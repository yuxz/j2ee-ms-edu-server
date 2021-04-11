package com.yxz.edu.student.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.student.entity.StudentClassEntity;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.vo.StudentClassListVo;
import com.yxz.edu.student.vo.StudentClassVo;

/**
 * 班级学生
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 19:17:49
 */
public interface StudentClassService extends IService<StudentClassEntity> {

    PageUtils queryPage(Map<String, Object> params);

	List<StudentClassListVo> queryIncludingByClass(Long classId);

	PageUtils queryPageNotIncludingByClass(Map<String, Object> params, Long classId);

	void saveBatch(List<StudentClassVo> teacherClassVos);
	
	List<StudentEntity>  queryUnassignedStudentsByClass();
	void autoAssign();
}

