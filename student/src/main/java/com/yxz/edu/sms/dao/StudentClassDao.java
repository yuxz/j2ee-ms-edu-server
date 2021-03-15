package com.yxz.edu.sms.dao;

import com.yxz.edu.sms.entity.StudentClassEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
