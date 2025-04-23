package com.dao;

import com.entity.WupinOrderEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WupinOrderView;

/**
 * 物品订单 Dao 接口
 *
 * @author 
 */
public interface WupinOrderDao extends BaseMapper<WupinOrderEntity> {

   List<WupinOrderView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
