package com.yxz.edu.teacher.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.teacher.dao.TeacherExperienceDao;
import com.yxz.edu.teacher.entity.TeacherExperienceEntity;
import com.yxz.edu.teacher.service.TeacherExperienceService;


@Service("teacherExperienceService")
public class TeacherExperienceServiceImpl extends ServiceImpl<TeacherExperienceDao, TeacherExperienceEntity> implements TeacherExperienceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherExperienceEntity> page = this.page(
                new Query<TeacherExperienceEntity>().getPage(params),
                new QueryWrapper<TeacherExperienceEntity>()
        );

        return new PageUtils(page);
    }

}