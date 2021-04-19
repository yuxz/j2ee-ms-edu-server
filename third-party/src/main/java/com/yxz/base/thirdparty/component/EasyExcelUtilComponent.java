package com.yxz.base.thirdparty.component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.excel.EasyExcel;

//@Component
public class EasyExcelUtilComponent<T> {
	
	private final Class<T> clazz;

    public EasyExcelUtilComponent(Class<T> clazz) {
        this.clazz = clazz;
    }
	
	public void  exportFile(HttpServletResponse response, List<T> list, String fileName, String SheetName) throws IOException {
		// 设置响应类型
				response.setContentType("application/vnd.ms-excel");
				// 设置字符编码
				response.setCharacterEncoding("utf-8");
				// 设置响应头信息
				response.setHeader("Content-disposition",
						"attachment;filename*=utf-8''" + URLEncoder.encode(fileName,  "UTF-8") + ".xlsx");

//				List<Student> studentList = studentService.list();
				// 写入文件
				EasyExcel.write(response.getOutputStream(), clazz).sheet(SheetName).doWrite(list);
	}

}
