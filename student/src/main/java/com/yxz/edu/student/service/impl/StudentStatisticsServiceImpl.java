package com.yxz.edu.student.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxz.base.common.vo.echarts.EchartsSeriesVo;
import com.yxz.base.common.vo.echarts.EchartsStatisticsVo;

import com.yxz.edu.student.dao.StudentDao;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.feign.InstitutionFeignService;
import com.yxz.edu.student.service.StudentStatisticsService;
import com.yxz.edu.student.vo.StudentStatisticsTableVo;

@Service("studentStatisticsService")
public class StudentStatisticsServiceImpl implements StudentStatisticsService {

	@Autowired
	private StudentDao dao;

	@Autowired
	private InstitutionFeignService institutionFeignService;

	@Override
	public EchartsStatisticsVo statisticsLine(Map<String, Object> params) {

		// 1. get count per class Type
		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>();
		wrapper.groupBy("QUARTER(registered),class_type_id");
		wrapper.orderByAsc("QUARTER(registered),class_type_id");
		wrapper.select("QUARTER(registered) AS registered ,class_type_id , COUNT(*) AS total_count ");
		wrapper.eq("YEAR(registered)", Calendar.getInstance().get(Calendar.YEAR));
		List<StudentEntity> list = dao.selectList(wrapper);

		List<StudentStatisticsTableVo> statisticsVoList = list.stream().map(entity -> {

			StudentStatisticsTableVo statisticsVo = new StudentStatisticsTableVo();
			Map classType = (Map) institutionFeignService.classtypeInfo(entity.getClassTypeId()).get("classType");
			if (classType != null)
				statisticsVo.setClassTypeName((String) classType.get("name"));
			statisticsVo.setQuarter(entity.getRegistered().toString());
			statisticsVo.setTotalCount(entity.getTotalCount());//
			return statisticsVo;
		}).collect(Collectors.toList());

		// 3. create EchartsStatisticsVo
		EchartsStatisticsVo echartsStatisticsVo = new EchartsStatisticsVo();

		// 3.1 set title
		echartsStatisticsVo.setTitle("季度招生走勢");

		// 3.2-1 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		Set<String> legendSet = new HashSet<>();

		// 3.3-1 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
		Set<String> xAxisSet = new HashSet<>();

		// set legend and xAxis
		for (StudentStatisticsTableVo entity : statisticsVoList) {
			// 1. set legend data
			legendSet.add(entity.getClassTypeName());
			// 2. set aAxis data
			xAxisSet.add(entity.getQuarter());
		}
		;

		// 3.4 set series
		String tempClassTypeName = null;
		Boolean newSeries = true;

		List<EchartsSeriesVo> echartsSeriesList = new ArrayList<>();
		for (StudentStatisticsTableVo statisticsVo : statisticsVoList) {
			// the same class type
			if (tempClassTypeName == null || tempClassTypeName == statisticsVo.getClassTypeName()) {
			}
			// not same class type
			else {
				newSeries = true;
			}

			if (newSeries) {
				EchartsSeriesVo echartsSeriesVo = new EchartsSeriesVo();
				List<Integer> dataList = new ArrayList<>();
				echartsSeriesVo.setData(dataList);
				echartsSeriesVo.setType("line");
				echartsSeriesVo.setStack("total");
				echartsSeriesVo.setName(statisticsVo.getClassTypeName());
				echartsSeriesList.add(echartsSeriesVo);
				newSeries = false;
			}
			echartsSeriesList.get(echartsSeriesList.size() - 1).getData().add(statisticsVo.getTotalCount());
			tempClassTypeName = statisticsVo.getClassTypeName();
		}
		;

		// 3.2-2 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		echartsStatisticsVo.setLegend(new ArrayList<String>(legendSet));
		// 3.3-2 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
		List<String> xAxislist = new ArrayList<String>(xAxisSet);
		Collections.sort(xAxislist);
		echartsStatisticsVo.setXAxis(xAxislist);

		echartsStatisticsVo.setSeries(echartsSeriesList);

		return echartsStatisticsVo;

	}

	@Override
	public EchartsStatisticsVo statisticsBar(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EchartsStatisticsVo statisticsPie(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EchartsStatisticsVo statisticsScatter(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<StudentStatisticsTableVo> statisticsTable(Map<String, Object> params) {
		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<>();
		wrapper.groupBy("campus_id, class_type_id,class_level_id");
		wrapper.orderByAsc("campus_id, class_type_id,class_level_id ");
		wrapper.select("campus_id, class_type_id,class_level_id, count(*) as total_count");
		List<StudentEntity> studentList = dao.selectList(wrapper);

		List<StudentStatisticsTableVo> collect = studentList.stream().map(mapper -> {
			StudentStatisticsTableVo statisticsTableVo = new StudentStatisticsTableVo();
			BeanUtils.copyProperties(mapper, statisticsTableVo);

			// query Campus
//			Map institution = (Map)institutionFeignService.institutionInfo(mapper.getInstitutionId());
//			if (institution != null)
//				statisticsTableVo.setInstitutionName((String)institution.get("name"));
			// query Campus Schedule
			Map campus = (Map) institutionFeignService.campusInfo(mapper.getCampusId()).get("campus");
			if (campus != null)
				statisticsTableVo.setCampusName((String) campus.get("name"));
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
			System.out.println("classType---->>>" + classType);
			if (classType != null)
				statisticsTableVo.setClassTypeName((String) classType.get("name"));

//        	//query class Level
			Map classLevel = (Map) institutionFeignService.classlevelInfo(mapper.getClassLevelId()).get("classLevel");
			if (classLevel != null)
				statisticsTableVo.setClassLevelName((String) classLevel.get("name"));

			statisticsTableVo.setTotalCount(mapper.getTotalCount());
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
	public EchartsStatisticsVo statisticsLineStudent(Map<String, Object> params) {

		// 1. get count per class Type
		QueryWrapper<StudentEntity> wrapper = new QueryWrapper<StudentEntity>();
		wrapper.groupBy("class_type_id,QUARTER(registered)");
		wrapper.orderByAsc("class_type_id,QUARTER(registered)");
		wrapper.select("class_type_id , QUARTER(registered) AS quarter ,COUNT(*) AS total_count ");
		wrapper.eq("YEAR(registered)", Calendar.getInstance().get(Calendar.YEAR));
		List<StudentEntity> list = dao.selectList(wrapper);
        
		// class_type_id -> classTypeName
		Map<String, Object> tempVal = new HashMap<>();

		List<StudentStatisticsTableVo> statisticsVoList = list.stream().map(entity -> {

			StudentStatisticsTableVo statisticsVo = new StudentStatisticsTableVo();
			if (entity.getClassTypeId() != tempVal.get("id")) {
				Map classType = (Map) institutionFeignService.classtypeInfo(entity.getClassTypeId()).get("classType");
				tempVal.put("id", entity.getClassTypeId());
				if (classType != null) {
					tempVal.put("name", (String) classType.get("name"));
				} else {
					tempVal.put("name", "anonymity");
				}
			}
			;
			statisticsVo.setClassTypeName((String) tempVal.get("name"));
			statisticsVo.setQuarter(entity.getQuarter());
			statisticsVo.setTotalCount(entity.getTotalCount());//

			return statisticsVo;
		}).collect(Collectors.toList());

		// 3. create EchartsStatisticsVo
		EchartsStatisticsVo echartsStatisticsVo = new EchartsStatisticsVo();

		// 3.1 set title
		echartsStatisticsVo.setTitle("季度招生走勢");

		// 3.2-1 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		Set<String> legendSet = new HashSet<>();

		// 3.3-1 set xAxis [ '2018', '2019', '2020', '2021', '2022' ]
		Set<String> xAxisSet = new HashSet<>();

		// set legend and xAxis
		for (StudentStatisticsTableVo entity : statisticsVoList) {
			// 3.2-2. set legend data
			legendSet.add(entity.getClassTypeName());
			// 3.3-2. set aAxis data
			xAxisSet.add(entity.getQuarter());
		}
		;

		// sort [ '2019', '2018', '2021', '2020', '2022' ] => [ '2018', '2019', '2020',
		// '2021', '2022' ]
		List<String> xAxisList = new ArrayList<>(xAxisSet);
		Collections.sort(xAxisList);
		System.out.println(xAxisList);

		// 3.2-3 set legend [ '私校奖学金计划', 'VCE备考冲刺', 'AEAS私校入学考试'],
		echartsStatisticsVo.setLegend(new ArrayList<String>(legendSet));
		// 3.3-3 set xAxis [ '2019', '2018', '2021', '2020', '2022' ] => [ '2018',
		// '2019', '2020', '2021', '2022' ]
		List<String> xAxislist = new ArrayList<String>(xAxisSet);
		Collections.sort(xAxislist);
		echartsStatisticsVo.setXAxis(xAxislist);

		// 3.4 set series
		String tempClassTypeName = null;
		Boolean newSeries = true;
		Integer xAxisIndex = 0;
		List<EchartsSeriesVo> echartsSeriesList = new ArrayList<>();
		for (StudentStatisticsTableVo statisticsVo : statisticsVoList) {
			// the same class type
			if (tempClassTypeName == null || tempClassTypeName == statisticsVo.getClassTypeName()) {
			}
			// not same class type
			else {
				newSeries = true;
				xAxisIndex = 0;
			}

			if (newSeries) {
				EchartsSeriesVo echartsSeriesVo = new EchartsSeriesVo();
				List<Integer> dataList = new ArrayList<>();
				echartsSeriesVo.setData(dataList);
//						echartsSeriesVo.setType("line");
//						echartsSeriesVo.setStack("total");
				echartsSeriesVo.setName(statisticsVo.getClassTypeName());
				echartsSeriesList.add(echartsSeriesVo);
				newSeries = false;
			}
			int indexOf = xAxisList.indexOf(statisticsVo.getQuarter());
			echartsSeriesList.get(echartsSeriesList.size() - 1).getData().add(indexOf, statisticsVo.getTotalCount());
			tempClassTypeName = statisticsVo.getClassTypeName();
		};
		
		echartsStatisticsVo.setSeries(echartsSeriesList);

		return echartsStatisticsVo;

	}

}
