package com.yxz.edu.institution.vo;

import lombok.Data;

/**
 * 教室
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
 //TODO 添加后端校验代码
@Data
public class ClassroomListVo extends ClassroomVo{
	

	/**
	 * 校区名称
	 */
	private String campusName;
	

}
