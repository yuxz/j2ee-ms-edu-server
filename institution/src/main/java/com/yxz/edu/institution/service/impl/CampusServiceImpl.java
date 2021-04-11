package com.yxz.edu.institution.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yxz.base.common.utils.PageUtils;
import com.yxz.base.common.utils.Query;
import com.yxz.base.common.utils.R;
import com.yxz.edu.institution.dao.CampusDao;
import com.yxz.edu.institution.entity.CampusEntity;
import com.yxz.edu.institution.entity.ClassEntity;
import com.yxz.edu.institution.feign.StudentFeignService;
import com.yxz.edu.institution.service.CampusService;
import com.yxz.edu.institution.service.ClassService;
import com.yxz.edu.institution.vo.CampusAssignVo;
import com.yxz.edu.institution.vo.ClassAssignVo;


@Service("campusService")
public class CampusServiceImpl extends ServiceImpl<CampusDao, CampusEntity> implements CampusService {

	@Autowired
	private ClassService classService;
	@Autowired
	private StudentFeignService studentFeignService;
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
	
	@Override
	public List<CampusAssignVo> campusAssign() {
		List<CampusAssignVo> campusAssignVoList = null;
		//1.get campuses
		List<CampusEntity> campusEntityList = this.list();	
		
		//2.get classes of every campus
		if(campusEntityList != null) {
			 campusAssignVoList = campusEntityList.stream().map(campusEntity->{
				 //active:[3,4],
				List<Long> active = new ArrayList<>();				
				
				//class: [id:3, name: "year5", teacher:[], student:[]]
				List<ClassAssignVo> classAssignVoList = null;
				
				List<ClassEntity> classEntities = classService.list(new QueryWrapper<ClassEntity>().eq("campus_id", campusEntity.getId()));
				if(classEntities != null) {
					classAssignVoList = classEntities.stream().map(classEntity->{
						//2.1 get ids of class of every campus
						active.add(classEntity.getId());
					
						//2.2 get assigned students
						System.out.println("start get sudents---------");
						
//						R r = studentFeignService.list(null, classEntity.getId());
//						if(r != null){
//							List<Object> list = (List<Object>)r.get("data");
//							if(list != null) {
//								System.out.println("ooooooooo"+list);
//							}							
//						}
						
						//2.3 get assigned teachers
						
						
						ClassAssignVo classAssignVo = new ClassAssignVo();
						classAssignVo.setId(classEntity.getId());
						classAssignVo.setName(classEntity.getName());
						classAssignVo.setStudent(null); //
						classAssignVo.setTeacher(null); //
						return classAssignVo;
					}).collect(Collectors.toList());
				};
				
				CampusAssignVo campusAssignVo = new CampusAssignVo();
				campusAssignVo.setId(campusEntity.getId());
				campusAssignVo.setName(campusEntity.getName());
				campusAssignVo.setActive(active);
				campusAssignVo.setClasses(classAssignVoList);
				
				return campusAssignVo;
			}).collect(Collectors.toList());			
		}		
		
		
		//3.get unassigned students of every campus
		R r = studentFeignService.listNotIncludingByClass(null, null);

		
		
		
		return campusAssignVoList;
	}


}