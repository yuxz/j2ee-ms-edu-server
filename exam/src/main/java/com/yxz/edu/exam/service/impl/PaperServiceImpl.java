package com.yxz.edu.exam.service.impl;

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
import com.yxz.edu.exam.dao.CategoryDao;
import com.yxz.edu.exam.dao.PaperDao;
import com.yxz.edu.exam.entity.CategoryEntity;
import com.yxz.edu.exam.entity.PaperEntity;
import com.yxz.edu.exam.service.PaperService;
import com.yxz.edu.exam.vo.PaperListVo;

@Service("paperService")
public class PaperServiceImpl extends ServiceImpl<PaperDao, PaperEntity> implements PaperService {
	@Autowired
	CategoryDao categoryDao;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {

		QueryWrapper<PaperEntity> queryWrapper = new QueryWrapper<PaperEntity>();
		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			queryWrapper.eq("paper_No", key).or().like("name", key);
		}

		IPage<PaperEntity> page = this.page(new Query<PaperEntity>().getPage(params), queryWrapper);

		PageUtils pageUtils = new PageUtils(page);
		List<PaperEntity> paperList = page.getRecords();
		List<PaperListVo> paperListVos = paperList.stream().map((paper) -> {
			PaperListVo paperListVo = new PaperListVo();
			BeanUtils.copyProperties(paper, paperListVo);

			CategoryEntity selectById = categoryDao.selectById(paper.getCategoryId());
			paperListVo.setCategoryName(selectById.getName());

			return paperListVo;
		}).collect(Collectors.toList());

		pageUtils.setList(paperListVos);

		return pageUtils;
	}

	@Override
	public PageUtils queryPage(Map<String, Object> params, Long categoryId) {

		QueryWrapper<PaperEntity> wrapper = new QueryWrapper<PaperEntity>();

		if (categoryId != 0) {
			wrapper.eq("category_id", categoryId);
		}

		String key = (String) params.get("key");
		if (!StringUtils.isEmpty(key)) {
			wrapper.and((obj) -> {
				obj.eq("id", key).or().like("name", key);
			});
		}

		IPage<PaperEntity> page = this.page(new Query<PaperEntity>().getPage(params), wrapper);
		
		List<PaperEntity> papers = page.getRecords();
		List<PaperListVo> paperListVoList = papers.stream().map((paperEntity) ->{
			PaperListVo paperListVo = new PaperListVo();
			BeanUtils.copyProperties(paperEntity, paperListVo);
			
			CategoryEntity categoryEntity = categoryDao.selectOne(new QueryWrapper<CategoryEntity>().eq("id", paperEntity.getCategoryId()));
			if(categoryEntity != null) {
				paperListVo.setCategoryName(categoryEntity.getName());
			}
			
			return paperListVo;
			}).collect(Collectors.toList());
		
		PageUtils pageUtils = new PageUtils(page);
		pageUtils.setList(paperListVoList);

		return pageUtils;
	}

}