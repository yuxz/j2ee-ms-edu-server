package com.yxz.edu.institution.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.institution.entity.ClassTypeEntity;

import java.util.Map;

/**
 * 班级/课程类型
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
public interface ClassTypeService extends IService<ClassTypeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

