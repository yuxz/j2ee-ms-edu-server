package com.yxz.edu.student.service.impl;

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
import com.yxz.edu.student.dao.StudentClassDao;
import com.yxz.edu.student.dao.StudentDao;
import com.yxz.edu.student.entity.StudentClassEntity;
import com.yxz.edu.student.entity.StudentEntity;
import com.yxz.edu.student.feign.InstitutionFeignService;
import com.yxz.edu.student.service.StudentClassService;
import com.yxz.edu.student.service.StudentService;
import com.yxz.edu.student.vo.StudentClassListVo;
import com.yxz.edu.student.vo.StudentClassVo;

@Service("studentClassService")
public class StudentClassServiceImpl extends ServiceImpl<StudentClassDao, StudentClassEntity>
		implements StudentClassService {
	@Autowired
	private StudentService studentService;

	@Autowired
	private StudentDao studentDao;
	
	@Autowired
	private InstitutionFeignService institutionFeignService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<StudentClassEntity> page = this.page(new Query<StudentClassEntity>().getPage(params),
				new QueryWrapper<StudentClassEntity>());

		return new PageUtils(page);
	}

	@Override
	public List<StudentClassListVo> queryIncludingByClass(Long classId) {

		// 1. 从sms_student_class表中找出本班已有学生
		List<StudentClassEntity> list = this.list(new QueryWrapper<StudentClassEntity>().eq("class_id", classId));
//		2. 为本版所有学生添加详细信息
		List<StudentClassListVo> collect = list.stream().map((studentClass) -> {
			StudentClassListVo studentClassListVo = new StudentClassListVo();
			BeanUtils.copyProperties(studentClass, studentClassListVo);
			StudentEntity student = studentDao.selectById(studentClass.getStudentId());
			studentClassListVo.setStudentName(student.getName());
			studentClassListVo.setBirth(student.getBirth());
			studentClassListVo.setGender(student.getGender());
			studentClassListVo.setMobile(student.getMobile());
			studentClassListVo.setEmail(student.getEmail());
			return studentClassListVo;
		}).collect(Collectors.toList());

		if (collect == null || collect.size() == 0) {
			return null;
		}

		return collect;
	}

	@Override
	public PageUtils queryPageNotIncludingByClass(Map<String, Object> params, Long classId) {
		// TODO 學生只能被分配一次，存在問題，每學期，學生都要重新分班的
		// TODO 只查询本机构所有學生
		QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<StudentEntity>();
		List<Long> includedStudentIds = null;
		if (classId != null) {
			// 1.查出班级已有學生
			List<StudentClassEntity> list = this.list(new QueryWrapper<StudentClassEntity>().eq("class_id", classId));
			includedStudentIds = list.stream().map((studentClass) -> {
				return studentClass.getStudentId();
			}).collect(Collectors.toList());

		}
		// 未被分配的學生
		else {
			// 1.已被分班學生
			List<StudentClassEntity> list = this.list();
			includedStudentIds = list.stream().map((studentClass) -> {
				return studentClass.getStudentId();
			}).collect(Collectors.toList());

		}
		// 2.查出本机构除了本班级以外的所有學生
		if (includedStudentIds != null && includedStudentIds.size() > 0) {
			queryWrapper.eq("is_actived", 1).notIn("id", includedStudentIds);
		}
		if (params != null) {
			String key = (String) params.get("key");
			if (!StringUtils.isEmpty(key)) {
				queryWrapper.like("name", key);
//									queryWrapper.and((w)->{
//						                w.eq("attr_id",key).or().like("attr_name",key);
//						            });
			}
		}
		IPage<StudentEntity> page = studentService.page(new Query<StudentEntity>().getPage(params), queryWrapper);
		PageUtils pageUtils = new PageUtils(page);
		return pageUtils;
	}

	@Override
	public List<StudentEntity>  queryUnassignedStudentsByClass() {
		// TODO 學生只能被分配一次，存在問題，每學期，學生都要重新分班的
		// TODO 只查询本机构所有學生
		QueryWrapper<StudentEntity> queryWrapper = new QueryWrapper<StudentEntity>();
		
		// 1.已被分班學生
		List<StudentClassEntity> assignedList = this.list();
		List<Long> assignedStudentIds = assignedList.stream().map((studentClass) -> {
			return studentClass.getStudentId();
		}).collect(Collectors.toList());
		
		// 2.查出本机构除了本班级以外的所有學生
		if (assignedStudentIds != null && assignedStudentIds.size() > 0) {
			queryWrapper.eq("is_actived", 1).notIn("id", assignedStudentIds);
		}
		List<StudentEntity> unassignedList = studentService.list(queryWrapper);
		
		
		return unassignedList;
	}
	@Override
	public void saveBatch(List<StudentClassVo> sudentClassVos) {
		List<StudentClassEntity> collect = sudentClassVos.stream().map((studentClassVo) -> {
			StudentClassEntity studentClassEntity = new StudentClassEntity();
			BeanUtils.copyProperties(studentClassVo, studentClassEntity);
			return studentClassEntity;
		}).collect(Collectors.toList());
		this.saveBatch(collect);

	}

	@Override
	public void autoAssign() {
		//TODO 如何判定那些是新班級 autoAssign
		//1.get all new class 
//		List<ClassTo> classes = new ArrayList<ClassTo>();
//		classes= (List<ClassTo>)institutionFeignService.listAllClass().get("classTos");//institutionFeignService.list();
//		String json = restTemplate.getForObject("http://SPRINGCLOUD-EMPLOYEE-PROVIDER/emp/list",
//                String.class);
		List<Map> classes = (List<Map>)institutionFeignService.listAllClass().get("classTos");
//		List<ClassTo> classes =JSON.parseArray((String)institutionFeignService.listAllClass().get("classTos"),ClassTo.class);
		System.out.println(classes);
		//2. get all unassigned students
		List<StudentEntity> unassignedStudents = this.queryUnassignedStudentsByClass();
		
		List<Long> studentIds = unassignedStudents.stream().map(student->
			student.getId()).collect(Collectors.toList());

//		Stack<Long> sta = new Stack<>();
//		sta.addAll(studentIds);
//		System.out.println(sta.toString());
		//3. 按照班級最大人數，自動插入數據
		for (Map classTo : classes) {
			//学生全部被分配，终止循环
//			if(sta.size()==0) break;
			//1.獲取班級信息
			Long id = new Long((int)classTo.get("id"));
			int maxinum  = (int)classTo.get("maxinum");
			
//			Integer maxinum = classTo.getMaxinum();
			System.out.println(id +"、" + maxinum);
			//2.獲取班級已有人數
			int hadCount = this.count(new QueryWrapper<StudentClassEntity>().eq("class_id", id));
			System.out.println(id +"、" + hadCount);
			//3.循環插入學生，直到滿足最大人數限制
			for (int i = 0; i < (maxinum-hadCount); i++) {				
				StudentClassEntity studentClassEntity = new StudentClassEntity();
				studentClassEntity.setClassId(id);
				//studentClassEntity.setStudentId(sta.pop());				
				this.save(studentClassEntity);
			}
		}
//		List<ClassTo> collect = classes.stream().map(classTo->{
//			//1.獲取班級最大人數
//			Integer maxnum = classTo.getMaxinum();
//			//2.獲取班級已有人數
//			int hadCount = this.count(new QueryWrapper<StudentClassEntity>().eq("class_id", classTo.getId()));
//			
//			//3.循環插入學生，直到滿足最大人數限制
//			for (int i = 0; i < (maxnum-hadCount); i++) {
//				if(stuIndex < maxIndex) {
//					StudentClassEntity studentClassEntity = new StudentClassEntity();
//					studentClassEntity.setClassId(classTo.getId());
//					studentClassEntity.setStudentId(sta.pop());				
//					this.save(studentClassEntity);					
//				}else {
//					break;
//				}			
//			}
//			return classTo;				
//		}).collect(Collectors.toList());
	
	}

}