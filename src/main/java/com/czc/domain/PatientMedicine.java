package com.czc.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author caozican
 * @email czc@qq.com
 * @date 2024-04-27 23:17:56
 */
@Data
@TableName("t_patient_medicine")
public class PatientMedicine implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private Integer mId;
	/**
	 * 
	 */
	private Integer pId;

	private Integer num;
}
