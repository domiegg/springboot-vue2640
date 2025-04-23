package com.dao;

import com.entity.WupinCommentbackEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.WupinCommentbackView;

/**
 * 物品评价 Dao 接口
 *
 * @author 
 */
public interface WupinCommentbackDao extends BaseMapper<WupinCommentbackEntity> {

   List<WupinCommentbackView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
