package com.yxz.edu.ims.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;

import com.yxz.edu.ims.dao.CampusDao;
import com.yxz.edu.ims.entity.CampusEntity;
import com.yxz.edu.ims.service.CampusService;
import com.yxz.edu.ims.vo.CampusListVo;


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