package com.yxz.edu.teacher.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yxz.edu.teacher.entity.TeacherClassEntity;

import org.apache.ibatis.annotations.Mapper;

/**
 * 班级老师
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
@Mapper
public interface TeacherClassDao extends BaseMapper<TeacherClassEntity> {
	
}
