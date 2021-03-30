package com.yxz.edu.teacher.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.teacher.dao.TeacherBackgroundDao;
import com.yxz.edu.teacher.entity.TeacherBackgroundEntity;
import com.yxz.edu.teacher.service.TeacherBackgroundService;


@Service("teacherBackgroundService")
public class TeacherBackgroundServiceImpl extends ServiceImpl<TeacherBackgroundDao, TeacherBackgroundEntity> implements TeacherBackgroundService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<TeacherBackgroundEntity> page = this.page(
                new Query<TeacherBackgroundEntity>().getPage(params),
                new QueryWrapper<TeacherBackgroundEntity>()
        );

        return new PageUtils(page);
    }

}