package com.bosssoft.egov.asset.config.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 *
 * @ClassName 类名：BfSysConfig
 * @Description 功能说明： 配置信息表
 *              <p>
 *              TODO
 *              </p>
 ***********************************************************************
 * @date 创建日期：2018-12-25
 * @author 创建人：wuj
 * @version 版本号：V1.0
 *          <p>
 ****************************修订记录************************************
 * 
 *          2018-12-25 wuj 创建该类功能。
 *
 ***********************************************************************
 *          </p>
 */ 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
@Accessors(chain = true)
@TableName("BF_SYS_CONFIG")
public class  BfSysConfig extends Model<BfSysConfig> {

    private static final long serialVersionUID = 1L; 
 
    
    /**
	 * 配置ID
	 */
    @TableId(value = "CONFIG_ID")
    private Long configId;
    
    /**
	 * 配置编码
	 */
    private String configCode;
    
    /**
	 * 配置名称
	 */
    private String configName;
    
    /**
	 * 配置类型
	 */
    private String configType;
    
    /**
	 * 配置值
	 */
    private String configValue;
    
    /**
	 * 备注
	 */
    private String remark;
    
    /**
	 * 配置值1
	 */
    private String configValue1;
    
    /**
	 * 配置值2
	 */
    private String configValue2;
    
    /**
	 * 配置值3
	 */
    private String configValue3;
    
    /**
	 * 配置值4
	 */
    private String configValue4;
    
    /**
	 * 配置值5
	 */
    private String configValue5;
    
    /**
	 * 创建人
	 */
    private Long creatorId;
    
    /**
	 * 创建人编码
	 */
    private String creatorCode;
    
    /**
	 * 逻辑删除：0 未删除 1 已删除
	 */
    @TableField("DEL_FLAG")
    @TableLogic
    private String delFlag;
    
    /**
	 * 创建时间
	 */
	@TableField(value = "CREATE_TIME", fill = FieldFill.INSERT)
    private Date createTime;
    
    /**
	 * 修改时间
	 */
	@TableField(value = "MODIFY_TIME", fill = FieldFill.INSERT_UPDATE)
    private Date modifyTime;
    
    public static final String CONFIG_ID = "CONFIG_ID";
    public static final String CONFIG_CODE = "CONFIG_CODE";
    public static final String CONFIG_NAME = "CONFIG_NAME";
    public static final String CONFIG_TYPE = "CONFIG_TYPE";
    public static final String CONFIG_VALUE = "CONFIG_VALUE";
    public static final String REMARK = "REMARK";
    public static final String CONFIG_VALUE1 = "CONFIG_VALUE1";
    public static final String CONFIG_VALUE2 = "CONFIG_VALUE2";
    public static final String CONFIG_VALUE3 = "CONFIG_VALUE3";
    public static final String CONFIG_VALUE4 = "CONFIG_VALUE4";
    public static final String CONFIG_VALUE5 = "CONFIG_VALUE5";
    public static final String CREATOR_ID = "CREATOR_ID";
    public static final String CREATOR_CODE = "CREATOR_CODE";
    public static final String DEL_FLAG = "DEL_FLAG";
    public static final String CREATE_TIME = "CREATE_TIME";
    public static final String MODIFY_TIME = "MODIFY_TIME";

    @Override
	protected Serializable pkVal() {
		return this.configId;
	}
}
