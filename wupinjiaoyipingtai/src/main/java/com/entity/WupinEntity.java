package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 物品
 *
 * @author 
 * @email
 */
@TableName("wupin")
public class WupinEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public WupinEntity() {

	}

	public WupinEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 商家
     */
    @TableField(value = "shangjia_id")

    private Integer shangjiaId;


    /**
     * 物品名称
     */
    @TableField(value = "wupin_name")

    private String wupinName;


    /**
     * 物品照片
     */
    @TableField(value = "wupin_photo")

    private String wupinPhoto;


    /**
     * 物品类型
     */
    @TableField(value = "wupin_types")

    private Integer wupinTypes;


    /**
     * 物品库存
     */
    @TableField(value = "wupin_kucun_number")

    private Integer wupinKucunNumber;


    /**
     * 物品原价
     */
    @TableField(value = "wupin_old_money")

    private Double wupinOldMoney;


    /**
     * 现价
     */
    @TableField(value = "wupin_new_money")

    private Double wupinNewMoney;


    /**
     * 点击次数
     */
    @TableField(value = "wupin_clicknum")

    private Integer wupinClicknum;


    /**
     * 物品简介
     */
    @TableField(value = "wupin_content")

    private String wupinContent;


    /**
     * 是否上架
     */
    @TableField(value = "shangxia_types")

    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */
    @TableField(value = "wupin_delete")

    private Integer wupinDelete;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：商家
	 */
    public Integer getShangjiaId() {
        return shangjiaId;
    }


    /**
	 * 获取：商家
	 */

    public void setShangjiaId(Integer shangjiaId) {
        this.shangjiaId = shangjiaId;
    }
    /**
	 * 设置：物品名称
	 */
    public String getWupinName() {
        return wupinName;
    }


    /**
	 * 获取：物品名称
	 */

    public void setWupinName(String wupinName) {
        this.wupinName = wupinName;
    }
    /**
	 * 设置：物品照片
	 */
    public String getWupinPhoto() {
        return wupinPhoto;
    }


    /**
	 * 获取：物品照片
	 */

    public void setWupinPhoto(String wupinPhoto) {
        this.wupinPhoto = wupinPhoto;
    }
    /**
	 * 设置：物品类型
	 */
    public Integer getWupinTypes() {
        return wupinTypes;
    }


    /**
	 * 获取：物品类型
	 */

    public void setWupinTypes(Integer wupinTypes) {
        this.wupinTypes = wupinTypes;
    }
    /**
	 * 设置：物品库存
	 */
    public Integer getWupinKucunNumber() {
        return wupinKucunNumber;
    }


    /**
	 * 获取：物品库存
	 */

    public void setWupinKucunNumber(Integer wupinKucunNumber) {
        this.wupinKucunNumber = wupinKucunNumber;
    }
    /**
	 * 设置：物品原价
	 */
    public Double getWupinOldMoney() {
        return wupinOldMoney;
    }


    /**
	 * 获取：物品原价
	 */

    public void setWupinOldMoney(Double wupinOldMoney) {
        this.wupinOldMoney = wupinOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getWupinNewMoney() {
        return wupinNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setWupinNewMoney(Double wupinNewMoney) {
        this.wupinNewMoney = wupinNewMoney;
    }
    /**
	 * 设置：点击次数
	 */
    public Integer getWupinClicknum() {
        return wupinClicknum;
    }


    /**
	 * 获取：点击次数
	 */

    public void setWupinClicknum(Integer wupinClicknum) {
        this.wupinClicknum = wupinClicknum;
    }
    /**
	 * 设置：物品简介
	 */
    public String getWupinContent() {
        return wupinContent;
    }


    /**
	 * 获取：物品简介
	 */

    public void setWupinContent(String wupinContent) {
        this.wupinContent = wupinContent;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getWupinDelete() {
        return wupinDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setWupinDelete(Integer wupinDelete) {
        this.wupinDelete = wupinDelete;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Wupin{" +
            "id=" + id +
            ", shangjiaId=" + shangjiaId +
            ", wupinName=" + wupinName +
            ", wupinPhoto=" + wupinPhoto +
            ", wupinTypes=" + wupinTypes +
            ", wupinKucunNumber=" + wupinKucunNumber +
            ", wupinOldMoney=" + wupinOldMoney +
            ", wupinNewMoney=" + wupinNewMoney +
            ", wupinClicknum=" + wupinClicknum +
            ", wupinContent=" + wupinContent +
            ", shangxiaTypes=" + shangxiaTypes +
            ", wupinDelete=" + wupinDelete +
            ", createTime=" + createTime +
        "}";
    }
}
