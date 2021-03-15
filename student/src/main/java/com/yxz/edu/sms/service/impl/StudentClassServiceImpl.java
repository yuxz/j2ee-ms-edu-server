package com.yxz.edu.sms.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.sms.dao.StudentClassDao;
import com.yxz.edu.sms.entity.StudentClassEntity;
import com.yxz.edu.sms.service.StudentClassService;


@Service("studentClassService")
public class StudentClassServiceImpl extends ServiceImpl<StudentClassDao, StudentClassEntity> implements StudentClassService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StudentClassEntity> page = this.page(
                new Query<StudentClassEntity>().getPage(params),
                new QueryWrapper<StudentClassEntity>()
        );

        return new PageUtils(page);
    }

}