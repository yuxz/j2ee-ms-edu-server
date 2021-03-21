package com.yxz.edu.exam.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.edu.exam.dao.CategoryDao;
import com.yxz.edu.exam.entity.CategoryEntity;
import com.yxz.edu.exam.service.CategoryService;
import com.yxz.edu.exam.vo.CategoryChildrenVo;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {

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
		// 2. sorted
		//
		//BeanUtils.copyProperties(allCategories, target);
    	List<CategoryChildrenVo> categoryTree = allCategories.stream()
    			.filter(categoryEntity -> categoryEntity.getParentId() == 0)
    			.map(categoryEntity -> {
    				CategoryChildrenVo categoryChildrenVo = new CategoryChildrenVo();
    				BeanUtils.copyProperties(categoryEntity, categoryChildrenVo);
    				
    				categoryChildrenVo.setChildren(getChildren(categoryChildrenVo, allCategories));
    				return categoryChildrenVo;
    				
    			})
    			.sorted((menu1,menu2)->{    	           
    	            return (menu1.getSort()==null?0:menu1.getSort()) - (menu2.getSort()==null?0:menu2.getSort());
    	        })
    			.collect(Collectors.toList());    	
		
		return categoryTree;
	}

	private List<CategoryChildrenVo> getChildren(CategoryChildrenVo parent, List<CategoryEntity> allCategories) {
		
		return allCategories.stream().filter(categoryEntity -> categoryEntity.getParentId() == parent.getId())
				.map(categoryEntity-> {
					CategoryChildrenVo categoryChildrenVo = new CategoryChildrenVo();
					BeanUtils.copyProperties(categoryEntity, categoryChildrenVo);
					categoryChildrenVo.setChildren(getChildren(categoryChildrenVo, allCategories));
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
		if (catagoryId!=0) {	
			fullPath.add(catagoryId);
			CategoryEntity selectById = this.getById(catagoryId);
			if (selectById.getParentId() != 0 ) {
				getParentCategoryId(selectById.getParentId(),fullPath);
			}			
		}		
	}
	

}