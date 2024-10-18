package com.czc.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author caozican
 * @email czc@qq.com
 * @date 2024-05-10 16:35:40
 */
@Data
@TableName("t_medicine")
public class Medicine implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer mId;
	/**
	 * 
	 */
	private String mName;
	/**
	 * 
	 */
	private String efficacy;
	/**
	 * 
	 */
	private String packing;
	/**
	 * 
	 */
	private String frequency;
	/**
	 * 
	 */
	private Integer meId;
	/**
	 * 
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date productionDate;


	private Integer Inventory;


	private String Picture;

	@TableField(exist = false)
	private Medical medical;
}
