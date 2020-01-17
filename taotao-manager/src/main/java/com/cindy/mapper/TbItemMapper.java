package com.cindy.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cindy.bean.TbItem;
import com.cindy.common.TaoTaoResult;

public interface TbItemMapper {

	TbItem findTbItemById(Long tbItemId);

	List<TbItem> findTbItem(Integer page, Integer limit);

	Integer findCount();

	

	int updateItemByIds(@Param("ids") List<Long> ids,@Param("type") Integer type);

	//List<TbItem> findTbItemByName(String title);

	List<TbItem> findTbItemByName(Integer page, Integer limit, String title);

	int findTbItemCountBySearch(@Param("title")String title, @Param("priceMin")Integer priceMin, @Param("priceMax")Integer priceMax, @Param("cId")Long cId);

	List<TbItem> findTbItemBySearchPage(@Param("index")int index, @Param("limit")Integer limit, @Param("title")String title, @Param("priceMin")Integer priceMin, 
			@Param("priceMax")Integer priceMax, @Param("cId")Long cId);

	List<TbItem> statisticsItemCId();//统计商品分类

	int findTbItemCountByCId(@Param("cId")Long cId);//根据商品id查询分类下的商品数量
	
	

	

	

	

}
