package com.yxz.base.thirdparty.service.impl;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.excel.EasyExcel;
import com.yxz.base.thirdparty.entity.Student;
import com.yxz.base.thirdparty.listener.StudentListener;
import com.yxz.base.thirdparty.service.StudentService;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Override
	public List<Student> importInfo(InputStream fis) {
		
		StudentListener studentListener = new StudentListener();
		EasyExcel.read(fis, Student.class, studentListener).sheet().doRead();
		
		return  studentListener.getStudentList();
	}

	@Override
	public List<Student> list() {
		List<Student> studentList = new ArrayList<Student>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		try {
			studentList.add(new Student("1001", "张三", 23, "男", "陕西西安", dateFormat.parse("2020-09-01")));
			studentList.add(new Student("1002", "李四", 22, "女", "陕西渭南", dateFormat.parse("2020-09-01")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		return studentList;
	}

}
