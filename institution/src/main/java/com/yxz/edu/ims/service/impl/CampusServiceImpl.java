package com.yxz.edu.ims.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.ims.dao.CampusDao;
import com.yxz.edu.ims.entity.CampusEntity;
import com.yxz.edu.ims.service.CampusService;


@Service("campusService")
public class CampusServiceImpl extends ServiceImpl<CampusDao, CampusEntity> implements CampusService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CampusEntity> page = this.page(
                new Query<CampusEntity>().getPage(params),
                new QueryWrapper<CampusEntity>()
        );

        return new PageUtils(page);
    }

}