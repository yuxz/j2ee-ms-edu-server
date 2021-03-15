package com.yxz.edu.tms.dao;

import com.yxz.edu.tms.entity.TeacherEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 教师
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 16:32:08
 */
@Mapper
public interface TeacherDao extends BaseMapper<TeacherEntity> {
	
}
