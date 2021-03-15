package com.yxz.edu.exam.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.exam.dao.PaperItemOptionDao;
import com.yxz.edu.exam.entity.PaperItemOptionEntity;
import com.yxz.edu.exam.service.PaperItemOptionService;


@Service("paperItemOptionService")
public class PaperItemOptionServiceImpl extends ServiceImpl<PaperItemOptionDao, PaperItemOptionEntity> implements PaperItemOptionService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PaperItemOptionEntity> page = this.page(
                new Query<PaperItemOptionEntity>().getPage(params),
                new QueryWrapper<PaperItemOptionEntity>()
        );

        return new PageUtils(page);
    }

}