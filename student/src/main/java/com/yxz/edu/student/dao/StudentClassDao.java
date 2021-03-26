package com.yxz.edu.student.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.edu.student.entity.StudentClassEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 班级学生
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 19:17:49
 */
@Mapper
public interface StudentClassDao extends BaseMapper<StudentClassEntity> {
	
}
