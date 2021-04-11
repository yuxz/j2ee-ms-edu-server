package com.yxz.base.cms.service.impl;

import java.io.Console;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.constraints.Null;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.cms.dao.CategoryDao;
import com.yxz.base.cms.entity.CategoryEntity;
import com.yxz.base.cms.entity.ContentEntity;
import com.yxz.base.cms.service.CategoryService;
import com.yxz.base.cms.service.ContentService;
import com.yxz.base.cms.vo.CategoryChildrenVo;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;



@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
	
	
	@Autowired
	private ContentService contentService;
	
	@Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    
	@Override
	public List<CategoryChildrenVo> listByTree() {
		
		
		List<CategoryEntity> allCategories = baseMapper.selectList(null);
		
		//TODO 为树添加根节点-机构名称
		// 1. sorted		
		//BeanUtils.copyProperties(allCategories, target);
		List<ContentEntity> contentList = contentService.totalCount();
		
    	List<CategoryChildrenVo> categoryTree = allCategories.stream()
    			.filter(categoryEntity -> categoryEntity.getParentId() == 0)
    			.map(categoryEntity -> {
    				CategoryChildrenVo categoryChildrenVo = new CategoryChildrenVo();
    				BeanUtils.copyProperties(categoryEntity, categoryChildrenVo);
    				
    				categoryChildrenVo.setChildren(getChildren(categoryChildrenVo, allCategories,contentList));
    				return categoryChildrenVo;
    				
    			})
    			.sorted((menu1,menu2)->{    	           
    	            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
    	        })
    			.collect(Collectors.toList());    	
		//2. count
//    	List<ContentEntity> contentList = contentService.totalCount();
//    	List<CategoryChildrenVo> collect = categoryTree.stream().map(categoryEntity ->{
//    		for (ContentEntity contentEntity : contentList) {
//				if(contentEntity.getCategoryId() == categoryEntity.getId()) {
//				    System.out.println(contentEntity.getCategoryId()+"////"+categoryEntity.getId());
//					categoryEntity.setName(categoryEntity.getName() + " "+ contentEntity.getTotalCount());
//					break;
//				}				
//			}
//    		return categoryEntity;
//    	}).collect(Collectors.toList());
		return categoryTree;
	}

	private List<CategoryChildrenVo> getChildren(CategoryChildrenVo parent, List<CategoryEntity> allCategories, List<ContentEntity> contentList) {
		
		return allCategories.stream().filter(categoryEntity -> categoryEntity.getParentId() == parent.getId())
				.map(categoryEntity-> {
					CategoryChildrenVo categoryChildrenVo = new CategoryChildrenVo();
					BeanUtils.copyProperties(categoryEntity, categoryChildrenVo);
					for (ContentEntity contentEntity : contentList) {
						if(contentEntity.getCategoryId() == categoryChildrenVo.getId()) {
						   // System.out.println(contentEntity.getCategoryId()+"////"+categoryEntity.getId());
						    categoryChildrenVo.setName(categoryChildrenVo.getName() + " ("+ contentEntity.getTotalCount() + ")");
							break;
						}				
					}
					
					categoryChildrenVo.setChildren(getChildren(categoryChildrenVo, allCategories,contentList));
					return categoryChildrenVo;
					})
				.sorted((menu1,menu2)->{		            
		            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
		        })
				.collect(Collectors.toList());
		
	}

	@Override
	public void removeMenuByIds(List<Long> asList) {
		//TODO  1、检查当前删除的菜单，是否被别的地方引用

        //逻辑删除
        baseMapper.deleteBatchIds(asList);
		
	}


	@Override
	public Long[] getFullPath(Long catagoryId) {
		
		List<Long> fullPath = new ArrayList<>();
		
		getParentCategoryId(catagoryId,fullPath );			
		
		Collections.reverse(fullPath);
	
		return fullPath.toArray(new Long[fullPath.size()]);
	}


	private void getParentCategoryId(Long catagoryId, List<Long> fullPath) {
		if (catagoryId != null && catagoryId != 0) {	
			fullPath.add(catagoryId);
			CategoryEntity selectById = this.getById(catagoryId);
			if (selectById != null && selectById.getParentId() != 0 ) {
				getParentCategoryId(selectById.getParentId(),fullPath);
			}			
		}		
	}




}