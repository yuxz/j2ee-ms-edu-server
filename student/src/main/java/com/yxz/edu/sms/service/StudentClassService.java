package com.yxz.edu.sms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.sms.entity.StudentClassEntity;

import java.util.Map;

/**
 * 班级学生
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 19:17:49
 */
public interface StudentClassService extends IService<StudentClassEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

