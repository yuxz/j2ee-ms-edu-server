package com.yxz.edu.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.teacher.entity.TeacherExperienceEntity;

import java.util.Map;

/**
 * Working Experience
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */
public interface TeacherExperienceService extends IService<TeacherExperienceEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

