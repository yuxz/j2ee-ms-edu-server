package com.yxz.edu.teacher.service;

import java.util.List;
import java.util.Map;

import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.edu.teacher.vo.TeacherStatisticsTableVo;

public interface TeacherStatisticsService {

	
	EchartsOption statisticsLine(Map<String, Object> params);

	EchartsOption statisticsBar(Map<String, Object> params);

	EchartsOption statisticsPie(Map<String, Object> params);

	List<TeacherStatisticsTableVo> statisticsTable(Map<String, Object> params);

	EchartsOption statisticsScatter(Map<String, Object> params);

	Integer statisticsTeacherCount(Map<String, Object> params);

}
