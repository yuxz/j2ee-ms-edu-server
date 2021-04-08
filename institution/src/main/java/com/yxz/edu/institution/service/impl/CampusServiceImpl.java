package com.yxz.edu.institution.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.institution.dao.CampusDao;
import com.yxz.edu.institution.entity.CampusEntity;
import com.yxz.edu.institution.service.CampusService;


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

	@Override
	public PageUtils queryPageCampus(Map<String, Object> params) {
		 
		IPage<CampusEntity> page = this.page(
                new Query<CampusEntity>().getPage(params),
                new QueryWrapper<CampusEntity>()
        );
//		List<CampusEntity> records = page.getRecords();
//		records.stream().map((campusEntity) -> {
//			CampusListVo campusListVo = new  CampusListVo();
//			BeanUtils.copyProperties(campusEntity, campusListVo);
//			
//			})

        return new PageUtils(page);
	}

}