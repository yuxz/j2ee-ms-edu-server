package com.yxz.edu.student.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.base.common.utils.echarts.EchartsUtils;
import com.yxz.edu.student.dao.StudentDao;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.feign.InstitutionFeignService;
import com.yxz.edu.student.service.StudentStatisticsService;
import com.yxz.edu.student.vo.StudentEchartsStatisticsVo;

@Service("studentStatisticsService")
public class StudentStatisticsServiceImpl implements StudentStatisticsService {

	@Autowired
	private StudentDao dao;

	@Autowired
	private InstitutionFeignService institutionFeignService;

	private String type = "quarter";
	private int started = Calendar.getInstance().get(Calendar.YEAR);
	private int ended = Calendar.getInstance().get(Calendar.YEAR);

	@Override
	public EchartsOption statisticsLine(Map<String, Object> params) {
		// 0. set params
		setParams(params);

		// 1. get count per class Type
		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>();
		wrapper.select("QUARTER(registered) AS registered ,class_type_id , COUNT(*) AS total_count ");
		wrapper.groupBy("QUARTER(registered),class_type_id");
		wrapper.orderByAsc("QUARTER(registered),class_type_id");
//		wrapper.eq("YEAR(registered)", Calendar.getInstance().get(Calendar.YEAR));
		wrapper.between("YEAR(registered)", started, ended);
		List<StudentEntity> list = dao.selectList(wrapper);

		List<StudentEchartsStatisticsVo<Integer>> statisticsVoList = list.stream().map(entity -> {

			StudentEchartsStatisticsVo<Integer> statisticsVo = new StudentEchartsStatisticsVo<>();
			Map classType = (Map) institutionFeignService.classtypeInfo(entity.getClassTypeId()).get("classType");
			if (classType != null)
				statisticsVo.setLegend((String) classType.get("name"));
			statisticsVo.setXAxis(entity.getRegistered().toString());
			statisticsVo.setValue(entity.getTotalCount());//
			return statisticsVo;
		}).collect(Collectors.toList());

		EchartsUtils<StudentEchartsStatisticsVo<Integer>> echartsUtils = new EchartsUtils<StudentEchartsStatisticsVo<Integer>>();

		return echartsUtils.setEcharts("各校區招生趨勢", statisticsVoList);

	}

	@Override
	public EchartsOption statisticsBar(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EchartsOption statisticsPie(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EchartsOption statisticsScatter(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentEchartsStatisticsVo<Integer>> statisticsTable(Map<String, Object> params) {
		// 0. set params
		setParams(params);

		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
		wrapper.select("campus_id, class_type_id,class_level_id, count(*) as total_count");
		wrapper.groupBy("campus_id, class_type_id,class_level_id");
		wrapper.orderByAsc("campus_id, class_type_id,class_level_id ");
		wrapper.between("YEAR(registered)", started, ended);
		List<StudentEntity> studentList = dao.selectList(wrapper);

		List<StudentEchartsStatisticsVo<Integer>> collect = studentList.stream().map(mapper -> {
			StudentEchartsStatisticsVo<Integer> statisticsTableVo = new StudentEchartsStatisticsVo<>();
			BeanUtils.copyProperties(mapper, statisticsTableVo);

			// query Campus
//			Map institution = (Map)institutionFeignService.institutionInfo(mapper.getInstitutionId());
//			if (institution != null)
//				statisticsTableVo.setInstitutionName((String)institution.get("name"));
			// query Campus Schedule
			HashMap<String, String> campus = (HashMap<String, String>) institutionFeignService
					.campusInfo(mapper.getCampusId()).get("campus");
			if (campus != null) {

				statisticsTableVo.setCampusName((String) campus.get("name"));
			} else {
				statisticsTableVo.setCampusName("anonymity");
			}
			// query schedule
//        	Map schedule = (Map)institutionFeignService.campusscheduleInfo(mapper.getCampusScheduleId()).get("campusTrainingSchedule");
//        	if(schedule != null)
//        		statisticsTableVo.setCampusscheduleName((String)schedule.get("name"));
			// query classroom
//        	Map classroom = (Map)institutionFeignService.classroomInfo(mapper.getClassroomId()).get("classroom");
//        	if(classroom != null)
//        		statisticsTableVo.setClassroomName((String)classroom.get("name"));

//        	//query class Type
			Map classType = (Map) institutionFeignService.classtypeInfo(mapper.getClassTypeId()).get("classType");
//			System.out.println("classType---->>>" + classType);
			if (classType != null) {
				statisticsTableVo.setClassTypeName((String) classType.get("name"));
			} else {
				statisticsTableVo.setClassTypeName("anonymity");
			}
//        	//query class Level
			Map classLevel = (Map) institutionFeignService.classlevelInfo(mapper.getClassLevelId()).get("classLevel");
			if (classLevel != null) {
				statisticsTableVo.setClassLevelName((String) classLevel.get("name"));
			} else {
				statisticsTableVo.setClassLevelName("anonymity");
			}
			statisticsTableVo.setValue(mapper.getTotalCount());
			return statisticsTableVo;
		}).collect(Collectors.toList());
		return collect;

	}

	@Override
	public Integer statisticsStudentCount(Map<String, Object> params) {

		Integer selectCount = dao.selectCount(new QueryWrapper<>());
		return selectCount;
	}

	@Override
	public EchartsOption statisticsLineByClasstype(Map<String, Object> params) {

		/// 0. set params
		setParams(params);

		// 1. get statistic data
		// 1.1 get count per class Type
		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>();

		switch (type) {
		case "year":
			wrapper.select("class_type_id , YEAR(registered) AS year ,COUNT(*) AS total_count ");
			wrapper.groupBy("class_type_id, YEAR(registered)");
			wrapper.orderByAsc("class_type_id, YEAR(registered)");
			wrapper.between("YEAR(registered)", started, ended);
			break;
		case "quarter":
			wrapper.select("class_type_id , QUARTER(registered) AS quarter ,COUNT(*) AS total_count ");
			wrapper.groupBy("class_type_id, QUARTER(registered)");
			wrapper.orderByAsc("class_type_id, QUARTER(registered)");
			wrapper.between("YEAR(registered)", started, ended);
			break;
		case "month":
			wrapper.select("class_type_id , MONTH(registered) AS month ,COUNT(*) AS total_count ");
			wrapper.groupBy("class_type_id, MONTH(registered)");
			wrapper.orderByAsc("class_type_id, MONTH(registered)");
			wrapper.between("YEAR(registered)", started, ended);
			break;
		case "week":
			wrapper.select("class_type_id , WEEK(registered) AS week ,COUNT(*) AS total_count ");
			wrapper.groupBy("class_type_id, WEEK(registered)");
			wrapper.orderByAsc("class_type_id,WEEK(registered)");
			wrapper.between("YEAR(registered)", started, ended);
			break;
		case "day":
			wrapper.select("class_type_id , DAY(registered) AS day ,COUNT(*) AS total_count ");
			wrapper.groupBy("class_type_id, DAY(registered)");
			wrapper.orderByAsc("class_type_id, DAY(registered)");
			wrapper.between("YEAR(registered)", started, ended);
		}

		List<StudentEntity> list = dao.selectList(wrapper);

		// 1.2 class_type_id -> classTypeName
		Map<String, Object> tempVal = new HashMap<>();

		List<StudentEchartsStatisticsVo<Integer>> statisticsVoList = list.stream().map(entity -> {

			StudentEchartsStatisticsVo<Integer> statisticsVo = new StudentEchartsStatisticsVo<>();
			if (entity.getClassTypeId() != tempVal.get("id")) {
				Map classType = (Map) institutionFeignService.classtypeInfo(entity.getClassTypeId()).get("classType");
				tempVal.put("id", entity.getClassTypeId());
				if (classType != null) {
					tempVal.put("name", classType.get("name"));
				} else {
					tempVal.put("name", "anonymity");
				}
			}
			;
			statisticsVo.setLegend((String) tempVal.get("name"));
			statisticsVo.setXAxis(entity.getQuarter());
			statisticsVo.setValue(entity.getTotalCount());//

			return statisticsVo;
		}).collect(Collectors.toList());

		// 2. change to Echarts Style Data

		EchartsUtils<StudentEchartsStatisticsVo<Integer>> echartsUtils = new EchartsUtils<StudentEchartsStatisticsVo<Integer>>();

		return echartsUtils.setEcharts("各類別招生趨勢", statisticsVoList);

	}

	@Override
	public EchartsOption statisticsLineByCampus(Map<String, Object> params) {
		// 0. set params
		setParams(params);

		// 1. get statistic data
		// 1.1 get count per class Type
		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>();
		wrapper.select("campus_id, QUARTER(registered), COUNT(*) AS total_count ");
		wrapper.groupBy("campus_id, QUARTER(registered)");
		wrapper.orderByAsc("campus_id, QUARTER(registered)");
		// wrapper.eq("YEAR(registered)", Calendar.getInstance().get(Calendar.YEAR));
		wrapper.between("YEAR(registered)", started, ended);
		List<StudentEntity> list = dao.selectList(wrapper);

		// 1.2 campus_id -> campusName
		Map<String, Object> tempVal = new HashMap<>();

		List<StudentEchartsStatisticsVo<Integer>> statisticsVoList = list.stream().map(entity -> {

			StudentEchartsStatisticsVo<Integer> statisticsVo = new StudentEchartsStatisticsVo<>();
			if (entity.getClassTypeId() != tempVal.get("id")) {
				Map campus = (Map) institutionFeignService.campusInfo(entity.getCampusId()).get("campus");
				tempVal.put("id", entity.getCampusId());
				if (campus != null) {
					tempVal.put("name", campus.get("name"));
				} else {
					tempVal.put("name", "anonymity");
				}
			}
			;
			statisticsVo.setLegend((String) tempVal.get("name"));
			statisticsVo.setXAxis(entity.getQuarter());
			statisticsVo.setValue(entity.getTotalCount());//

			return statisticsVo;
		}).collect(Collectors.toList());

		// 2. change to Echarts Style Data

		EchartsUtils<StudentEchartsStatisticsVo<Integer>> echartsUtils = new EchartsUtils<StudentEchartsStatisticsVo<Integer>>();

		return echartsUtils.setEcharts("各校區招生趨勢", statisticsVoList);
	}

	private void setParams(Map<String, Object> params) {

		String typeV = (String) params.get("type");
		if (!StringUtils.isEmpty(typeV))
			type = typeV;

		String startedV = (String) params.get("started");
		if (StringUtils.isNumeric(startedV))
			started = Integer.valueOf((String) params.get("started")).intValue();
		

		String endedV = (String) params.get("ended");
		if (StringUtils.isNumeric(endedV))
			ended = Integer.valueOf((String) params.get("started")).intValue();
		

	}

}
