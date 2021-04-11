package com.yxz.edu.institution.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.institution.dao.CampusTrainingScheduleDao;
import com.yxz.edu.institution.entity.CampusEntity;
import com.yxz.edu.institution.entity.CampusTrainingScheduleEntity;
import com.yxz.edu.institution.service.CampusService;
import com.yxz.edu.institution.service.CampusTrainingScheduleService;
import com.yxz.edu.institution.vo.CampusTrainingScheduleListVo;


@Service("campusTrainingScheduleService")
public class CampusTrainingScheduleServiceImpl extends ServiceImpl<CampusTrainingScheduleDao, CampusTrainingScheduleEntity> implements CampusTrainingScheduleService {
	
	@Autowired
	private CampusService campusService;
	
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
    	QueryWrapper<CampusTrainingScheduleEntity> queryWrapper = new QueryWrapper<CampusTrainingScheduleEntity>();
    	String key = (String)params.get("key");
    	
    	if(!StringUtils.isEmpty(key)) {
    		queryWrapper.like("name", key);
    	}
    	
        IPage<CampusTrainingScheduleEntity> page = this.page(
                new Query<CampusTrainingScheduleEntity>().getPage(params),
                queryWrapper
        );
        List<CampusEntity> campusEntityList = campusService.list();
        List<CampusTrainingScheduleEntity> records = page.getRecords();
        List<CampusTrainingScheduleListVo> collect = records.stream().map(scheduleEntity->{
        	CampusTrainingScheduleListVo campusTrainingScheduleListVo = new CampusTrainingScheduleListVo();
        	BeanUtils.copyProperties(scheduleEntity, campusTrainingScheduleListVo);

        	if(campusEntityList != null) {
        		for (CampusEntity campusEntity : campusEntityList) {
        			if(campusEntity.getId() == campusTrainingScheduleListVo.getCampusId()) {
        				campusTrainingScheduleListVo.setCampusName(campusEntity.getName());
        				break;
        			}
        		}
        	}
        	return campusTrainingScheduleListVo;
        }).collect(Collectors.toList());
        
        PageUtils pageUtils = new PageUtils(page);
		pageUtils.setList(collect);

		return pageUtils; 
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