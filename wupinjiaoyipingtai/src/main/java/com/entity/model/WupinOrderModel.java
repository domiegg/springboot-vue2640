package com.entity.model;

import com.entity.WupinOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 物品订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class WupinOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String wupinOrderUuidNumber;


    /**
     * 收获地址
     */
    private Integer addressId;


    /**
     * 物品
     */
    private Integer wupinId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买数量
     */
    private Integer buyNumber;


    /**
     * 实付价格
     */
    private Double wupinOrderTruePrice;


    /**
     * 快递公司
     */
    private String wupinOrderCourierName;


    /**
     * 订单快递单号
     */
    private String wupinOrderCourierNumber;


    /**
     * 订单类型
     */
    private Integer wupinOrderTypes;


    /**
     * 支付类型
     */
    private Integer wupinOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getWupinOrderUuidNumber() {
        return wupinOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setWupinOrderUuidNumber(String wupinOrderUuidNumber) {
        this.wupinOrderUuidNumber = wupinOrderUuidNumber;
    }
    /**
	 * 获取：收获地址
	 */
    public Integer getAddressId() {
        return addressId;
    }


    /**
	 * 设置：收获地址
	 */
    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }
    /**
	 * 获取：物品
	 */
    public Integer getWupinId() {
        return wupinId;
    }


    /**
	 * 设置：物品
	 */
    public void setWupinId(Integer wupinId) {
        this.wupinId = wupinId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买数量
	 */
    public Integer getBuyNumber() {
        return buyNumber;
    }


    /**
	 * 设置：购买数量
	 */
    public void setBuyNumber(Integer buyNumber) {
        this.buyNumber = buyNumber;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getWupinOrderTruePrice() {
        return wupinOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setWupinOrderTruePrice(Double wupinOrderTruePrice) {
        this.wupinOrderTruePrice = wupinOrderTruePrice;
    }
    /**
	 * 获取：快递公司
	 */
    public String getWupinOrderCourierName() {
        return wupinOrderCourierName;
    }


    /**
	 * 设置：快递公司
	 */
    public void setWupinOrderCourierName(String wupinOrderCourierName) {
        this.wupinOrderCourierName = wupinOrderCourierName;
    }
    /**
	 * 获取：订单快递单号
	 */
    public String getWupinOrderCourierNumber() {
        return wupinOrderCourierNumber;
    }


    /**
	 * 设置：订单快递单号
	 */
    public void setWupinOrderCourierNumber(String wupinOrderCourierNumber) {
        this.wupinOrderCourierNumber = wupinOrderCourierNumber;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getWupinOrderTypes() {
        return wupinOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setWupinOrderTypes(Integer wupinOrderTypes) {
        this.wupinOrderTypes = wupinOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getWupinOrderPaymentTypes() {
        return wupinOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setWupinOrderPaymentTypes(Integer wupinOrderPaymentTypes) {
        this.wupinOrderPaymentTypes = wupinOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
