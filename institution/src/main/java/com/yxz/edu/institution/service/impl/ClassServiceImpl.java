package com.yxz.edu.institution.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.to.ClassTo;
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
import com.yxz.edu.institution.vo.ClassAssignVo;
import com.yxz.edu.institution.vo.ClassDetailVo;
import com.yxz.edu.institution.vo.ClassListVo;

@Service("classService")
public class ClassServiceImpl extends ServiceImpl<ClassDao, ClassEntity> implements ClassService {
	@Autowired
	private CampusService campusService;
	@Autowired
	private ClassService classService;
	@Autowired
	private ClassroomService classroomService;
	@Autowired
	private ClassTypeService classTypeService;

	@Override
	public PageUtils queryPage(Map<String, Object> params, Integer status) {
		QueryWrapper<ClassEntity> queryWrapper = new QueryWrapper<ClassEntity>();

		if (status != null) {
			
			switch (status) {
			case 0: //new 
				queryWrapper.gt("started", new Date());
				break;

			case 1: // on reading
				queryWrapper.nested(i->i.lt("started", new Date()).gt("ended", new Date()));
				break;
			case 2: // finished 
				queryWrapper.lt("ended", new Date());
				break;			
			}
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

		pageUtils.setList(collect);

		return pageUtils;
	}

	@Override
	public ClassDetailVo getDetailById(Long id) {
		ClassEntity classEntity = this.getById(id);
		ClassDetailVo classDetailVo = new ClassDetailVo();
		BeanUtils.copyProperties(classEntity, classDetailVo);
		CampusEntity campusEntity = campusService.getById(classDetailVo.getCampusId());
		ClassroomEntity classroomEntity = classroomService.getById(classDetailVo.getClassroomId());
		ClassTypeEntity classTypeEntity = classTypeService.getById(classDetailVo.getClassTypeId());
		classDetailVo.setCampusName(campusEntity.getName());
		classDetailVo.setClassroomName(classroomEntity.getName());
		classDetailVo.setClassTypeName(classTypeEntity.getName());

		return classDetailVo;
	}

	@Override
	public List<ClassTo> queryAllClasses() {
		//
		List<ClassEntity> classList = this.list(new QueryWrapper<ClassEntity>().eq("is_finished", 0));
		List<ClassTo> classTos = classList.stream().map(classEntity -> {
			ClassTo classTo = new ClassTo();
			classTo.setId(classEntity.getId());
			classTo.setCampusId(classEntity.getCampusId());
			classTo.setCampusScheduleId(classEntity.getCampusScheduleId());
			classTo.setClassTypeId(classEntity.getClassTypeId());
			classTo.setClassLevelId(classEntity.getClassLevelId());
			classTo.setMaxinum(classEntity.getMaximum());
			return classTo;
		}).collect(Collectors.toList());

		return classTos;
	}

	@Override
	public String classListByStatus(String status) {
		QueryWrapper<ClassEntity> queryWrapper = new QueryWrapper<ClassEntity>();

		if (!StringUtils.isEmpty(status)) {
			
			switch (status) {
			case "new": //new 
				queryWrapper.gt("started", new Date());
				break;

			case "on": // on reading
				queryWrapper.nested(i->i.lt("started", new Date()).gt("ended", new Date()));
				break;
			case "off": // finished 
				queryWrapper.lt("ended", new Date());
				break;			
			}
		}

		List<ClassEntity> list = baseMapper.selectList(queryWrapper);
		List<String> classIds = list.stream().map(mapper-> Long.toString(mapper.getId())).collect(Collectors.toList());

		return String.join(",", classIds);
	}

}