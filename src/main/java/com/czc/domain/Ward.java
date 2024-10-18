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
@TableName("t_ward")
public class Ward implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer wId;
	/**
	 * 
	 */
	private String size;
	/**
	 * 
	 */
	private String wClass;

	private Integer mixNum;
}
