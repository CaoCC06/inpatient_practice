package com.czc.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * 
 * @author caozican
 * @email czc@qq.com
 * @date 2024-04-27 23:17:56
 */
@Data
@TableName("t_patient")
public class Patient implements Serializable{
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer pId;
	/**
	 * 
	 */
	private String pName;

	private String pSex;

	private String symptom;

	private Integer pAge;

	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date pDateOfHospitalization;
	/**
	 * 
	 */
	private Integer wId;


	@TableField(exist = false)
	private Ward ward;

	private List<Medicine> medicines;
}
