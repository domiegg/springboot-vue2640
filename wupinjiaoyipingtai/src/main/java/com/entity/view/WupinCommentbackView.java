package com.entity.view;

import com.entity.WupinCommentbackEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 物品评价
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("wupin_commentback")
public class WupinCommentbackView extends WupinCommentbackEntity implements Serializable {
    private static final long serialVersionUID = 1L;




		//级联表 wupin
			/**
			* 物品 的 商家
			*/
			private Integer wupinShangjiaId;
			/**
			* 物品名称
			*/
			private String wupinName;
			/**
			* 物品照片
			*/
			private String wupinPhoto;
			/**
			* 物品类型
			*/
			private Integer wupinTypes;
				/**
				* 物品类型的值
				*/
				private String wupinValue;
			/**
			* 物品库存
			*/
			private Integer wupinKucunNumber;
			/**
			* 物品原价
			*/
			private Double wupinOldMoney;
			/**
			* 现价
			*/
			private Double wupinNewMoney;
			/**
			* 点击次数
			*/
			private Integer wupinClicknum;
			/**
			* 物品简介
			*/
			private String wupinContent;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer wupinDelete;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 用户手机号
			*/
			private String yonghuPhone;
			/**
			* 用户身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 用户头像
			*/
			private String yonghuPhoto;
			/**
			* 电子邮箱
			*/
			private String yonghuEmail;

	public WupinCommentbackView() {

	}

	public WupinCommentbackView(WupinCommentbackEntity wupinCommentbackEntity) {
		try {
			BeanUtils.copyProperties(this, wupinCommentbackEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

























				//级联表的get和set wupin
					/**
					* 获取：物品 的 商家
					*/
					public Integer getWupinShangjiaId() {
						return wupinShangjiaId;
					}
					/**
					* 设置：物品 的 商家
					*/
					public void setWupinShangjiaId(Integer wupinShangjiaId) {
						this.wupinShangjiaId = wupinShangjiaId;
					}

					/**
					* 获取： 物品名称
					*/
					public String getWupinName() {
						return wupinName;
					}
					/**
					* 设置： 物品名称
					*/
					public void setWupinName(String wupinName) {
						this.wupinName = wupinName;
					}
					/**
					* 获取： 物品照片
					*/
					public String getWupinPhoto() {
						return wupinPhoto;
					}
					/**
					* 设置： 物品照片
					*/
					public void setWupinPhoto(String wupinPhoto) {
						this.wupinPhoto = wupinPhoto;
					}
					/**
					* 获取： 物品类型
					*/
					public Integer getWupinTypes() {
						return wupinTypes;
					}
					/**
					* 设置： 物品类型
					*/
					public void setWupinTypes(Integer wupinTypes) {
						this.wupinTypes = wupinTypes;
					}


						/**
						* 获取： 物品类型的值
						*/
						public String getWupinValue() {
							return wupinValue;
						}
						/**
						* 设置： 物品类型的值
						*/
						public void setWupinValue(String wupinValue) {
							this.wupinValue = wupinValue;
						}
					/**
					* 获取： 物品库存
					*/
					public Integer getWupinKucunNumber() {
						return wupinKucunNumber;
					}
					/**
					* 设置： 物品库存
					*/
					public void setWupinKucunNumber(Integer wupinKucunNumber) {
						this.wupinKucunNumber = wupinKucunNumber;
					}
					/**
					* 获取： 物品原价
					*/
					public Double getWupinOldMoney() {
						return wupinOldMoney;
					}
					/**
					* 设置： 物品原价
					*/
					public void setWupinOldMoney(Double wupinOldMoney) {
						this.wupinOldMoney = wupinOldMoney;
					}
					/**
					* 获取： 现价
					*/
					public Double getWupinNewMoney() {
						return wupinNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setWupinNewMoney(Double wupinNewMoney) {
						this.wupinNewMoney = wupinNewMoney;
					}
					/**
					* 获取： 点击次数
					*/
					public Integer getWupinClicknum() {
						return wupinClicknum;
					}
					/**
					* 设置： 点击次数
					*/
					public void setWupinClicknum(Integer wupinClicknum) {
						this.wupinClicknum = wupinClicknum;
					}
					/**
					* 获取： 物品简介
					*/
					public String getWupinContent() {
						return wupinContent;
					}
					/**
					* 设置： 物品简介
					*/
					public void setWupinContent(String wupinContent) {
						this.wupinContent = wupinContent;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
					}


						/**
						* 获取： 是否上架的值
						*/
						public String getShangxiaValue() {
							return shangxiaValue;
						}
						/**
						* 设置： 是否上架的值
						*/
						public void setShangxiaValue(String shangxiaValue) {
							this.shangxiaValue = shangxiaValue;
						}
					/**
					* 获取： 逻辑删除
					*/
					public Integer getWupinDelete() {
						return wupinDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setWupinDelete(Integer wupinDelete) {
						this.wupinDelete = wupinDelete;
					}
















				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 用户手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 用户手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 用户身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 用户身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 用户头像
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 用户头像
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 电子邮箱
					*/
					public String getYonghuEmail() {
						return yonghuEmail;
					}
					/**
					* 设置： 电子邮箱
					*/
					public void setYonghuEmail(String yonghuEmail) {
						this.yonghuEmail = yonghuEmail;
					}



}
