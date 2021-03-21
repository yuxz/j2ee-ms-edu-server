package com.yxz.edu.ims.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.ims.dao.CampusDao;
import com.yxz.edu.ims.dao.ClassroomDao;
import com.yxz.edu.ims.entity.CampusEntity;
import com.yxz.edu.ims.entity.ClassroomEntity;
import com.yxz.edu.ims.service.ClassroomService;
import com.yxz.edu.ims.vo.ClassroomListVo;

@Service("classroomService")
public class ClassroomServiceImpl extends ServiceImpl<ClassroomDao, ClassroomEntity> implements ClassroomService {

	@Autowired
	CampusDao campusDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<ClassroomEntity> page = this.page(new Query<ClassroomEntity>().getPage(params),
				new QueryWrapper<ClassroomEntity>());

		return new PageUtils(page);
	}

	@Override
	public PageUtils queryPageClassRoom(Map<String, Object> params) {
		IPage<ClassroomEntity> page = this.page(new Query<ClassroomEntity>().getPage(params),
				new QueryWrapper<ClassroomEntity>());
		List<ClassroomEntity> records = page.getRecords();
		List<ClassroomListVo> collect = records.stream().map((classroom) -> {
			ClassroomListVo classroomListVo = new ClassroomListVo();
			BeanUtils.copyProperties(classroom, classroomListVo);
			CampusEntity campus = campusDao.selectById(classroom.getCampusId());
			classroomListVo.setCampusName(campus.getName());
			return classroomListVo;

		}).collect(Collectors.toList());
		PageUtils pageUtils = new PageUtils(page);
		pageUtils.setList(collect);

		return pageUtils;
	}

}