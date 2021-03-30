package com.yxz.edu.teacher.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.teacher.entity.TeacherBackgroundEntity;

import java.util.Map;

/**
 * Education Background
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-29 13:59:24
 */
public interface TeacherBackgroundService extends IService<TeacherBackgroundEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

