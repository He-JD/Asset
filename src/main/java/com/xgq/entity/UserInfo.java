package com.xgq.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.Version;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author HeJD
 * @since 2018-09-17
 */
@Data
@Accessors(chain = true)
public class UserInfo extends Model<UserInfo> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Integer id;
	private String userName;
	private String passWord;


	public static final String ID = "id";

	public static final String USER_NAME = "user_name";

	public static final String PASS_WORD = "pass_word";

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
