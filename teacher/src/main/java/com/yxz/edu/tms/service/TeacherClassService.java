package com.yxz.edu.tms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.tms.entity.TeacherClassEntity;

import java.util.Map;

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
	
}

