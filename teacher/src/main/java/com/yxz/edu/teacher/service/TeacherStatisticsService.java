package com.yxz.edu.teacher.service;

import java.util.List;
import java.util.Map;

import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;
import com.yxz.edu.teacher.vo.TeacherStatisticsTableVo;

public interface TeacherStatisticsService {

	
	EchartsStatisticsVo statisticsLine(Map<String, Object> params);

	EchartsStatisticsVo statisticsBar(Map<String, Object> params);

	EchartsStatisticsVo statisticsPie(Map<String, Object> params);

	List<TeacherStatisticsTableVo> statisticsTable(Map<String, Object> params);

	EchartsStatisticsVo statisticsScatter(Map<String, Object> params);

	Integer statisticsTeacherCount(Map<String, Object> params);

}
