package com.yxz.edu.institution.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.institution.dao.CampusTrainingScheduleDao;
import com.yxz.edu.institution.entity.CampusTrainingScheduleEntity;
import com.yxz.edu.institution.service.CampusTrainingScheduleService;


@Service("campusTrainingScheduleService")
public class CampusTrainingScheduleServiceImpl extends ServiceImpl<CampusTrainingScheduleDao, CampusTrainingScheduleEntity> implements CampusTrainingScheduleService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CampusTrainingScheduleEntity> page = this.page(
                new Query<CampusTrainingScheduleEntity>().getPage(params),
                new QueryWrapper<CampusTrainingScheduleEntity>()
        );

        return new PageUtils(page);
    }

	@Override
	public PageUtils queryPage(Map<String, Object> params, Long campusId) {
		
		IPage<CampusTrainingScheduleEntity> page = this.page(
                new Query<CampusTrainingScheduleEntity>().getPage(params),
                new QueryWrapper<CampusTrainingScheduleEntity>().eq("campus_id",campusId)
        );

        return new PageUtils(page);
	}

}