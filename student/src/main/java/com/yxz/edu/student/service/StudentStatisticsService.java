package com.yxz.edu.student.service;

import java.util.List;
import java.util.Map;

import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.edu.student.vo.StudentEchartsStatisticsVo;

public interface StudentStatisticsService {
	EchartsOption statisticsLine(Map<String, Object> params);
	
	EchartsOption statisticsBar(Map<String, Object> params);
	
	EchartsOption statisticsPie(Map<String, Object> params);
	
	EchartsOption statisticsScatter(Map<String, Object> params);
	
	List<StudentEchartsStatisticsVo<Integer>>  statisticsTable(Map<String, Object> params);

	Integer statisticsStudentCount(Map<String, Object> params);

	EchartsOption statisticsLineByClasstype(Map<String, Object> params);

	EchartsOption statisticsLineByCampus(Map<String, Object> params);
}
