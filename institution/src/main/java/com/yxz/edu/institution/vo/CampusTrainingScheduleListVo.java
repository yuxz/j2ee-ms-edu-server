package com.yxz.edu.institution.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-28 17:50:49
 */
 //TODO 添加后端校验代码
@Data
public class CampusTrainingScheduleListVo  extends CampusTrainingScheduleVo{
	

	
	/**
	 * campus name
	 */
	@JsonProperty("campusName")
	private String campusName;
	
	

}
