package com.yxz.edu.teacher.service.impl;

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
import com.yxz.edu.teacher.dao.TeacherDao;
import com.yxz.edu.teacher.entity.TeacherEntity;
import com.yxz.edu.teacher.service.TeacherService;
import com.yxz.edu.teacher.vo.TeacherListVo;


@Service("teacherService")
public class TeacherServiceImpl extends ServiceImpl<TeacherDao, TeacherEntity> implements TeacherService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Long isLeaved) {
//        IPage<TeacherEntity> page = this.page(
//                new Query<TeacherEntity>().getPage(params),
//                new QueryWrapper<TeacherEntity>()
//        );

    	QueryWrapper<TeacherEntity> queryWrapper = new QueryWrapper<TeacherEntity>();
    	if(isLeaved != null) {
    		queryWrapper.eq("is_leaved", isLeaved);
    	}
		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			// attr_id attr_name
			queryWrapper.and((wrapper) -> {
				wrapper.like("name", key).or().like("email", key).or().like("mobile", key);// .or().like("attr_name",key);
			});
		}

		IPage<TeacherEntity> page = this.page(new Query<TeacherEntity>().getPage(params), queryWrapper);

		PageUtils pageUtils = new PageUtils(page);
		List<TeacherEntity> records = page.getRecords();
		
		//TODO TeacherServiceImpl
		List<TeacherListVo> collect = records.stream().map((teacherEntity) -> {
			TeacherListVo teacherListVo = new TeacherListVo();
			BeanUtils.copyProperties(teacherEntity, teacherListVo);
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
			return teacherListVo;
		}).collect(Collectors.toList());

//        IPage<ClassEntity> page = this.page(
//                new Query<ClassEntity>().getPage(params),
//                new QueryWrapper<ClassEntity>()
//        );
		pageUtils.setList(collect);

		return pageUtils;
    }

}