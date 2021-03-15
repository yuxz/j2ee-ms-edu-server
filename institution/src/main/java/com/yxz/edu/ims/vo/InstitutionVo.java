package com.yxz.edu.ims.vo;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

import com.yxz.base.common.valid.AddGroup;
import com.yxz.base.common.valid.UpdateGroup;

import lombok.Data;

/**
 * 教育机构
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */
 //TODO 添加后端校验代码
@Data
public class InstitutionVo {
	

	/**
	 * id
	 */
	@NotNull(message = "修改必须指定id", groups = { UpdateGroup.class })
	@Null(message = "新增不能指定id", groups = { AddGroup.class })
  private Long id;
	/**
	 * 教育机构名称
	 */
	@NotBlank(message = "机构名称必须提交", groups = { AddGroup.class, UpdateGroup.class })
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 机构负责人
	 */
	
	private String leader;
	/**
	 * 手机号码
	 */
	@Pattern(regexp="^[0-9]$",message = "手机号码必須是數字",groups={AddGroup.class,UpdateGroup.class})
	private String mobile;
	/**
	 * 地址
	 */	
	private String address;
	/**
	 * 邮箱
	 */
	@NotBlank(message = "邮箱地址必须提交", groups = { AddGroup.class, UpdateGroup.class })
	@Email(message = "请输入正确的邮箱地址")
	private String email;
	/**
	 * 网站
	 */
	private String url;
	/**
	 * 头像
	 */
	private String photo;
	/**
	 * 备注
	 */
	private String note;
	/**
	 * token
	 */
	private String token;
	/**
	 * 是否激活
	 */
	private Integer isActived;
	/**
	 * 是否被删除[0-已删，1未删]
	 */
	private Integer logicDeleted;
	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 创建人
	 */
	private Long userId;

}
