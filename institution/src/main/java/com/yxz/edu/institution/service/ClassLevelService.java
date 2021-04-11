package com.yxz.edu.institution.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.institution.entity.ClassLevelEntity;

import java.util.Map;

/**
 * 年级
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-04-11 12:29:23
 */
public interface ClassLevelService extends IService<ClassLevelEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

