package com.yxz.edu.institution.service.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yxz.base.common.utils.echarts.EchartsOption;
import com.yxz.base.common.utils.echarts.EchartsOptionSeriesDataPie;
import com.yxz.base.common.utils.echarts.EchartsUtils;
import com.yxz.edu.institution.dao.ClassDao;
import com.yxz.edu.institution.entity.ClassEntity;
import com.yxz.edu.institution.entity.ClassTypeEntity;
import com.yxz.edu.institution.service.ClassStatisticsService;
import com.yxz.edu.institution.service.ClassTypeService;
import com.yxz.edu.institution.vo.ClassStatisticsTableVo;
import com.yxz.edu.institution.vo.StatisticsTableVo;

@Service("classStatisticsService")
public class ClassStatisticsServiceImpl implements ClassStatisticsService {
	@Autowired
	private ClassDao dao;
	@Autowired
	private ClassTypeService classTypeService;

	@Override
	public EchartsOption statisticsLine(Map<String, Object> params) {
		// 1. get count per class Type
		QueryWrapper<ClassEntity> wrapper = new QueryWrapper<ClassEntity>();
		wrapper.groupBy("class_type_id, YEAR(started)");
		wrapper.orderByAsc("class_type_id, YEAR(started) ");
		wrapper.select("class_type_id, YEAR(started) as start_year, count(*) as total_count");
		List<ClassEntity> classList = dao.selectList(wrapper);

		// 2. get class type name
		List<ClassTypeEntity> classTypeList = classTypeService.list();
		List<ClassStatisticsTableVo> statisticsVoList = classList.stream().map(classEntity -> {
			ClassStatisticsTableVo<Integer> classStatisticsVo = new ClassStatisticsTableVo<>();
			BeanUtils.copyProperties(classEntity, classStatisticsVo);
			for (ClassTypeEntity classTypeEntity : classTypeList) {
				if (classTypeEntity.getId() == classEntity.getClassTypeId()) {
					classStatisticsVo.setLegend(classTypeEntity.getName());
					classStatisticsVo.setXAxis(classEntity.getStartYear());
					classStatisticsVo.setValue(classEntity.getTotalCount());
					break;
				}
			}
			return classStatisticsVo;
		}).collect(Collectors.toList());

		// 2. change to Echarts Style Data

		EchartsUtils<ClassStatisticsTableVo> echartsUtils = new EchartsUtils<ClassStatisticsTableVo>();

		return echartsUtils.setEcharts("各校區招生趨勢", statisticsVoList);

	}

	@Override
	public EchartsOption statisticsBar(Map<String, Object> params) {

		// 1. get count per class Type
		QueryWrapper<ClassEntity> wrapper = new QueryWrapper<ClassEntity>();
		wrapper.groupBy("class_type_id, YEAR(started)");
		wrapper.orderByAsc("class_type_id, YEAR(started) ");
		wrapper.select("class_type_id, YEAR(started) as start_year, count(*) as total_count");
		List<ClassEntity> classList = dao.selectList(wrapper);

		// 2. get class type name
		List<ClassTypeEntity> classTypeList = classTypeService.list();
		List<ClassStatisticsTableVo<Integer>> statisticsVoList = classList.stream().map(classEntity -> {
			ClassStatisticsTableVo<Integer> classStatisticsVo = new ClassStatisticsTableVo<>();
			
			BeanUtils.copyProperties(classEntity, classStatisticsVo);
			for (ClassTypeEntity classTypeEntity : classTypeList) {
				if (classTypeEntity.getId() == classEntity.getClassTypeId()) {
					classStatisticsVo.setLegend(classTypeEntity.getName());
					classStatisticsVo.setXAxis(classEntity.getStartYear());
					classStatisticsVo.setValue(classEntity.getTotalCount());
					break;
				}
			}
			return classStatisticsVo;
		}).collect(Collectors.toList());

		// 2. change to Echarts Style Data

		EchartsUtils<ClassStatisticsTableVo<Integer>> echartsUtils = new EchartsUtils<ClassStatisticsTableVo<Integer>>();

		return echartsUtils.setEcharts("各校區招生数量", statisticsVoList);
	}

	@Override
	public EchartsOption statisticsPie(Map<String, Object> params) {

		// 1. get count per class Type
		QueryWrapper<ClassEntity> wrapper = new QueryWrapper<ClassEntity>();
		wrapper.groupBy("class_type_id");
		wrapper.select("class_type_id, count(*) as total_count");
		wrapper.eq("YEAR(started)", Calendar.getInstance().get(Calendar.YEAR));
		List<ClassEntity> classList = dao.selectList(wrapper);

		// 2. get class type name
		List<ClassTypeEntity> classTypeList = classTypeService.list();

		// 3. create List<EchartsSeriesDataVo>
		List<ClassStatisticsTableVo<EchartsOptionSeriesDataPie>> statisticsVoList = classList.stream().map(classEntity -> {
			ClassStatisticsTableVo<EchartsOptionSeriesDataPie> statisticsVo = new ClassStatisticsTableVo<>();
			EchartsOptionSeriesDataPie echartsOptionSeriesDataPie = new EchartsOptionSeriesDataPie();
			for (ClassTypeEntity classTypeEntity : classTypeList) {
				if (classTypeEntity.getId() == classEntity.getClassTypeId()) {
					echartsOptionSeriesDataPie.setName(classTypeEntity.getName());
					echartsOptionSeriesDataPie.setValue(classEntity.getTotalCount());
					break;
				}
			}
			statisticsVo.setLegend(null);
			statisticsVo.setXAxis(null);
			statisticsVo.setValue(echartsOptionSeriesDataPie);
			return statisticsVo;
		}).collect(Collectors.toList());

//		// 4. create EchartsSeriesVo
//		EchartsOptionSeries echartsSeriesVo = new EchartsOptionSeries<EchartsOptionSeriesDataPie>();
//		echartsSeriesVo.setName("");
////		        echartsSeriesVo.setType("");
////		        echartsSeriesVo.setStack("");
//		echartsSeriesVo.setData(echartsSeriesDataList);
//
//		// 5. create List<EchartsSeriesVo>
//		List<EchartsOptionSeries> echartsSerieslist = new ArrayList<>();
//		echartsSerieslist.add(echartsSeriesVo);
//
//		// 6. create EchartsStatisticsVo
//		EchartsOption echartsStatisticsVo = new EchartsOption();
//		echartsStatisticsVo.setTitle("培训类型");
//		echartsStatisticsVo.setLegend(null);
//		echartsStatisticsVo.setXAxis(null);
//		echartsStatisticsVo.setYAxis(null);
//		echartsStatisticsVo.setSeries(echartsSerieslist);
//
//		return echartsStatisticsVo;

		// 2. change to Echarts Style Data

		EchartsUtils<ClassStatisticsTableVo<EchartsOptionSeriesDataPie>> echartsUtils = new EchartsUtils<ClassStatisticsTableVo<EchartsOptionSeriesDataPie>>();

		return echartsUtils.setEcharts("各类型招生比例", statisticsVoList);
	}

	@Override
	public EchartsOption statisticsScatter(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StatisticsTableVo statisticsTable(Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer statisticsClassCount(Map<String, Object> params) {
		Integer selectCount = dao.selectCount(new QueryWrapper<>());
		return selectCount;
	}

	@Override
	public EchartsOption statisticsLineClassByClasstype(Map<String, Object> params) {
		// 1. get count per class Type
		QueryWrapper<ClassEntity> wrapper = new QueryWrapper<ClassEntity>();
		wrapper.groupBy("class_type_id,QUARTER(started)");
		wrapper.orderByAsc("class_type_id,QUARTER(started)");
		wrapper.select("class_type_id , QUARTER(started) AS quarter ,COUNT(*) AS total_count ");
		wrapper.eq("YEAR(started)", Calendar.getInstance().get(Calendar.YEAR));
		List<ClassEntity> list = dao.selectList(wrapper);

		// class_type_id -> classTypeName
		Map<String, Object> tempVal = new HashMap<>();

		List<ClassStatisticsTableVo> statisticsVoList = list.stream().map(entity -> {

			ClassStatisticsTableVo<Integer> statisticsVo = new ClassStatisticsTableVo<>();
			if (entity.getClassTypeId() != tempVal.get("id")) {
				// Map classType = (Map)
				// institutionFeignService.classtypeInfo(entity.getClassTypeId()).get("classType");
				ClassTypeEntity classType = classTypeService.getById(entity.getClassTypeId());

				tempVal.put("id", entity.getClassTypeId());
				if (classType != null) {
					tempVal.put("name", classType.getName());
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

		EchartsUtils<ClassStatisticsTableVo> echartsUtils = new EchartsUtils<ClassStatisticsTableVo>();

		return echartsUtils.setEcharts("各校區招生趨勢", statisticsVoList);
	}

}
