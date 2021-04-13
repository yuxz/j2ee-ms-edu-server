package com.yxz.edu.student.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.ibatis.io.ResolverUtil.IsA;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.to.CampusTo;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.student.dao.StudentDao;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.feign.InstitutionFeignService;
import com.yxz.edu.student.service.StudentService;
import com.yxz.edu.student.vo.SchoolsVo;
import com.yxz.edu.student.vo.StudentListVo;

@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {
	
	@Autowired
	InstitutionFeignService institutionFeignService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		
		//

		QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<StudentEntity>();
		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			// attr_id attr_name
			queryWrapper.nested(wrapper-> {
				wrapper.like("name", key)
				.or().like("first_name", key)
				.or().like("last_name", key)
				.or().like("cname", key)
				.or().like("email", key)
				.or().like("mobile", key)// .or().like("attr_name",key);
				.or().like("suburb", key)
				.or().like("postcode", key)
				.or().like("school", key)
				.or().like("parent", key);
			});
		}
		queryWrapper.orderByAsc("campus_id","campus_schedule_id","class_type_id","class_level_id");

		IPage<StudentEntity> page = this.page(new Query<StudentEntity>().getPage(params), queryWrapper);

		
		List<StudentEntity> records = page.getRecords();
		
		List<StudentListVo> collect = records.stream().map((studentEntity) -> {
			StudentListVo studentListVo = new StudentListVo();
			BeanUtils.copyProperties(studentEntity, studentListVo);
			// query Campus
//			Map institution = (Map)institutionFeignService.getById(studentEntity.getInstitutionId());
//			if (institution != null)
//				studentListVo.setInstitutionName(institution.getName());
			// query Campus Schedule
			Map campus = (Map)institutionFeignService.campusInfo(studentEntity.getCampusId()).get("campus");
			if (campus != null)
				studentListVo.setCampusName((String)campus.get("name"));
			// query schedule
        	Map schedule = (Map)institutionFeignService.campusscheduleInfo(studentEntity.getCampusScheduleId()).get("campusTrainingSchedule");
        	if(schedule != null)
        		studentListVo.setCampusscheduleName((String)schedule.get("name"));
			// query classroom
//        	Map classroom = (Map)institutionFeignService.classroomInfo(studentEntity.getClassroomId()).get("classroom");
//        	if(classroom != null)
//        		StudetnListVo.setClassroomName((String)classroom.get("name"));
			
//        	//query class Type
        	Map classType = (Map)institutionFeignService.classtypeInfo(studentEntity.getClassTypeId()).get("classType");
        	System.out.println("classType---->>>"+classType);
        	if(classType != null)
        		studentListVo.setClassTypeName((String)classType.get("name"));
			
//        	//query class Level
        	Map classLevel = (Map)institutionFeignService.classlevelInfo(studentEntity.getClassLevelId()).get("classLevel");
        	if(classLevel != null)
        		studentListVo.setClassLevelName((String)classLevel.get("name"));
			return studentListVo;
		}).collect(Collectors.toList());
		
		PageUtils pageUtils = new PageUtils(page);
		pageUtils.setList(collect);

		return pageUtils;
	}

	@Override
	public List<SchoolsVo> querySchools() {
		
		List<StudentEntity> studentEntities = baseMapper.selectSchools();
		if(studentEntities != null) {
			System.out.println("studentEntities>>>"+ studentEntities);
			List<SchoolsVo> schools = studentEntities.stream()
					.filter(student-> student != null)
					.map(student->{
						SchoolsVo schoolsVo = new SchoolsVo();
						//System.out.println(student.getSchool());
						schoolsVo.setValue(student.getSchool());
						return schoolsVo;
						
					})					
					.collect(Collectors.toList());
			return schools;
		}
		return null;
	}

	@Override
	public StudentEntity getByEmail(String email) {

		StudentEntity student = baseMapper.selectOne(new QueryWrapper<StudentEntity>().eq("email", email));
		
		return student;
	}

}