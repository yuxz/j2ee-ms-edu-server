package com.yxz.edu.ims.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.ims.dao.InstitutionDao;
import com.yxz.edu.ims.entity.InstitutionEntity;
import com.yxz.edu.ims.service.InstitutionService;


@Service("institutionService")
public class InstitutionServiceImpl extends ServiceImpl<InstitutionDao, InstitutionEntity> implements InstitutionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InstitutionEntity> page = this.page(
                new Query<InstitutionEntity>().getPage(params),
                new QueryWrapper<InstitutionEntity>()
        );

        return new PageUtils(page);
    }

}