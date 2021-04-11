package com.yxz.edu.institution.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.institution.dao.ClassLevelDao;
import com.yxz.edu.institution.entity.ClassLevelEntity;
import com.yxz.edu.institution.service.ClassLevelService;


@Service("classLevelService")
public class ClassLevelServiceImpl extends ServiceImpl<ClassLevelDao, ClassLevelEntity> implements ClassLevelService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClassLevelEntity> page = this.page(
                new Query<ClassLevelEntity>().getPage(params),
                new QueryWrapper<ClassLevelEntity>()
        );

        return new PageUtils(page);
    }

}