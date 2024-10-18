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
 * @date 2024-04-28 13:13:19
 */
@Data
@TableName("t_medical")
public class Medical implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String medicalInsurance;

}
