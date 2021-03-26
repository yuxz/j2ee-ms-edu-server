package com.yxz.edu.institution.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.institution.dao.ClassDao;
import com.yxz.edu.institution.entity.CampusEntity;
import com.yxz.edu.institution.entity.ClassEntity;
import com.yxz.edu.institution.entity.ClassTypeEntity;
import com.yxz.edu.institution.entity.ClassroomEntity;
import com.yxz.edu.institution.service.CampusService;
import com.yxz.edu.institution.service.ClassService;
import com.yxz.edu.institution.service.ClassTypeService;
import com.yxz.edu.institution.service.ClassroomService;
import com.yxz.edu.institution.vo.ClassListVo;

@Service("classService")
public class ClassServiceImpl extends ServiceImpl<ClassDao, ClassEntity> implements ClassService {
	@Autowired
	CampusService campusService;
	@Autowired
	ClassroomService classroomService;
	@Autowired
	ClassTypeService classTypeService;

	@Override
	public PageUtils queryPage(Map<String, Object> params, Long isFinished) {
		QueryWrapper<ClassEntity> queryWrapper = new QueryWrapper<ClassEntity>();

		if (isFinished != null) {
			queryWrapper.eq("is_finished", isFinished);
		}

		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			// attr_id attr_name
			queryWrapper.and((wrapper) -> {
				wrapper.like("name", key);// .or().like("attr_name",key);
			});
		}

		IPage<ClassEntity> page = this.page(new Query<ClassEntity>().getPage(params), queryWrapper);

		PageUtils pageUtils = new PageUtils(page);
		List<ClassEntity> records = page.getRecords();
		List<ClassListVo> collect = records.stream().map((classEntity) -> {
			ClassListVo classListVo = new ClassListVo();
			BeanUtils.copyProperties(classEntity, classListVo);
			// query campus
			CampusEntity campus = campusService.getById(classEntity.getCampusId());
			if (campus != null)
				classListVo.setCampusName(campus.getName());
			// query classroom
			ClassroomEntity classroom = classroomService.getById(classEntity.getClassroomId());
			if (classroom != null)
				classListVo.setClassroomName(classroom.getName());
			// query classtype
			ClassTypeEntity classtype = classTypeService.getById(classEntity.getClassTypeId());
			if (classtype != null)
				classListVo.setClassTypeName(classtype.getName());
			return classListVo;
		}).collect(Collectors.toList());

//        IPage<ClassEntity> page = this.page(
//                new Query<ClassEntity>().getPage(params),
//                new QueryWrapper<ClassEntity>()
//        );
		pageUtils.setList(collect);

		return pageUtils;
	}

}