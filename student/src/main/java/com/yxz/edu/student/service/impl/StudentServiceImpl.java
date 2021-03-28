package com.yxz.edu.student.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.student.dao.StudentDao;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.service.StudentService;
import com.yxz.edu.student.vo.SchoolsVo;
import com.yxz.edu.student.vo.StudentListVo;

@Service("studentService")
public class StudentServiceImpl extends ServiceImpl<StudentDao, StudentEntity> implements StudentService {
	//TODO feign Campus
//	@Autowired
//	CampusService campusService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
//        IPage<StudentEntity> page = this.page(
//                new Query<StudentEntity>().getPage(params),
//                new QueryWrapper<StudentEntity>()
//        );
		QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<StudentEntity>();

		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			// attr_id attr_name
			queryWrapper.and((wrapper) -> {
				wrapper.like("name", key).or().like("email", key).or().like("mobile", key);// .or().like("attr_name",key);
			});
		}

		IPage<StudentEntity> page = this.page(new Query<StudentEntity>().getPage(params), queryWrapper);

		PageUtils pageUtils = new PageUtils(page);
		List<StudentEntity> records = page.getRecords();
		
		List<StudentListVo> collect = records.stream().map((studentEntity) -> {
			StudentListVo studentListVo = new StudentListVo();
			BeanUtils.copyProperties(studentEntity, studentListVo);
			// query campus
//			CampusEntity campus = campusService.getById(studentEntity.getCampusId());
//			if (campus != null)
//				studentListVo.setCampusName(campus.getName());
			// query classroom
//        	ClassroomEntity classroom = classroomService.getById(studentEntity.getClassroomId());
//        	if(classroom != null)
//        		StudetnListVo.setClassroomName(classroom.getName());
//        	//query classtype
//        	ClassTypeEntity classtype = classTypeService.getById(studentEntity.getClassTypeId());
//        	if(classtype != null)
//        		StudetnListVo.setClassTypeName(classtype.getName());
			return studentListVo;
		}).collect(Collectors.toList());

//        IPage<ClassEntity> page = this.page(
//                new Query<ClassEntity>().getPage(params),
//                new QueryWrapper<ClassEntity>()
//        );
		pageUtils.setList(collect);

		return pageUtils;
	}

	@Override
	public List<SchoolsVo> querySchools() {
		// TODO Auto-generated method stu
		List<StudentEntity> studentEntities = baseMapper.selectSchools();
		if(studentEntities != null) {
			List<SchoolsVo> schools = studentEntities.stream()
					.filter(student-> !StringUtils.isEmpty(student.getSchool()))
					.map((student)->{
						SchoolsVo schoolsVo = new SchoolsVo();
						schoolsVo.setValue(student.getSchool());
						return schoolsVo;
						
					})					
					.collect(Collectors.toList());
			return schools;
		}
		return null;
	}

}