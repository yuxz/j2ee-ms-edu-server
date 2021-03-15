package com.yxz.edu.tms.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.tms.dao.TeacherClassDao;
import com.yxz.edu.tms.dao.TeacherDao;
import com.yxz.edu.tms.entity.TeacherClassEntity;
import com.yxz.edu.tms.entity.TeacherEntity;
import com.yxz.edu.tms.service.TeacherClassService;
import com.yxz.edu.tms.service.TeacherService;
import com.yxz.edu.tms.vo.TeacherClassListVo;

@Service("teacherClassService")
public class TeacherClassServiceImpl extends ServiceImpl<TeacherClassDao, TeacherClassEntity>
		implements TeacherClassService {
	
	@Autowired
	TeacherDao teacherDao;
	
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
		if(classId != 0) {
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
        
        List<TeacherClassListVo> collect = records.stream().map((teacherClass)->{
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

}