package com.yxz.edu.institution.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.institution.entity.ClassEntity;

public interface StatisticsService extends IService<ClassEntity> {

	EchartsStatisticsVo statisticsLine(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsBar(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsPie(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsScatter(Map<String, Object> params);

}
