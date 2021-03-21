package com.yxz.edu.sms.service.impl;

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
import com.yxz.edu.sms.dao.StudentClassDao;
import com.yxz.edu.sms.dao.StudentDao;
import com.yxz.edu.sms.entity.StudentClassEntity;
import com.yxz.edu.sms.entity.StudentEntity;
import com.yxz.edu.sms.service.StudentClassService;
import com.yxz.edu.sms.service.StudentService;
import com.yxz.edu.sms.vo.StudentClassListVo;
import com.yxz.edu.sms.vo.StudentClassVo;

@Service("studentClassService")
public class StudentClassServiceImpl extends ServiceImpl<StudentClassDao, StudentClassEntity>
		implements StudentClassService {
	@Autowired
	StudentService studentService;

	@Autowired
	StudentDao studentDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<StudentClassEntity> page = this.page(new Query<StudentClassEntity>().getPage(params),
				new QueryWrapper<StudentClassEntity>());

		return new PageUtils(page);
	}

	@Override
	public List<StudentClassListVo> queryIncludingByClass(Long classId) {
		
		// 1. 从sms_student_class表中找出本班已有学生
		List<StudentClassEntity> list = this.list(new QueryWrapper<StudentClassEntity>().eq("class_id", classId));
//		2. 为本版所有学生添加详细信息
		List<StudentClassListVo> collect = list.stream().map((studentClass) -> {	
			StudentClassListVo studentClassListVo = new StudentClassListVo();
			BeanUtils.copyProperties(studentClass, studentClassListVo);
			StudentEntity student = studentDao.selectById(studentClass.getStudentId());
			studentClassListVo.setStudentName(student.getName());
			studentClassListVo.setBirth(student.getBirth());
			studentClassListVo.setGender(student.getGender());
			studentClassListVo.setMobile(student.getMobile());
			studentClassListVo.setEmail(student.getEmail());
			return studentClassListVo;
		}).collect(Collectors.toList());
		

		if (collect == null || collect.size() == 0) {
			return null;
		}

		return collect;
	}

	@Override
	public PageUtils queryPageNotIncludingByClass(Map<String, Object> params, Long classId) {
		// 1.查出班级已有老师
		List<StudentClassEntity> list = this.list(new QueryWrapper<StudentClassEntity>().eq("class_id", classId));
		List<Long> includedStudentIds = list.stream().map((studentClass) -> {
			return studentClass.getStudentId();
		}).collect(Collectors.toList());

		// 2.查出本机构除了本班级以外的所有老师
		// TODO 只查询本机构所有教师
		QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<StudentEntity>();
		if (includedStudentIds != null && includedStudentIds.size() > 0) {
			queryWrapper.eq("is_actived", 1).notIn("id", includedStudentIds);
		}
		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			queryWrapper.like("name", key);
//					queryWrapper.and((w)->{
//		                w.eq("attr_id",key).or().like("attr_name",key);
//		            });
		}

		IPage<StudentEntity> page = studentService.page(new Query<StudentEntity>().getPage(params), queryWrapper);
		PageUtils pageUtils = new PageUtils(page);
		return pageUtils;
	}

	@Override
	public void saveBatch(List<StudentClassVo> sudentClassVos) {
		List<StudentClassEntity> collect = sudentClassVos.stream().map((studentClassVo) -> {
			StudentClassEntity studentClassEntity = new StudentClassEntity();
			BeanUtils.copyProperties(studentClassVo, studentClassEntity);
			return studentClassEntity;
		}).collect(Collectors.toList());
		this.saveBatch(collect);

	}

}