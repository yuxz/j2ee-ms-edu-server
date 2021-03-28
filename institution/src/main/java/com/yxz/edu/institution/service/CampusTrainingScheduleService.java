package com.yxz.edu.institution.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.edu.institution.entity.CampusTrainingScheduleEntity;

import java.util.Map;

/**
 * 
 *
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-28 17:50:49
 */
public interface CampusTrainingScheduleService extends IService<CampusTrainingScheduleEntity> {

    PageUtils queryPage(Map<String, Object> params);

	PageUtils queryPage(Map<String, Object> params, Long campusId);
}

