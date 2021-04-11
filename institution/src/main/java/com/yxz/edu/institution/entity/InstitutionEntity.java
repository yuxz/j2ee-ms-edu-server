package com.yxz.edu.institution.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 教育机构
 * 
 * @author XINZHONG YU
 * @email yxz20180810@gmail.com
 * @date 2021-03-15 09:31:08
 */

@Data
@TableName("ims_institution")
public class InstitutionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	
@TableId
	private Long id;
	/**
	 * 教育机构名称
	 */
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
	private String mobile;
	/**
	 * 地址
	 */
	private String address;
	/**
	 * 邮箱
	 */
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
	private Date created;
	/**
	 * 更改时间
	 */
	private Date updated;
	/**
	 * 创建人
	 */
	private Long userId;

}
