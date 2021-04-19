package com.yxz.base.thirdparty.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.excel.EasyExcel;
import com.yxz.base.thirdparty.component.EasyExcelListenerComponent;
import com.yxz.base.thirdparty.component.EasyExcelUtilComponent;
import com.yxz.base.thirdparty.entity.Student;
import com.yxz.base.thirdparty.service.StudentService;

@Controller
@RequestMapping(value = "/student")
public class EasyExcelController {
	
	@Autowired
	private StudentService studentService;
//	@Autowired
//	EasyExcelComponent<Student> easyExcelComponent;
	
	@RequestMapping(value = "/importPage")    
	public String importStudentInfosPage(MultipartFile file) throws IOException {
		
		return "student";
	}
	/**
	 * 导入学生信息
	 * 
	 * @param file
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "/import")    
	public List<Student> importStudentInfos(MultipartFile file) throws IOException {		
		 List<Student> studentList = studentService.importInfo(file.getInputStream()); 
		 
		 return studentList;
	}
	
	
	
	/**
	 * 导出学生信息
	 * 
	 * @param response
	 * @param request
	 * @throws IOException
	 * @throws ParseException
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value = "/export")
	public void exportStudentInfos(HttpServletResponse response, HttpServletRequest request)
			throws IOException, ParseException {	

		
		studentService.exportInfo(response, "学生花名册", "学生花名册"); 
	}
	

	/**
	 * 读取Excel
	 * 
	 * @return
	 */
	@RequestMapping(value = "/readExcel")
	public List<Student> readExcel() {
		String fileName = "d:\\花名册.xlsx";
		EasyExcelListenerComponent<Student> studentListener = new EasyExcelListenerComponent<>();
		EasyExcel.read(fileName, Student.class, studentListener).sheet().doRead();
		return studentListener.getList();
	}

	/**
	 * 写入Excel
	 * 
	 * @return
	 * @throws ParseException
	 */
	@SuppressWarnings("serial")
	@RequestMapping(value = "/writeExcel")
	public Boolean writeExcel() throws ParseException {
		String fileName = "d:\\花名册2.xlsx";

		List<Student> studentList = new ArrayList<Student>() {
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				add(new Student("2001", "张三2", 23, "男", "陕西西安", dateFormat.parse("2020-09-01")));
				add(new Student("2002", "李四2", 22, "女", "陕西渭南", dateFormat.parse("2020-09-01")));
			}
		};

		EasyExcel.write(fileName, Student.class).sheet("学生信息2").doWrite(studentList);
		return true;
	}
	

	
	
}
