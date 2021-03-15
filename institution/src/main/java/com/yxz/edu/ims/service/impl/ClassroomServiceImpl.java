package com.yxz.edu.ims.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.ims.dao.ClassroomDao;
import com.yxz.edu.ims.entity.ClassroomEntity;
import com.yxz.edu.ims.service.ClassroomService;


@Service("classroomService")
public class ClassroomServiceImpl extends ServiceImpl<ClassroomDao, ClassroomEntity> implements ClassroomService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ClassroomEntity> page = this.page(
                new Query<ClassroomEntity>().getPage(params),
                new QueryWrapper<ClassroomEntity>()
        );

        return new PageUtils(page);
    }

}