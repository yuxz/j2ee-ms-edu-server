package com.yxz.edu.institution.service;

import java.util.Map;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.institution.entity.ClassEntity;
import com.yxz.edu.institution.vo.StatisticsTableVo;

public interface InstitutionStatisticsService {

	EchartsStatisticsVo statisticsLine(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsBar(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsPie(Map<String, Object> params);
	
	EchartsStatisticsVo statisticsScatter(Map<String, Object> params);

	StatisticsTableVo statisticsTable(Map<String, Object> params);

	Integer statisticsCampus(Map<String, Object> params);

	Integer statisticsClasses(Map<String, Object> params);

	EchartsStatisticsVo statisticsLineClass(Map<String, Object> params);

}
