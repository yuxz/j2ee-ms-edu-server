package com.yxz.edu.teacher.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.teacher.dao.TeacherClassDao;
import com.yxz.edu.teacher.dao.TeacherDao;
import com.yxz.edu.teacher.entity.TeacherClassEntity;
import com.yxz.edu.teacher.entity.TeacherEntity;
import com.yxz.edu.teacher.service.TeacherClassService;
import com.yxz.edu.teacher.service.TeacherService;
import com.yxz.edu.teacher.vo.TeacherClassListVo;
import com.yxz.edu.teacher.vo.TeacherClassVo;

@Service("teacherClassService")
public class TeacherClassServiceImpl extends ServiceImpl<TeacherClassDao, TeacherClassEntity>
		implements TeacherClassService {

	@Autowired
	TeacherDao teacherDao;
	@Autowired
	TeacherService teacherService;

	@Override
	public int count(Wrapper<TeacherClassEntity> queryWrapper) {
		// TODO Auto-generated method stub
		return super.count(queryWrapper);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<TeacherClassEntity> page = this.page(new Query<TeacherClassEntity>().getPage(params),
				new QueryWrapper<TeacherClassEntity>());

		return new PageUtils(page);
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params, Long classId) {
		QueryWrapper<TeacherClassEntity> queryWrapper = new QueryWrapper<TeacherClassEntity>();
		if (classId != 0) {
			queryWrapper.eq("class_id", classId);
		}

//		String key = (String) params.get("key");
//		if (!StringUtils.isEmpty(key)) {
//			queryWrapper.and((wrapper)->{wrapper.like("name", key)})
//			
//		}
		IPage<TeacherClassEntity> page = this.page(new Query<TeacherClassEntity>().getPage(params), queryWrapper);
		PageUtils pageUtils = new PageUtils(page);
		List<TeacherClassEntity> records = page.getRecords();

		List<TeacherClassListVo> collect = records.stream().map((teacherClass) -> {
			TeacherClassListVo teacherClassListVo = new TeacherClassListVo();
			BeanUtils.copyProperties(teacherClass, teacherClassListVo);
			TeacherEntity selectById = teacherDao.selectById(teacherClass.getTeacherId());
			teacherClassListVo.setTeacherName(selectById.getName());
			return teacherClassListVo;
		}).collect(Collectors.toList());
		pageUtils.setList(collect);

//		IPage<TeacherClassEntity> page = this.page(new Query<TeacherClassEntity>().getPage(params),
//				new QueryWrapper<TeacherClassEntity>());

		return pageUtils;
	}

	@Override
	public PageUtils queryPageNotIncludingByClass(Map<String, Object> params, Long classId) {

		// 1.查出班级已有老师
		List<TeacherClassEntity> list = this.list(new QueryWrapper<TeacherClassEntity>().eq("class_id", classId));
		List<Long> includedTeacherIds = list.stream().map((teacherClass) -> {
			return teacherClass.getTeacherId();
		}).collect(Collectors.toList());

		// 2.查出本机构除了本班级以外的所有老师
		// TODO 只查询本机构所有教师
		QueryWrapper<TeacherEntity> queryWrapper = new QueryWrapper<TeacherEntity>();
		if (includedTeacherIds != null && includedTeacherIds.size() > 0) {
			queryWrapper.eq("is_actived", 1).notIn("id", includedTeacherIds);
		}
		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			queryWrapper.like("name", key);
//			queryWrapper.and((w)->{
//                w.eq("attr_id",key).or().like("attr_name",key);
//            });
		}

		IPage<TeacherEntity> page = teacherService.page(new Query<TeacherEntity>().getPage(params), queryWrapper);
		PageUtils pageUtils = new PageUtils(page);
		return pageUtils;
	}

	
	@Override
	public List<TeacherClassListVo> queryIncludingByClass(Long classId) {
		//1. 找出本班已有教师
		List<TeacherClassEntity> list = this.list(new QueryWrapper<TeacherClassEntity>().eq("class_id", classId));

		List<TeacherClassListVo> collect = list.stream().map((teacherClass) -> {
			TeacherClassListVo teacherClassListVo = new TeacherClassListVo();
			BeanUtils.copyProperties(teacherClass, teacherClassListVo);
			TeacherEntity teacher = teacherDao.selectById(teacherClass.getTeacherId());			
			teacherClassListVo.setTeacherName(teacher.getName());			
			return teacherClassListVo;
		}).collect(Collectors.toList());

		if (collect == null || collect.size() == 0) {
			return null;
		}
		
		return collect;
	}

	@Override
	public void saveBatch(List<TeacherClassVo> teacherClassVos) {
		
		List<TeacherClassEntity> collect = teacherClassVos.stream().map((teacherClassVo)->{
			TeacherClassEntity teacherClassEntity = new TeacherClassEntity();
			BeanUtils.copyProperties(teacherClassVo, teacherClassEntity);
			return teacherClassEntity;
		}).collect(Collectors.toList());
		this.saveBatch(collect);
		
	}

}