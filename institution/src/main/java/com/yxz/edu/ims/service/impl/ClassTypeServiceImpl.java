package com.yxz.edu.ims.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.ims.dao.ClassTypeDao;
import com.yxz.edu.ims.entity.ClassTypeEntity;
import com.yxz.edu.ims.service.ClassTypeService;


@Service("classTypeService")
public class ClassTypeServiceImpl extends ServiceImpl<ClassTypeDao, ClassTypeEntity> implements ClassTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClassTypeEntity> page = this.page(
                new Query<ClassTypeEntity>().getPage(params),
                new QueryWrapper<ClassTypeEntity>()
        );

        return new PageUtils(page);
    }

}